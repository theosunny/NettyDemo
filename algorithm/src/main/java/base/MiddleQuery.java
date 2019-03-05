package base;

import java.util.Arrays;

/**
 * 二分查找
 * 要求必须是排序好的字段
 */
public class MiddleQuery {
    public static void main(String[] args){
        int []arr = new int[]{1,2,3,4,5};
        int rank = rank(4, arr);
        System.out.println(rank);
    }
    static int rank(int qstr,int[] arr){

        Arrays.sort(arr);
        int start = 0;
        int n =arr.length-1;
        while (start<=n){
            int mid = start + (n-start)/2;
            if (arr[mid]<qstr){
                start = mid+1;
            }else if (arr[mid]>qstr){
                n = mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
