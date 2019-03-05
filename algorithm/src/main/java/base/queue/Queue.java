package base.queue;

public class Queue<Item> {
    private Node first;//指向最早添加的节点
    private Node last;//指向最近添加的节点
    private int N;
    private class Node{
        private Item item;
        private Node next;
    }
    private  boolean isEmpty(){
        return first ==null;
    }
    private int size(){
        return N;
    }
    private Item dequeue(){
        Node node = first;
        first = first.next;
        if (isEmpty()){
            last=null;
        }
        N--;
        return node.item;
    }
    private void enqueue(Item item){
        Node node = new Node();
        node.item=item;
        last=node;
        last.next=null;
        if (isEmpty()){
                first=last;
        }else {
            node.next=last;
        }
        N++;

    }
}
