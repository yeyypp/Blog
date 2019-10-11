# Strategy
- >Define a family of algorithms, encapsulate each one, and
make them interchangeable.
```
public interface Sorting {
    void sort(int[] nums);
}

public class MergeSort implements Sorting {

    public void sort(int[] nums) {
        split(nums, 0, nums.length - 1);
    }

    private void split(int[] nums, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            split(nums, low, mid);
            split(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }
    }

    private void merge(int[] nums, int low, int mid, int high) {
        int[] tem = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                tem[k++] = nums[i++];
            } else {
                tem[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            tem[k++] = nums[i++];
        }

        while (j <= high) {
            tem[k++] = nums[j++];
        }

        System.arraycopy(tem, 0, nums, 0, tem.length);
    }
}

public class SortingTools {
    private Sorting sorting;

    public SortingTools(Sorting sorting) {
        this.sorting = sorting;
    }

    public void sort(int[] nums) {
        sorting.sort(nums);
    }
}
```