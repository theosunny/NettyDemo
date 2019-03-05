package base.stack;

import java.util.Iterator;

/***
 * 定容栈
 * @param <Item>
 */
public class ImproveStack<Item> implements Iterable<Item> {
    private int N = 0;
    private Item[] a;

    public ImproveStack(int capacity) {
        a = (Item[]) new Object[capacity];
    }

    public void push(Item item) {
        if (N==a.length){
            resize(2*a.length);
        }
        a[N++] = item;
    }

    public Item pop() throws Exception {
        if (N==0){
            throw new Exception("栈已经没有元素了");
        }

        Item item =  a[--N];
        a[N] = null;
        if (N>0&&N==a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void resize(int length) {
        Item[] newArr = (Item[]) new Object[length];
        for (int i = 0; i < a.length; i++) {
            newArr[i] = a[i];
        }
        a = newArr;
    }

    public boolean hasNext() {
        return false;
    }

    public Item next() {
        return null;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int n = N;
            public boolean hasNext() {
                return n>0;
            }

            public Item next() {
                return a[--n];
            }
        };
    }
}

