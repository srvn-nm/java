package com.company;

class Node{
    private int value ; // Node value
    private Node next ; // next Node
    private Node prev ;// previous Node

    /**
     * constructor of node class
     * @param value
     * @param next
     * @param prev
     */
    public Node(int value , Node next , Node prev) {
        this.value = value ;
        this.next = next ;
        this.prev = prev ;
    }

    /**
     * this method will return value of node
     * @return value
     */
    public int getValue() {
        return value ;
    }

    /**
     * this method will return next node
     * @return next
     */
    public Node getNext() {
        return next ;
    }/**
     * this method will return prev node
     * @return prev
     */

    public Node getPrev() {
        return prev ;
    }

    /**
     * this method will set the node value
     * @param value
     */
    public void setValue(int value) {
        this.value = value ;
    }
    /**
     * this method will set the next node value
     * @param next
     */
    public void setNext(Node next) {
        this.next = next ;
    }
    /**
     * this method will set the prev node value
     * @param prev
     */
    public void setPrev(Node prev) {
        this.prev = prev ;
    }
}