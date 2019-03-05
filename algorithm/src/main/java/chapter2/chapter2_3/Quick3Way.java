package chapter2.chapter2_3;

import chapter2.Example;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way extends Example {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt=lo,i=lo+1, gt=hi;
        Comparable v = a[lo];
        while (i<=gt){
            int cmp = a[i].compareTo(v);
            if (cmp<0){
                exch(a,lt++,i++);
            }else if (cmp>0){
                exch(a,i,gt--);
            }else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);

    }

    public static void main(String[] args) {
        Comparable[] nums = {3, 43, 6, 45, 2, 1, 1};
        sort(nums);
        show(nums);
    }
}
