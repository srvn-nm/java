package com.company;

import java.util.*;

class Pair {
        int v2num, lengthnum;
        public Pair(int v2num, int lengthnum) {
            this.v2num = v2num;
            this.lengthnum = lengthnum;
        }
    }
    class GFG{
        static final int infi = 1000000000;
        // Class of the node
        static class Node {
            int vertexNumber;
            // Adjacency list that shows the vertexNumber of child vertex and the weight of the edge
            List<Pair> children;
            Node(int vertexNumber) {
                this.vertexNumber = vertexNumber;
                children = new ArrayList<>();
            }
            // Function to add the child for the given node
            void add_child(int vNumber, int length) {
                Pair p = new Pair(vNumber, length);
                children.add(p);
            }
        }
        // Function to find the distance of the node from the given source vertex to the destination vertex
        static int[] dijkstraDist(List<Node> g, int s, int[] path) {
            // Stores distance of each vertex from source vertex
            int[] dist = new int[g.size()];
            // Boolean array that show whether the vertex 'i' is visited or not
            boolean[] visited = new boolean[g.size()];
            for(int i = 0; i < g.size(); i++) {
                visited[i] = false;
                path[i] = -1;
                dist[i] = infi;
            }
            dist[s] = 0;
            path[s] = -1;
            int current = s;
            // Set of vertices that has a parent (one or more) marked as visited
            Set<Integer> sett = new HashSet<>();
            while (true) {
                // Mark current as visited
                visited[current] = true;
                for(int i = 0; i < g.get(current).children.size(); i++) {
                    int v = g.get(current).children.get(i).v2num;
                    if (visited[v])
                        continue;
                    // Inserting into the visited vertex
                    sett.add(v);
                    int alt = dist[current] + g.get(current).children.get(i).lengthnum;
                    // Condition to check the distance is correct and update it if it is minimum from the previous computed distance
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        path[v] = current;
                    }
                }
                sett.remove(current);
                if (sett.isEmpty())
                    break;
                // The new current
                int minDist = infi;
                int index = 0;
                // Loop to update the distance of the vertices of the graph
                for(int a : sett) {
                    if (dist[a] < minDist) {
                        minDist = dist[a];
                        index = a;
                    }
                }
                current = index;
            }
            return dist;
        }
        // Driver Code
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            List<Node> v = new ArrayList<>();
            int n = 0 , m = 0,  s = 0;
            Scanner input2 = new Scanner(System.in);
            String edge2 = input2.nextLine();
            String[] edgeString2 = edge2.split(" ");
            n = Integer.parseInt(edgeString2[0]);
            m = Integer.parseInt(edgeString2[1]);
            // Loop to create the nodes
            for(int i = 0; i < n; i++) {
                Node a = new Node(i);
                v.add(a);
            }
            // Creating directed weighted edges
            String edge;
            Scanner input = new Scanner(System.in);
            for (int i = 0; i < m; i++) {
                edge = input.nextLine();
                String[] edgeString = edge.split(" ");
                v.get(Integer.parseInt(edgeString[0])-1).add_child(Integer.parseInt(edgeString[1]), Integer.parseInt(edgeString[2]));
            }
            s = in.nextInt();
            int[] path = new int[v.size()];
            int[] dist = dijkstraDist(v, s, path);
            // Loop to print the distance of every node from source vertex
            for (int j : dist) {
                if (j == infi) {
                    System.out.print(-1+" ");
                    continue;
                }
                System.out.print(j+" ");
            }
        }
    }