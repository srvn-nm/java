package com.company;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        ArrayList<ArrayList<graph.Node> > adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] line2 = sc.nextLine().split(" ");
            graph.addEdge(adj, Integer.parseInt(line2[0]), Integer.parseInt(line2[1]), Integer.parseInt(line2[2]));
        }

        graph.findShortestPaths(adj, N-1);
    }
}

class graph {

    static class Node implements Comparator<Node> {

        public int node;

        public int weight;

        public Node() {}

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.weight, node2.weight);
        }
    }


    static void addEdge(ArrayList<ArrayList<Node> > adj, int x, int y, int d) {
        adj.get(x).add(new Node(y, d));
        adj.get(y).add(new Node(x, d));
    }

    static void dijkstra(ArrayList<ArrayList<Node>> adj, int n, int[] dist, int[] paths) {

        PriorityQueue<Node> pq = new PriorityQueue<>(n + 1, new Node());

        Set<String> settled = new HashSet<>();

        pq.add(new Node(0, 0));

        dist[0] = 0;
        paths[0] = 1;

        while (!pq.isEmpty()) {

            int u = pq.peek().node;

            int d = pq.peek().weight;

            pq.poll();

            for (int i = 0; i < adj.get(u).size(); i++) {
                int to = adj.get(u).get(i).node;
                int cost = adj.get(u).get(i).weight;

                if (settled.contains(to + " " + u))
                    continue;

                if (dist[to] > dist[u] + cost) {

                    pq.add(new Node(to, d + cost));

                    dist[to] = dist[u] + cost;

                    paths[to] = paths[u];
                }

                else if (dist[to] == dist[u] + cost) {
                    paths[to] = (paths[to] + paths[u]);
                }

                settled.add(to + " " + u);
            }
        }
    }

    static void
    findShortestPaths(ArrayList<ArrayList<Node>> adj, int n) {

        int[] dist = new int[n + 5];

        int[] paths = new int[n + 5];

        for (int i = 0; i <= n; i++)
            dist[i] = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++)
            paths[i] = 0;

        dijkstra(adj, n, dist, paths);

        System.out.print(paths[n]);
    }
}