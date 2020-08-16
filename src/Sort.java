import java.util.Arrays;

/**
 * 排序算法大集合
 */
public class Sort {
    public static void main(String[] args) {
        int[] nums = new int[]{9, 7, 8, 5, 6, 2, 4, 3, 1, 0, 4};
//        bubbleSort(nums); // 冒泡排序
//        selectionSort(nums); // 选择排序
//        innsertSort(nums); // 插入排序
//        shellSort(nums); // 希尔排序
//        quickSort(nums, 0, nums.length - 1); //快速排序
//        mergeSort(nums, 0, nums.length - 1); // 归并排序
//        heapSort(nums); // 堆排序
        radixSort(nums); // 基数排序
        System.out.println(Arrays.toString(nums));
    }


    // 冒泡排序 平均时间复杂度和最差时间复杂度都为O(n^2) 是稳定排序 不占用额外空间
    // 1. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
    // 2. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
    // 3. 针对所有的元素重复以上的步骤，除了最后一个。
    // 4. 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
    public static void bubbleSort(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length - 1; i++) { // 冒泡排序的次数
            boolean flag = true; // 引入标识符对冒泡排序进行优化 如果没有发生交换就提前结束冒泡
            for (int j = 0; j < nums.length - 1 - i; j++) { // 开始比较和交换
                if (nums[j] > nums[j + 1]) { // 如果前一个比后一个大 交换
                    flag = false;
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
            if (flag) break; // 没有发生交换 提前结束冒泡
        }
    }


    // 选择排序 平均时间复杂度和最差时间复杂度都为O(n^2) 不是稳定排序 不占用额外空间
    //1. 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
    //2. 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
    //3. 重复第二步，直到所有元素均排序完毕。
    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) { // 选择排序的次数j
            int min = nums[i];
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) { // 从数组中找出最小的数并放到i的位置（最前面）
                if (nums[j] < min) {
                    min = nums[j]; // 更新最小值
                    minIndex = j;  // 更新最小值索引
                }
            }
            // 交换 最小值放到最前面去
            if (minIndex != i) { // 说明此时需要交换
                nums[minIndex] = nums[i];
                nums[i] = min;
            }
        }
    }


    // 插入排序 平均时间复杂度和最差时间复杂度都为O(n^2) 是稳定排序 不占用额外空间 （适用于大部分已经有序的数组）
    //1. 把 n 个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含 1 个元素，无序表中包含有 n-1 个元素，
    //2. 从头到尾依次扫描无序表，将扫描到的每个元素插入有序序列的适当位置。
    public static void innsertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int insertValue = nums[i]; // 获取待插入的数字
            int insertIndex = i; // 获取插入的索引
            for (int j = i - 1; j >= 0; j--) { // 遍历有序数组
                //拿待插入的数与之前已排序序列逐一往前比较，如果比待插入的值大，说明待插入的数应该放置在该数前面，把该数往后挪一步，并记录待插入的索引
                if (nums[j] > insertValue) { // 插入数字
                    nums[j + 1] = nums[j];
                    insertIndex = j;
                }
            }
            if (insertIndex != i) { // 如果索引变了 说明发生了插入 要更新值 否则可以跳过这一步
                nums[insertIndex] = insertValue;
            }
        }
    }


    // 希尔（shell）排序 平均时间复杂度为 O(nlogn)，最差时间复杂度为 O(n^2)，不是稳定排序，不占用额外空间
    //希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
    //1. 记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
    //2. 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止。
    public static void shellSort(int[] nums) {
        int n = nums.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) { // 从第一组的第二个数开始（每组交替进行插值排序）
                int insertValue = nums[i]; // 获得待插入的值
                int insertIndex = i; // 获得待插入的索引
                for (int j = i - gap; j >= 0; j -= gap) { //遍历有序数组(每组的索引相差gap)
                    if (nums[j] > insertValue) {
                        nums[j + gap] = nums[j];
                        insertIndex = j;
                    }
                }
                if (insertIndex != i) nums[insertIndex] = insertValue; //索引变了才需要改 否则跳过
            }
            gap /= 2;
        }
    }


    // 快速排序 平均时间复杂度为O(nlogn)，最差时间复杂度为O(n^2)，不是稳定排序 ，占用额外空间O(logn)~O(n)。
    //1. 从数列中挑出一个元素，称为 “基准”（pivot）;
    //2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
    //3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
    public static void quickSort(int[] nums, int left, int right) {
        if (left > right) return;  // 递归结束返回条件
        int value = nums[left]; // 取第一个数为基准
        int i = left;
        int j = right;
        while (i < j) {
            //先看右边，依次往左递减 直到找到要交换的数字为止
            while (nums[j] >= value && i < j) {
                j--;
            }
            //再看左边，依次往右递增 直到找到要交换的数字为止
            while (nums[i] <= value && i < j) {
                i++;
            }
            //开始交换
            if (i < j) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        //最后将基准为与i和j相等位置的数字交换（循环完成后 i = j）
        nums[left] = nums[i];
        nums[i] = value; //基准值归位

        quickSort(nums, left, i - 1); // 递归调用 处理左半部分
        quickSort(nums, i + 1, right); // 递归调用 处理右半部分
    }


    // 归并排序 平均时间复杂度为 O(nlogn)，最差时间复杂度为 O(nlogn)，是稳定排序 ，占用额外空间 O(n)。
    //归并排序（Merge Sort）是建立在归并操作上的一种有效，稳定的排序算法，该算法是采用分治法的一个非常典型的应用。
    // 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
    // 归并排序思路简单，速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2; // 用这种方法求中值避免未知的BUG（left+right过大溢出）
        // 分
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        // 治（合并）
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[nums.length]; // 初始化一个数组 用来存排序的子数组
        int i = left;
        int j = mid + 1;
        int cur = left; // 指向temp数组当前索引

        // 比较填充数组 直到左右两个有序数组有一个遍历完
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[cur] = nums[i];
                i++;
                cur++;
            } else {
                temp[cur] = nums[j];
                j++;
                cur++;
            }
        }

        // 有一个有序数组已经遍历完 则将另一个有序数组中剩余的数字添加到temp中
        while (i <= mid) {
            temp[cur++] = nums[i++];
        }
        while (j <= right) {
            temp[cur++] = nums[j++];
        }

        //复制回原数组
        for (int k = left; k <= right; k++)
            nums[k] = temp[k];
    }


    // 堆排序 平均时间复杂度为O(nlog n)，最差时间复杂度为O(nlog n)，不是稳定排序，不占用额外空间。
    //1. 将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
    //2. 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
    //3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
    public static void heapSort(int[] nums) {
        // 建堆
        for (int i = nums.length / 2 - 1; i >= 0; i--) { // num.length/2-1为最大的非叶子节点索引（完全二叉树的性质）
            buildHeap(nums, i, nums.length);
        }

        // 交换 将堆顶元素与堆尾交换
        for (int j = nums.length - 1; j > 0; j--) { // 从堆尾元素开始往前遍历 交换 再建堆 以此循环
            int temp = nums[j];
            nums[j] = nums[0];
            nums[0] = temp;

            // 重建堆
            buildHeap(nums, 0, j);
        }
    }

    private static void buildHeap(int[] nums, int i, int length) {
        int temp = nums[i]; // 先取出并保存非叶子节点i的值

        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && nums[k] < nums[k + 1]) k++; // 比较左子节点和右子节点的大小，将K指向较大的那个
            if (nums[i] < nums[k]) { // 将非叶子节点i的值与其左右子节点中较大的值进行比较，并调整
                nums[i] = nums[k];
                nums[k] = temp;
                i = k;  // 将i的指针变为k，继续判断下一个非叶子节点是否需要调整。
            } else {
                break;
            }
        }
    }


    // 基数排序
    //1. 将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零
    //2. 从最低位开始，依次进行一次排序
    //3. 从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列
    public static void radixSort(int[] nums) {
        // 寻找数组中最大数
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        // 得到最大数是几位数
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][nums.length]; // 建桶 10个桶分别对应0-9 每个桶中要存的数不知道有几个，初始化时用num.length
        int[] bucketElementCounts = new int[10]; // 初始化一个数组，里面存每一个桶中数字的个数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < nums.length; j++) {
                int digitOfElement = nums[j] / n % 10; // 取出个位、十位、百位·····
                // 存进桶里
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = nums[j];
                bucketElementCounts[digitOfElement]++; // 统计数字+1 （记录桶里有几个数字）
            }
            // 取数
            int index = 0;
            // 遍历每一桶
            for (int k = 0; k < 10; k++) {
                // 如果桶中有数据 取出放入原数组
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        nums[index] = bucket[k][i];
                        index++;
                    }
                }
                // 取完数字后 将计数器归零 方便下一轮计算
                bucketElementCounts[k] = 0;
            }
        }
    }
}
