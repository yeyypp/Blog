# Sort

- SelectionSort
稳定 n2  
The running time is insensitive to input.
It takes as long to run on an ordered array equal
as it does to run on an randomly-ordered array.
```
public static void Selection(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }


```

- InsertionSort
>Unlike selection sort, the running time of insertion sort depends on
>the initial order of the items in the input. It would be much faster
>than sorting a randomly-ordered array.
```
public static void Insertion(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }
```
    

- QuickSort

不稳定 nlogn
```
Java

public static void QuickSort(int[] nums) {
        partition(nums, 0, nums.length - 1);
    }

    private static void partition(int[] nums, int left, int right) {
        if (left < right) {
            int pivot = findPivot(nums, left, right);
            partition(nums, left, pivot - 1);
            partition(nums, pivot + 1, right);
        }
    }

    private static int findPivot(int[] nums, int left, int right) {
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
```

- MergeSort

归并 nlogn 稳定
```
Java

public static void MergeSort(int[] nums) {
        split(nums, 0, nums.length - 1);
    }

    private static void split(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            split(nums, left, mid);
            split(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] tem = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                tem[k++] = nums[i++];
            } else {
                tem[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            tem[k++] = nums[i++];
        }

        while (j <= right) {
            tem[k++] = nums[j++];
        }

        System.arraycopy(tem, 0, nums, left, tem.length);
    }
 ```

- 179 [Largest Number](https://leetcode.com/problems/largest-number/)
```
Java

/* 注意排序完，第一个字符串是0的情况 */

class Solution {
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);
            }
        });
        if (strings[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }
}
```

- 1122 [Relative Sort Array](https://leetcode.com/problems/relative-sort-array/)
```
Java

class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] tem = new int[1001];
        for (int num : arr1) {
            tem[num]++;
        }
        
        int[] ans = new int[arr1.length];
        int index = 0;
        
        for (int num : arr2) {
            while (tem[num]-- > 0) {
                ans[index++] = num;
            }
        }
        
        for (int i = 0; i < tem.length; i++) {
            while (tem[i]-- > 0) {
                ans[index++] = i;
            }
        }
        
        return ans;
    }
}
```