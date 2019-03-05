package chapter2.chapter2_1;

import chapter2.Example;

/***
 * 选择排序
 */
public class Insertion extends Example {

    public static   void sort(Comparable[] a){
        int N= a.length;
        for (int i = 1; i < N; i++) {

//            for (int j = i ; j >0&& less(a[j],a[j-1]); j--) {
//                exch(a,j,j-1);
//            }
            int j = i ;
            while (j>0&&less(a[j],a[j-1])){
                exch(a,j,j-1);
                j--;
            }

        }
    }
    public static void main(String[] args){
        Comparable[] nums = {3, 43, 6, 1};
       sort(nums);
       show(nums);
    }
}
