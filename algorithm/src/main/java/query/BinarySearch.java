package query;

public class BinarySearch {
    public static int search(int[] nums, int target) {
        int start = 0;
        int n = nums.length - 1;
        while (start <= n) {
            int mid = start + (n - start) / 2;
            if (target > nums[mid]) {
                start = mid + 1;
            } else if (target < nums[mid]) {
                n = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int search1 = search(new int[]{1, 2, 3, 4, 5, 6, 7}, 8);
        System.out.println(search1);
    }
}
