package sort.topk;

import java.util.Arrays;

public class TopK {

    private static int[] arr = {1, 5, 8, 9, 6, 24, 3, 1, 4, 8, 5, 3, 4, 5};
    private static int[] arr2 = {1, 5, 8, 9, 6, 24, 3, 1, 4, 8, 5, 3, 4, 5};
    private static int[] arr3 = {1, 5, 8, 9, 6, 24, 3, 1, 4, 8, 5, 3, 4, 5};

    public static void main(String[] args) {
        System.out.println("遍历排序方法结果:");
        topKA(arr, 3);
        System.out.println("快速选择方法结果:");
        topKB(arr2, 14, 3);
        display(arr2, 3);
        System.out.println("基于小顶堆方法的结果:");
        int[] result = topKC(arr3, 3);
        display(result, 3);
    }

    private static void display(int[] data, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    //排序再输出，不建议（复杂度n*logn）
    private static void topKA(int[] arr, int m) {
        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= arr.length - m; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //TopK问题优质解法,利用快排思想
    //在n个数中寻找最大的k个数
    //将数组M[n]中的最大的k(k<n)个数放在数组前面
    //复杂度n*logK
    private static void topKB(int[] arr, int n, int k) {
        if (k >= n) return;
        int X = arr[0];  //划分元素
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < j && arr[j] < X)    //从右向左扫描,如果是比划分元素小，则不动
                j--;
            if (i < j)
                arr[i++] = arr[j];       //大元素向左边移
            while (i < j && arr[i] >= X)  //从左向右扫描，如果是比划分元素大，则不动
                i++;
            if (i < j)
                arr[j--] = arr[i];       //小元素向右边移
        }
        arr[i] = X;
        //划分元素将数组划分两部分，一部分是大于X，我们设为Max[],一部分是小于等于X,Min[]
        //如果前K比Max[]的长度大，那么我们还需要在Min数组里面去递归遍历出前k-i-1个元素
        if (i < k) {
            topKB(arr, n - i - 1, k - i - 1);
        }
        //如果前K比Max[]的长度小，那么我们直接在Max[]数组里面找出TOPK
        else if (i > k) {
            topKB(arr, i, k);
        }
    }

    // 从data数组中获取最大的k个数
    private static int[] topKC(int[] data, int k) {
        // 先取K个元素放入一个数组topk中
        int[] topk = new int[k];
        for (int i = 0; i < k; i++) {
            topk[i] = data[i];
        }

        // 转换成最小堆
        MinHeap heap = new MinHeap(topk);

        // 从k开始，遍历data
        for (int i = k; i < data.length; i++) {
            int root = heap.getRoot();

            // 当数据大于堆中最小的数（根节点）时，替换堆中的根节点，再转换成堆
            if (data[i] > root) {
                heap.setRoot(data[i]);
            }
        }
        return topk;
    }
}

class MinHeap {
    // 堆的存储结构 - 数组
    private int[] data;

    // 将一个数组传入构造方法，并转换成一个小根堆
    public MinHeap(int[] data) {
        this.data = data;
        buildHeap();
    }

    // 将数组转换成最小堆
    private void buildHeap() {
        // 完全二叉树只有数组下标小于或等于 (data.length) / 2 - 1 的元素有孩子结点，遍历这些结点。
        // *比如上面的图中，数组有10个元素， (data.length) / 2 - 1的值为4，a[4]有孩子结点，但a[5]没有*
        for (int i = (data.length) / 2 - 1; i >= 0; i--) {
            // 对有孩子结点的元素heapify
            heapify(i);
        }
    }

    private void heapify(int i) {
        // 获取左右结点的数组下标
        int l = left(i);
        int r = right(i);

        // 这是一个临时变量，表示 跟结点、左结点、右结点中最小的值的结点的下标
        int smallest = i;

        // 存在左结点，且左结点的值小于根结点的值
        if (l < data.length && data[l] < data[i])
            smallest = l;

        // 存在右结点，且右结点的值小于以上比较的较小值
        if (r < data.length && data[r] < data[smallest])
            smallest = r;

        // 左右结点的值都大于根节点，直接return，不做任何操作
        if (i == smallest)
            return;

        // 交换根节点和左右结点中最小的那个值，把根节点的值替换下去
        swap(i, smallest);

        // 由于替换后左右子树会被影响，所以要对受影响的子树再进行heapify
        heapify(smallest);
    }

    // 获取右结点的数组下标
    private int right(int i) {
        return (i + 1) << 1;
    }

    // 获取左结点的数组下标
    private int left(int i) {
        return ((i + 1) << 1) - 1;
    }

    // 交换元素位置
    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    // 获取对中的最小的元素，根元素
    public int getRoot() {
        return data[0];
    }

    // 替换根元素，并重新heapify
    public void setRoot(int root) {
        data[0] = root;
        heapify(0);
    }
}