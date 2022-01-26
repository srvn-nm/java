/*
 * (C) Copyright 2019-2021, by Dimitrios Michail and Contributors.
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
package org.jgrapht.opt.graph.sparse;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.jgrapht.alg.util.Pair;
import org.jgrapht.opt.graph.sparse.specifics.AbstractSparseSpecificsGraph;
import org.jgrapht.opt.graph.sparse.specifics.IncidenceMatrixSparseUndirectedSpecifics;
import org.jgrapht.opt.graph.sparse.specifics.SparseGraphSpecifics;

/**
 * Sparse undirected graph.
 *
 * <p>
 * Assuming the graph has $n$ vertices, the vertices are numbered from $0$ to $n-1$. Similarly,
 * edges are numbered from $0$ to $m-1$ where $m$ is the total number of edges.
 * 
 * <p>
 * It stores the boolean incidence matrix of the graph (rows are vertices and columns are edges) as
 * Compressed Sparse Rows (CSR). In order to also support constant time source and target lookups
 * from an edge identifier we also store the transposed of the incidence matrix again in compressed
 * sparse rows format. This is a classic format for write-once read-many use cases. Thus, the graph
 * is unmodifiable.
 * 
 *
 * <p>
 * The question of whether a sparse or dense representation is more appropriate is highly dependent
 * on various factors such as the graph, the machine running the algorithm and the algorithm itself.
 * Wilkinson defined a matrix as "sparse" if it has enough zeros that it pays to take advantage of
 * them. For more details see
 * <ul>
 * <li>Wilkinson, J. H. 1971. Linear algebra; part II: the algebraic eigenvalue problem. In Handbook
 * for Automatic Computation, J. H. Wilkinson and C. Reinsch, Eds. Vol. 2. Springer-Verlag, Berlin,
 * New York.</li>
 * </ul>
 * 
 * Additional information about sparse representations can be found in the
 * <a href="https://en.wikipedia.org/wiki/Sparse_matrix">wikipedia</a>.
 * 
 * @author Dimitrios Michail
 */
public class SparseIntUndirectedGraph
    extends
    AbstractSparseSpecificsGraph<SparseGraphSpecifics>
{
    /**
     * Create a new graph from an edge list
     * 
     * @param numVertices number of vertices
     * @param edges edge list
     */
    public SparseIntUndirectedGraph(int numVertices, List<Pair<Integer, Integer>> edges)
    {
        this(numVertices, edges.size(), () -> edges.stream());
    }

    /**
     * Create a new graph from an edge stream
     * 
     * @param numVertices number of vertices
     * @param numEdges number of edges
     * @param edges supplier of an edge stream
     */
    public SparseIntUndirectedGraph(
        int numVertices, int numEdges, Supplier<Stream<Pair<Integer, Integer>>> edges)
    {
        super(() -> new IncidenceMatrixSparseUndirectedSpecifics(numVertices, numEdges, edges));
    }
}
