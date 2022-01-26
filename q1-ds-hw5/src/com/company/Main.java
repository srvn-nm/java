package com.company;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        Scanner in = new Scanner(System.in);
        long n = in.nextInt();
        for (int i = 0; i < n-1; i++) {
            Scanner input = new Scanner(System.in);
            String edge = input.nextLine();
            char[] e = edge.toCharArray();
            Integer v1 = Integer.getInteger(String.valueOf(e[0]));
            g.addVertex(v1);
            Integer v2 = Integer.getInteger(String.valueOf(e[2]));
            g.addVertex(v2);
            g.addEdge(v1, v2);
        }
        g.iterables();
    }
}
