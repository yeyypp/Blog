public class SortingAlgorithms {

    /**
     * worst time n^2;
     * best n
     * space 1
     * @param a
     */

    public void Insert(int[] a) {
        int temp = 0;
        int j = 0;
        for (int i = 1; i < a.length; i++) {
            temp = a[i];
            for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
    }

    /**
     * worst time nlogn
     *
     * @param a
     */

    public void QuickSort(int[] a) {
        QuickHelper(a, 0, a.length - 1);
    }

    private void QuickHelper(int[] a, int low, int high) {
        int first = low;
        int last = high;
        int key = a[low];

        while (first < last) {
            while (first < last) {
                if (a[last] >= key) {
                    last--;
                }
            }
            a[first] = a[last];
            while (first < last) {
                if (a[first] <= key) {
                    first++;
                }
            }
            a[last] = a[first];
        }
        a[first] = key;
        QuickHelper(a, low, first - 1);
        QuickHelper(a, first + 1, high);
    }

    /**
     * non-stable
     *
     * @param a
     */

    public void Shell(int[] a) {
        int d = a.length / 2;
        while (d >= 1) {
            ShellHelper(a, d);
            d /= 2;
        }
    }

    private void ShellHelper(int[] a, int d) {
        int temp = 0;
        int j = 0;
        for (int i = 1; i < a.length; i++) {
            temp = a[i];
            for (j = i - d; j >= 0 && a[j] > temp; j -= d) {
                a[j + d] = a[j];
            }
            a[j + d] = temp;
        }
    }


}