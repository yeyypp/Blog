public class Main {
    /**
     * top k 问题
     * 最大的几个数用最小堆，反之最大堆
     * 每次在头节点插入
     * 如最大堆，头节点为当前最大值，当新值比头节点小时，更换头节点为新值，再重新heapify化，最后得到的就是最小的k个数
     * 或者用类似快排的方法，找到K
     */

    public class Main {
        public static void main(String[] args) {
            int[] data = new int[]{4, 6, 8,5,9};
            int[] topK = topK(data, 3);
            System.out.println(Arrays.toString(topK));
        }
        //topK方法
        public static int[] topK(int[] data, int k) {
            int[] topK = new int[k];

            for (int i = 0; i < k; i++) {
                topK[i] = data[i];
            }

            MinHeap minHeap = new MinHeap(topK);

            for (int i = k; i < data.length; i++) {
                int root = minHeap.getRoot();
                if (data[i] > root) {
                    minHeap.setRoot(data[i]);
                }
            }
            return topK;
        }
    }

    class MinHeap {
        private int[] data;

        public MinHeap(int[] data) {
            this.data = data;
            buildHeap(data);
        }
        //获取根节点
        public int getRoot() {
            return data[0];
        }
        //设置根节点
        public void setRoot(int a) {
            data[0] = a;
            heapify(data, 0, data.length - 1);
        }

        //建立堆
        private void buildHeap(int[] data) {
            int start = (data.length - 2) / 2;
            for (int i = start; i >= 0; i--) {
                heapify(data, i, data.length - 1);
            }
        }
        // 遍历节点，保证堆性质
        private void heapify(int[] data, int node, int length) {
            int left = (node << 1) + 1;
            int right = left + 1;
            if (left > length) {
                return;
            }
            int min = left;
            if (right <= length && data[right] < data[min]) {
                min = right;
            }
            if (data[min] < data[node]) {
                swap(data, min, node);
                heapify(data, min, length);
            }
        }
        //交换元素
        private void swap(int[] data, int i, int j) {
            int tem = data[i];
            data[i] = data[j];
            data[j] = tem;
        }
    }

}