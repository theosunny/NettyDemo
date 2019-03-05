package base.stack;

import java.util.Iterator;

public class StackImplByLinked<Item> implements Iterable<Item> {
    private Node first;//栈顶
    private int N;
    private class Node{
        private Item item;
        private Node next;
    }
     private Item pop(){
         Item item = first.item;
         first = first.next;
         N--;
         return item;
     }
     private void push(Item v){
         Node old = first;
         first = new Node();
         first.item=v;
         first.next=old;
         N++;
     }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Item next() {
                return null;
            }
        };
    }
}
