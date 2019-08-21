public class Main {
    /**
     * 稳定的排序算法
     * 冒泡 n^2, 插入 n^2,归并nlogn
     *
     * 不稳定
     * 堆排 nlogn, 快排 nlogn, 选择 n^2;
     *
     * 快排优化https://blog.csdn.net/insistGoGo/article/details/7785038
     *
     * 为什么用快排不用堆排
     * 因为堆排中比较的都是不相邻的元素，而数据读取时，先从内存读到缓存，而一般读到缓存中会是需要的数据与其周围的数据，
     * 而堆排比较的是不相邻的元素，会造成多次从内存读取到缓存
     *
     *
     */

    /**
     * 75
     * 荷兰国旗问题
     * 利用三个指针
     * begin end cur
     * when cur == 0, swap begin cur, begin++ cur++
     * when cur == 1, cur++
     * when cur == 2, swap end cur, end--
     */
    public static void threePartition(int[] nums) {
        int cur = 0, begin = 0, end = nums.length - 1;
        while (cur <= end) {
            if (nums[cur] == 0) {
                swap(nums, cur, begin);
                begin++;
                cur++;
            } else if (nums[cur] == 1) {
                cur++;
            } else if (nums[cur] == 2) {
                swap(nums, cur, end);
                end--;
            }
        }
    }


    /**
     * 179 最大数
     * 比如 2 10 能组成的最大数为210
     * 比如 9 5 30 3 34 能组成的最大数为9533430
     * 先将int转为String， 再new一个Comparator使
     * String a = o1 + o2
     * String b = o2 + o1
     * 比如 a = 210 b = 102
     * 然后利用b.compareTo(a)将组合后比较大的开头放到前边
     */

    class Solution {
        public String largestNumber(int[] nums) {
            List<String> list = new ArrayList<>(nums.length);
            for (int i : nums) {
                list.add(String.valueOf(i));
            }
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String a = o1 + o2;
                    String b = o2 + o1;
                    return b.compareTo(a);
                }
            });
            if (list.get(0).equals("0")) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s);
            }
            return sb.toString();
        }
    }

    /**
     * 324 摆动排序II
     * 想把0，1，2，3对应到1，3，5怎么做
     * (1 + 2 * index) % n n为数组长度
     * 但如果想把剩下一半对应2，4，6
     * (1 + 2 * index) % (n|1)
     * n|1 if n = 6 for example 110 | 1 = 111 = 7
     *     if n = 7 for example 111 | 1 = 111 = 7
     *
     *     奇数%奇数还是奇数
     *
     *     注意使用findKth时，参数target需要转换为 target = nums.length - target使用
     *
     *     时间N，空间1
     */

    class Solution {
        public void wiggleSort(int[] nums) {
            int length = nums.length;
            int mid = findKthLargest(nums, (nums.length + 1) / 2);
            int left = 0, right = nums.length - 1, cur = 0;
            while (cur <= right) {
                if (nums[mapIndex(cur, length)] > mid) {
                    swap(nums, mapIndex(left, length), mapIndex(cur, length));
                    left++;
                    cur++;
                } else if (nums[mapIndex(cur, length)] == mid) {
                    cur++;
                } else {
                    swap(nums, mapIndex(cur, length), mapIndex(right, length));
                    right--;
                }
            }
        }

        private int mapIndex(int index, int length) {
            return (1 + 2 * index) % (length | 1);
        }

        private void swap(int[] nums, int i, int j) {
            int tem = nums[i];
            nums[i] = nums[j];
            nums[j] = tem;
        }

        private int findKthLargest(int[] nums, int k) {
            int target = nums.length - k;
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int pivot = findPivot(nums, left, right);
                if (pivot == target) {
                    break;
                }
                if (pivot < target) {
                    left = pivot + 1;
                } else {
                    right = pivot - 1;
                }
            }
            return nums[target];
        }



        private int findPivot(int[] nums, int left, int right) {
            int key = nums[left];
            while (left < right) {
                while (left < right && nums[right] >= key) {
                    right--;
                }
                nums[left] = nums[right];
                while (left < right && nums[left] <= key) {
                    left++;
                }
                nums[right] = nums[left];
            }
            nums[left] = key;
            return left;
        }
    }



    /**
     * Binary Search
     */

    public static int binarySearch(int[] nums, int target) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 快排
     * 不稳定
     * nlogn
     */

    public void QuickSort(int[] nums) {
        split(nums, 0, nums.length - 1);
    }

    private void split(int[] nums, int low, int high) {
        //注意这里必须是low > high不能是 =;
        if (low > high) {
            return;
        }
        int pivot = findPivot(nums, low, high);
        split(nums, low, pivot - 1);
        split(nums, pivot + 1, high);
    }

    private int findPivot(int[] nums, int low, int high) {

        int key = nums[low];
        while (low < high) {
            //注意这里的大于等于和小于等于,不然会进入死循环
            while (low < high && key <= nums[high]) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && key >= nums[low]) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = key;
        return low;
    }



    /**
     * 归并
     * 稳定
     * nlogn
     */

    public static void mergeSort(int[] nums) {
        split(nums, 0, nums.length - 1);
    }

    private static void split(int[] nums, int L, int R) {
        // 当位置相等时，说明只有一个数
        if (L == R) {
            return;
        }
        int M = (L + R) / 2;
        //拆分左右,注意,此处必须是L, M
        split(nums, L, M);
        split(nums, M + 1, R);
        //合并
        merge(nums, L, M, R);
    }

    private static void merge(int[] nums, int L, int M, int R) {
        // 创建一个临时数组
        int[] tem = new int[R - L + 1];
        //左指针
        int i = L;
        //右指针
        int j = M + 1;
        //新数组坐标
        int k = 0;

        while (i <= M && j <= R) {
            if (nums[i] < nums[j]) {
                tem[k] = nums[i];
                k++;
                i++;
            } else {
                tem[k] = nums[j];
                k++;
                j++;
            }
        }

        //若此时左边还有剩的，则放进tem中
        while (i <= M) {
            tem[k] = nums[i];
            k++;
            i++;
        }

        //右边有剩的
        while (j <= R) {
            tem[k] = nums[j];
            k++;
            j++;
        }

        //把临时数组中的元素覆盖原数组
        for (int a = 0; a < tem.length; a++) {
            nums[a + L] = tem[a];
        }
    }

    /**
     * 堆排
     * nlogn
     * 不稳定
     * 建堆复杂度n
     * 在top k 问题中，求最小的k个值，用最大堆，反之最小堆
     * （也可以用类似快排的方法求）
     * 例如：求最小k个数
     * 先设一个k大小的数组存放结果，然后将元素依次放进数组中，并堆化
     * 当元素小于堆顶时，替换堆顶元素，并堆化
     * 最后数组中元素为所求元素
     */

    private static void heapHelper(int[] nums, int node, int length) {
        int leftNode = (node << 1) + 1;
        int rightNode = leftNode + 1;

        //如果左节点已经大于长度限制了，则返回
        if (leftNode > length) {
            return;
        }

        //先设最大节点为左节点
        int maxNode = leftNode;
        //如果右节点在长度里，且值大于左节点
        if (rightNode <= length && nums[rightNode] > nums[maxNode]) {
            maxNode = rightNode;
        }

        // 将左右节点中最大的，与根比较
        if (nums[maxNode] > nums[node]) {
            swap(nums, maxNode, node);
            heapHelper(nums, maxNode, length);
        }
    }

    public static void HeapSort(int[] nums) {
        //从第一个非叶子节点开始，构造最大堆
        int start = (nums.length - 2) / 2;
        for (int i = start; i >= 0; i--) {
            heapHelper(nums, i, nums.length - 1);
        }

        //然后依次交换最后一个元素与第一个元素，再使剩下的元素为最大堆
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapHelper(nums, 0, i - 1);
        }
    }
    }


}