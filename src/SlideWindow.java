import java.util.*;

class SlideWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            queue.addLast(nums[i]);
        }
        int max = findMax(queue);
        int[] res = new int[nums.length - k + 1];
        res[0] = max;
        int temp;
        for (int i = k; i < nums.length; i++) {
            temp = queue.removeFirst(); // 取出第一个数
            queue.addLast(nums[i]); // 加入数
            if (temp != max && nums[i] < max) { // 退出的数不是最大的 并且进来的数小于最大的
                res[i - k + 1] = max;
            } else if (temp != max && nums[i] > max) { // 退出的数不是最大的 并且进来的数大于最大的
                max = nums[i];
                res[i - k + 1] = max;
            } else {
                max = findMax(queue);
                res[i - k + 1] = max;
            }
        }
        return res;
    }

    private int findMax(LinkedList<Integer> queue) {
        int max = queue.get(0);
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i) > max) {
                max = queue.get(i);
            }
        }
        return max;
    }

    // 测试
    public static void main(String[] args) {
        SlideWindow slideWindow = new SlideWindow();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(slideWindow.maxSlidingWindow(nums,3))); // [3, 3, 5, 5, 6, 7]
    }
}