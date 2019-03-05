package query;

import java.util.Arrays;

/**
 * ThreeSum 用于统计一个数组中和为 0 的三元组数量。
 */
public class ThreeSumBinarySearch {
    public static void main(String[] args) {
//        int count = count2(new int[]{-1, 1, 0, -2, 3});
        int count = count(new int[]{0,0,0,0,0});
        System.out.println(count);
    }

    //解法1
    public static int count(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum2 = nums[i] + nums[j];
                //二分查找
                int index = BinarySearch.search(nums, -sum2);
                // 应该注意这里的下标必须大于 j，否则会重复统计。
                if (index > j) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static int count2(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0;
        int N = nums.length;
        for (int i = 0; i < N - 2; i++) {
            int l = i + 1, h = N - 1, target = -nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            while (l < h) {
                int sum = nums[l] + nums[h];
                if (sum == target) {
                    cnt++;
                    while (l < h && nums[l] == nums[i + 1]) {
                        l++;
                    }
                    while (l < h && nums[h] == nums[h - 1]) {
                        h--;
                    }
                } else if (sum < target) {
                    l++;
                } else {
                    h--;
                }
            }
        }
        return cnt;
    }
}
