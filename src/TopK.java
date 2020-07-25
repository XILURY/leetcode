import java.util.*;

/**
 * 求数组前K个最小数
 */
public class TopK {
    /**
     * 方法一： 先排序 在输出 可以用sort
     */

    /**
     * 方法二： 大顶堆(前 K 小) / 小顶堆（前 K 大),Java中有现成的 PriorityQueue，实现起来最简单。O(NlogK)
     */
    class Solution0 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0) {
                return new int[0];
            }
            Queue<Integer> queue = new PriorityQueue<>(new newComparator()); // PriorityQueue默认从小到大排列，重写Comparator 将其从大到小排列
            for (int i = 0; i < arr.length; i++) {
                if (queue.size() < k) { // 如果队列里面的
                    queue.add(arr[i]);
                } else if (arr[i] < queue.peek()) {  // 如果要插入的值小于队列头（此时的最大值），则取出队头插入该数
                    queue.remove();
                    queue.add(arr[i]);
                }
            }
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = queue.remove();
            }
            return res;
        }
    }

    class newComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    /**
     * 快排改进（效率最高） O（N）
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数
        return quickSearch(arr, 0, arr.length - 1, k-1);
    }

    private int[] quickSearch(int[] nums, int lo, int hi, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
        int j = partition(nums, lo, hi); //基准元素位于下标 j，也就是说，左侧的数组有 j+1 个元素，是原数组中最小的 j+1 个数
//        若 k = j，我们就找到了最小的 k 个数，就是左侧的数组；
//        若 k < j ，则最小的 j 个数一定都在左侧数组中，我们只需要对左侧数组递归地 parition 即可；
//        若 k > j，则左侧数组中的 j 个数都属于最小的 k 个数，我们还需要在右侧数组中寻找最小的 k-j个数，对右侧数组递归地 partition 即可。
        if (j == k) {
            return Arrays.copyOf(nums, j+1);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return k <j ? quickSearch(nums, lo, j - 1, k) : quickSearch(nums, j + 1, hi, k);
    }

    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo]; // 取第一个数作为基准
        int i = lo + 1, j = hi;
        int temp;
        while (i < j) {
            while (nums[j] >= v && i < j) { // 找到一个小于基准值的数停止循环 否则一直递减
                j--;
            }
            while (nums[i] <= v && i < j){
                i++;
            } // 找到一个大于基准值的数
            // 交换
            temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        //最后将基准为与i和j相等位置的数字交换（循环完成后 i = j）
        nums[lo] = nums[j];
        nums[j] = v;
        // 返回基准的索引
        return j;
    }

    public static void main(String[] args) {
        TopK topK = new TopK();
        int[] nums = {0,1,2,1};
        System.out.println(Arrays.toString(topK.getLeastNumbers(nums,1)));
    }
}

