package chapter2.chapter2_1;

import chapter2.Example;

/***
 * 选择排序
 */
public class Selection extends Example {
    public static void sort(Comparable[] a){
        int N= a.length;
        for (int i = 0; i < N; i++) {
            int min = i ;
            for (int j = i+1; j < N; j++) {
                if (less(a[j],a[min])){
                    min = j ;
                }
            }
            exch(a,i,min);
        }
    }
    public static void main(String[] args){
        Comparable[] nums = {3, 43, 6, 1};
         sort(nums);
       show(nums);
    }
}
