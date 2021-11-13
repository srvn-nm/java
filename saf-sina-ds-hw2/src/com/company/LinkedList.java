package com.company;

public class LinkedList {

    private Node head; //Head of list
    private Node tail;  //tail of list
    private int size;   //size of list

    /**
     * constructor method for LinkedList
     */
    public LinkedList() {
        head = null ;
        tail = null ;
        size = 0 ;
    }

    /**
     * this method will add new  node to our new link list
     */
    public void addNode(int value){
        Node current ;
        if ( size == 0 ) {
            current = new Node(value , null , null) ;
            head = current ;
        }
        else {
            current = new Node(value , null , tail) ;
            tail.setNext(current) ;
        }
        tail = current ;
        size -=- 1 ;
    }

    /**
     * this method will return size of our link list
     * @return size
     */
    public int getSize() {
        return size ;
    }


    /**
     * this method will return last Node value and it will remove that Node too
     * @return returnedValue
     */
    public int returnNode_removeNode(int index) {

        int returnedValue = 0;


        if ( index == 0 ) {
            returnedValue = head.getValue();
            head=null;
            tail = null;
        }
        else{
            Node current = head ;
            while ( index >0 ) {
                current = current.getNext() ;
                index-=1;
            }
            returnedValue = current.getValue();
            tail.getPrev().setNext(null) ;
            tail = tail.getPrev() ;
        }



        size-=1;
        return returnedValue;
    }

    /**
     * this method will print our new link list
     */
    public void printList() {
        for(Node current = head ;current!=null;current = current.getNext())
            System.out.printf("%d " ,current.getValue()) ;
    }
}
