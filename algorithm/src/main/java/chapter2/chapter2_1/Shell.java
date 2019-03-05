package chapter2.chapter2_1;

import chapter2.Example;

/***
 * 选择排序
 * https://blog.csdn.net/weixin_37818081/article/details/79202115
 */
public class Shell extends Example {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                int j = i;
                while (j >= h && less(a[j], a[j - h])) {
                    exch(a, j, j - h);
                    j = j - h;
                }
            }
            h = h / 3;
        }

    }

    /**
     * 希尔排序手动练习
     * @param a
     */
    public static void sortManual(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h<N/3){
             h = h*3+1;
        }
        while (h>=1){
            for (int i = h; i < N; i++) {
                int j = i ;
                while (j>=h&&less(a[j],a[j-h])){
                    exch(a,j,j-h);
                    j=j-h;
                }
            }
            h=h/3;
        }
    }


    public static void main(String[] args) {
        Comparable[] nums = {3, 43, 6, 45,2,1,1};
        sortManual(nums);
        show(nums);
    }
}
