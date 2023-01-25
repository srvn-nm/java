package com.company;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        ArrayList<ArrayList<GFG.Node> > adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            Scanner sc2 = new Scanner(System.in);
            String[] line2 = sc.nextLine().split(" ");
            com.company.GFG.addEdge(adj, Integer.parseInt(line2[0]), Integer.parseInt(line2[1]), Integer.parseInt(line2[2]));
        }
//        com.company.GFG.addEdge(adj, 0, 6, 7);
//        com.company.GFG.addEdge(adj, 0, 1, 2);
//        com.company.GFG.addEdge(adj, 1, 2, 3);
//        com.company.GFG.addEdge(adj, 1, 3, 3);
//        com.company.GFG.addEdge(adj, 6, 3, 3);
//        com.company.GFG.addEdge(adj, 3, 5, 1);
//        com.company.GFG.addEdge(adj, 6, 5, 1);
//        com.company.GFG.addEdge(adj, 2, 5, 1);
//        com.company.GFG.addEdge(adj, 0, 4, 5);
//        com.company.GFG.addEdge(adj, 4, 6, 2);

        com.company.GFG.findShortestPaths(adj, N-1);
    }
}

class GFG {

    static class Node implements Comparator<Node> {

        public int node;

        public int cost;

        public Node() {}

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }
    }


    static void addEdge(ArrayList<ArrayList<Node> > adj, int x, int y, int w) {
        adj.get(x).add(new Node(y, w));
        adj.get(y).add(new Node(x, w));
    }

    static void dijkstra(ArrayList<ArrayList<Node> > adj, int src, int n, int[] dist, int[] paths) {

        PriorityQueue<Node> pq = new PriorityQueue<>(n + 1, new Node());

        Set<String> settled = new HashSet<>();

        pq.add(new Node(src, 0));

        dist[src] = 0;
        paths[src] = 1;

        while (!pq.isEmpty()) {

            int u = pq.peek().node;

            int d = pq.peek().cost;

            pq.poll();

            for (int i = 0; i < adj.get(u).size(); i++) {
                int to = adj.get(u).get(i).node;
                int cost = adj.get(u).get(i).cost;

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

        dijkstra(adj, 0, n, dist, paths);

        System.out.print(paths[n]);
    }
}