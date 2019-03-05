package base.stack;

/***
 * 定容栈
 * @param <Item>
 */
public class FixCapacityStack<Item> {
    private int N = 0;
    private Item[] a;

    public FixCapacityStack(int capacity) {
        a = (Item[]) new Object[capacity];
    }

    public void push(Item item) {
        a[N++] = item;
    }

    public Item pop() {
        return a[--N];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }
}

