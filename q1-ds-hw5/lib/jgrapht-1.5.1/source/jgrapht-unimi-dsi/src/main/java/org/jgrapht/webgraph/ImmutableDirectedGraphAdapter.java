/*
 * (C) Copyright 2020-2020, by Sebastiano Vigna and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */

package org.jgrapht.webgraph;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.jgrapht.GraphIterables;
import org.jgrapht.GraphType;
import org.jgrapht.graph.DefaultGraphType;

import com.google.common.collect.Iterables;

import it.unimi.dsi.fastutil.ints.IntIntPair;
import it.unimi.dsi.fastutil.ints.IntIntSortedPair;
import it.unimi.dsi.fastutil.objects.ObjectIterables;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;
import it.unimi.dsi.lang.FlyweightPrototype;
import it.unimi.dsi.webgraph.EFGraph;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.LazyIntIterator;
import it.unimi.dsi.webgraph.LazyIntIterators;
import it.unimi.dsi.webgraph.LazyIntSkippableIterator;
import it.unimi.dsi.webgraph.NodeIterator;
import it.unimi.dsi.webgraph.Transform;

/**
 * An adapter class for directed graphs using <a href="http://webgraph.di.unimi.it/">WebGraph</a>'s
 * {@link ImmutableGraph}.
 *
 * <p>
 * Nodes are instances of {@link Integer} corresponding to the index of a node in WebGraph. Edges
 * are represented by an {@link IntIntPair}. The left and right element are the source and the
 * target of the edge. Since the underlying graph is immutable, the resulting graph is unmodifiable.
 * Edges are immutable and can be tested for equality (e.g., stored in a dictionary).
 *
 * <p>
 * WebGraph provides methods for successors only, so to adapt a directed graph you must provide both
 * a graph and its transpose (methods to compute the transpose are available in {@link Transform}).
 *
 * You need to load an {@link ImmutableGraph} and its transpose using one of the available load
 * methods, and then build an adapter:
 *
 * <pre>
 * immutableGraph = ImmutableGraph.loadMapped("mygraph");
 * immutableTranspose = ImmutableGraph.loadMapped("mygraph-t");
 * adapter = new ImmutableDirectedGraphAdapter(immutableGraph, immutableTranspose);
 * </pre>
 *
 * <p>
 * The first graph will be used to implement {@link #outgoingEdgesOf(Integer)}, and the second graph
 * to implement {@link #incomingEdgesOf(Integer)}. It is your responsibility that the two provided
 * graphs are one the transpose of the other (for each arc
 * <var>x</var>&nbsp;&rarr;&nbsp;<var>y</var> in a graph there must be an arc
 * <var>y</var>&nbsp;&rarr;&nbsp;<var>x</var> in the other, and <i>vice versa</i>). No check will be
 * performed. Note that {@linkplain GraphIterables#edgeCount() computing the number of edges of a
 * graph} requires a full scan of the edge set if {@link ImmutableGraph#numArcs()} is not supported
 * (the first time&mdash;then it will be cached).
 *
 * <p>
 * If you use a load method that does not provide random access, most methods will throw an
 * {@link UnsupportedOperationException}.
 *
 * <p>
 * If you know that you will never used methods based on incoming edges
 * ({@link #incomingEdgesOf(Integer)}, {@link #inDegreeOf(Integer)}, {@link #edgesOf(Integer)},
 * {@link #degreeOf(Integer)}), you can also use the constructor using just a graph, but all such
 * methods will throw a {@link NullPointerException}:
 *
 * <pre>
 * immutableGraph = ImmutableGraph.loadMapped("mygraph");
 * adapter = new ImmutableDirectedGraphAdapter(immutableGraph);
 * </pre>
 *
 * <p>
 * If necessary, you can adapt a {@linkplain it.unimi.dsi.big.webgraph.ImmutableGraph big WebGraph
 * graph} with at most {@link Integer#MAX_VALUE} vertices using the suitable
 * {@linkplain it.unimi.dsi.big.webgraph.ImmutableGraph#wrap(ImmutableGraph) wrapper}.
 *
 * <h2>Thread safety</h2>
 *
 * <p>
 * This class is not thread safe: following the {@link FlyweightPrototype} pattern, users can access
 * concurrently the graph {@linkplain #copy() by getting lightweight copies}.
 *
 * <h2>Fast adjacency check</h2>
 *
 * <p>
 * As it happens for the sparse representation of JGraphT, usually a WebGraph compressed
 * representation requires scanning the adjacency list of a node to
 * {@linkplain #getEdge(Integer, Integer) test whether a specific arc exists}. However, if you adapt
 * a WebGraph class (such as {@link EFGraph}) which provides {@linkplain LazyIntSkippableIterator
 * skippable iterators} with fast skipping, adjacency can be tested more quickly (e.g., essentially
 * in constant time in the case of {@link EFGraph}).
 *
 * @see AbstractImmutableBigGraphAdapter
 * @author Sebastiano Vigna
 */

