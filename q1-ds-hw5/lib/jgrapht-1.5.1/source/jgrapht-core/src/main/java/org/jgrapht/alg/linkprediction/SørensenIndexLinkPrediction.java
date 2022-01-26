/*
 * (C) Copyright 2020-2021, by Dimitrios Michail and Contributors.
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
package org.jgrapht.alg.linkprediction;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.LinkPredictionAlgorithm;
import org.jgrapht.alg.util.Pair;

/**
 * Predict links using the Sørensen Index.
 * 
 * <p>
 * This is a local method which computes $s_{xy} = \frac{2|\Gamma(u)\cap\Gamma(v))|}{k(u) +k(v)}$
 * where for a node $v$, $\Gamma(v)$ denotes the set of neighbors of $v$ and $k(v) = |\Gamma(v)|$
 * denotes the degree of $v$.
 * </p>
 * 
 * See the following two papers:
 * <ul>
 * <li>Liben‐Nowell, David, and Jon Kleinberg. "The link‐prediction problem for social networks."
 * Journal of the American society for information science and technology 58.7 (2007):
 * 1019-1031.</li>
 * <li>Zhou, Tao, Linyuan Lü, and Yi-Cheng Zhang. "Predicting missing links via local information."
 * The European Physical Journal B 71.4 (2009): 623-630.</li>
 * </ul>
 * 
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 * 
 * @author Dimitrios Michail
 */
public class SørensenIndexLinkPrediction<V, E>
    implements
    LinkPredictionAlgorithm<V, E>
{
    private Graph<V, E> graph;

    /**
     * Create a new prediction
     * 
     * @param graph the input graph
     */
    public SørensenIndexLinkPrediction(Graph<V, E> graph)
    {
        this.graph = Objects.requireNonNull(graph);
    }

    @Override
    public double predict(V u, V v)
    {
        int du = graph.outDegreeOf(u);
        int dv = graph.outDegreeOf(v);

        if (du + dv == 0) {
            throw new LinkPredictionIndexNotWellDefinedException(
                "Both vertices have zero neighbors", Pair.of(u, v));
        }

        List<V> gu = Graphs.successorListOf(graph, u);
        List<V> gv = Graphs.successorListOf(graph, v);

        Set<V> intersection = new HashSet<>(gu);
        intersection.retainAll(gv);

        return 2d * intersection.size() / (du + dv);
    }

}
