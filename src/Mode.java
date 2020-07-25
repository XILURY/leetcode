import java.util.*;

public class Mode {
    /**
     * 摩尔投票法 找数组中个数多于 n/2 的那个数
     */
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0, count = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }

    //
    public int[] findMode(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (count == 0) { //目前得到的数出现次数为0时，更换另外一个数
                candidate = nums[i];
                count = 1;
            } else {
                if (candidate == nums[i])
                    count++;               //相同+1
                else
                    count--;              //不同-1，
            }
        }
        int[] res = new int[2];
        res[0] = candidate; // 众数
        res[1] = count; // 出现的次数
        return res;
    }


    // 通用方法 可以找任意众数 MAP
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); //新建一个哈希表 键存数组的数 值存该数出现的次数
        for (int ele : nums) {
            if (map.containsKey(ele)) { // 如果这个数已经存在键了
                map.put(ele, map.get(ele) + 1);
            } else { // 如果这个数还没进哈希表
                map.put(ele, 1);
            }
        }
        // 判断该数是不是超过数组一半个数的众数
        int n = nums.length / 2;
        for (Integer i : map.keySet()) {  // 遍历key集
            if (map.get(i) > nums.length / 2)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        Mode mode = new Mode();
        int[] nums = {0, 2, 5, 7, 9, 8, 4, 5, 6};
        System.out.println(Arrays.toString(mode.findMode(nums)));
        System.out.println(mode.majorityElement(nums));
    }
}