public class ImmutableDirectedGraphAdapter
    extends
    AbstractImmutableGraphAdapter<IntIntPair>
    implements
    FlyweightPrototype<ImmutableDirectedGraphAdapter>
{

    /**
     * The transpose of {@link #immutableGraph}, for a directed graph with full support;
     * {@code null}, for a directed graph with access to outgoing edges, only.
     */
    private final ImmutableGraph immutableTranspose;

    /**
     * Creates an adapter for a directed immutable graph.
     *
     * <p>
     * It is responsibility of the caller that the two provided graphs are one the transpose of the
     * other (for each arc <var>x</var>&nbsp;&rarr;&nbsp;<var>y</var> in a graph there must be an
     * arc <var>y</var>&nbsp;&rarr;&nbsp;<var>x</var> in the other). If this property is not true,
     * results will be unpredictable.
     *
     * @param immutableGraph an immutable graph.
     * @param immutableTranspose its transpose.
     */

    public ImmutableDirectedGraphAdapter(
        final ImmutableGraph immutableGraph, final ImmutableGraph immutableTranspose)
    {
        super(immutableGraph);
        this.immutableTranspose = immutableTranspose;
        if (immutableTranspose != null && n != immutableTranspose.numNodes())
            throw new IllegalArgumentException(
                "The graph has " + n + " nodes, but the transpose has "
                    + immutableTranspose.numNodes());
    }

    /**
     * Creates an adapter for a directed immutable graph implementing only methods based on outgoing
     * edges.
     *
     * @param immutableGraph an immutable graph.
     */
    public ImmutableDirectedGraphAdapter(final ImmutableGraph immutableGraph)
    {
        this(immutableGraph, null);
    }

    @Override
    protected IntIntPair makeEdge(final int x, final int y)
    {
        return IntIntPair.of(x, y);
    }

    @Override
    public boolean containsEdge(final IntIntPair e)
    {
        if (e == null)
            return false;
        if (e instanceof IntIntSortedPair)
            return false;
        return containsEdgeFast(e.leftInt(), e.rightInt());
    }

    @Override
    public Set<IntIntPair> edgeSet()
    {
        final NodeIterator nodeIterator = immutableGraph.nodeIterator();
        final long m = iterables().edgeCount();
        final ObjectOpenHashBigSet<IntIntPair> edges = new ObjectOpenHashBigSet<>(m);
        for (int i = 0; i < n; i++) {
            final int x = nodeIterator.nextInt();
            final LazyIntIterator successors = nodeIterator.successors();
            for (int y; (y = successors.nextInt()) != -1;)
                edges.add(IntIntPair.of(x, y));
        }
        return edges;
    }

    @Override
    public int degreeOf(final Integer vertex)
    {
        final long d = (long) inDegreeOf(vertex) + outDegreeOf(vertex);
        if (d > Integer.MAX_VALUE)
            throw new ArithmeticException();
        return (int) d;
    }

    @Override
    public Set<IntIntPair> edgesOf(final Integer vertex)
    {
        final ObjectLinkedOpenHashSet<IntIntPair> set = new ObjectLinkedOpenHashSet<>();
        final int source = vertex;
        final LazyIntIterator successors = immutableGraph.successors(source);
        for (int target; (target = successors.nextInt()) != -1;)
            set.add(IntIntPair.of(source, target));
        final LazyIntIterator predecessors = immutableTranspose.successors(source);
        for (int target; (target = predecessors.nextInt()) != -1;)
            if (source != target)
                set.add(IntIntPair.of(target, source));
        return set;
    }

    @Override
    public int inDegreeOf(final Integer vertex)
    {
        return immutableTranspose.outdegree(vertex);
    }

    @Override
    public Set<IntIntPair> incomingEdgesOf(final Integer vertex)
    {
        final ObjectLinkedOpenHashSet<IntIntPair> set = new ObjectLinkedOpenHashSet<>();
        final int source = vertex;
        final LazyIntIterator predecessors = immutableTranspose.successors(source);
        for (int target; (target = predecessors.nextInt()) != -1;)
            set.add(IntIntPair.of(target, source));
        return set;
    }

    @Override
    public int outDegreeOf(final Integer vertex)
    {
        return immutableGraph.outdegree(vertex);
    }

    @Override
    public Set<IntIntPair> outgoingEdgesOf(final Integer vertex)
    {
        final ObjectLinkedOpenHashSet<IntIntPair> set = new ObjectLinkedOpenHashSet<>();
        final int source = vertex;
        final LazyIntIterator successors = immutableGraph.successors(source);
        for (int target; (target = successors.nextInt()) != -1;)
            set.add(IntIntPair.of(source, target));
        return set;
    }

    @Override
    public GraphType getType()
    {
        return new DefaultGraphType.Builder()
            .weighted(false).modifiable(false).allowMultipleEdges(false).allowSelfLoops(true)
            .directed().build();
    }

    @Override
    public ImmutableDirectedGraphAdapter copy()
    {
        return new ImmutableDirectedGraphAdapter(
            immutableGraph.copy(), immutableTranspose != null ? immutableTranspose.copy() : null);
    }

    private final GraphIterables<Integer, IntIntPair> ITERABLES = new GraphIterables<>()
    {
        @Override
        public ImmutableDirectedGraphAdapter getGraph()
        {
            return ImmutableDirectedGraphAdapter.this;
        }

        @Override
        public long vertexCount()
        {
            return n;
        }

        @Override
        public long edgeCount()
        {
            if (m != -1)
                return m;
            try {
                return m = immutableGraph.numArcs();
            } catch (final UnsupportedOperationException e) {
            }
            return m = ObjectIterables.size(edges());
        }

        @Override
        public long degreeOf(final Integer vertex)
        {
            return inDegreeOf(vertex) + outDegreeOf(vertex);
        }

        @Override
        public Iterable<IntIntPair> edgesOf(final Integer source)
        {
            return Iterables.concat(outgoingEdgesOf(source), incomingEdgesOf(source, true));
        }

        @Override
        public long inDegreeOf(final Integer vertex)
        {
            return immutableTranspose.outdegree(vertex);
        }

        private Iterable<IntIntPair> incomingEdgesOf(final int x, final boolean skipLoops)
        {
            return () -> new Iterator<>()
            {
                final LazyIntIterator successors = immutableTranspose.successors(x);
                int y = successors.nextInt();

                @Override
                public boolean hasNext()
                {
                    if (y == -1) {
                        y = successors.nextInt();
                        if (skipLoops && x == y)
                            y = successors.nextInt();
                    }
                    return y != -1;
                }

                @Override
                public IntIntPair next()
                {
                    final IntIntPair edge = IntIntPair.of(y, x);
                    y = -1;
                    return edge;
                }
            };
        }

        @Override
        public Iterable<IntIntPair> incomingEdgesOf(final Integer vertex)
        {
            return incomingEdgesOf(vertex, false);
        }

        @Override
        public long outDegreeOf(final Integer vertex)
        {
            return immutableGraph.outdegree(vertex);
        }

        @Override
        public Iterable<IntIntPair> outgoingEdgesOf(final Integer vertex)
        {
            return () -> new Iterator<>()
            {
                final int x = vertex;
                final LazyIntIterator successors = immutableGraph.successors(x);
                int y = successors.nextInt();

                @Override
                public boolean hasNext()
                {
                    if (y == -1)
                        y = successors.nextInt();
                    return y != -1;
                }

                @Override
                public IntIntPair next()
                {
                    final IntIntPair edge = IntIntPair.of(x, y);
                    y = -1;
                    return edge;
                }
            };
        }

        @Override
        public Iterable<IntIntPair> edges()
        {
            return () -> new Iterator<>()
            {
                final NodeIterator nodeIterator = immutableGraph.nodeIterator();
                LazyIntIterator successors = LazyIntIterators.EMPTY_ITERATOR;
                int x, y = -1;

                @Override
                public boolean hasNext()
                {
                    if (y != -1)
                        return true;
                    while ((y = successors.nextInt()) == -1) {
                        if (!nodeIterator.hasNext())
                            return false;
                        x = nodeIterator.nextInt();
                        successors = nodeIterator.successors();
                    }
                    return true;
                }

                @Override
                public IntIntPair next()
                {
                    if (!hasNext())
                        throw new NoSuchElementException();
                    final IntIntPair edge = IntIntPair.of(x, y);
                    y = -1;
                    return edge;
                }
            };
        }
    };

    @Override
    public GraphIterables<Integer, IntIntPair> iterables()
    {
        return ITERABLES;
    }
}
