public class ComparableComparator {
    class Item implements Comparable<Item> {
        private String name;

        public Item(String name) {
            this.name = name;
        }

        /**
         * return -1 代表第一个元素放在前面
         * @param item
         * @return
         */
        @Override
        public int compareTo(Item item) {
            if (this.name > item.name) {
                return 1;
            }
            if (this.name < item.name) {
                return -1;
            }
            if (this.name.equals(item.name)) {
                return 0;
            }
        }
    }



    class ItemComparator implements Comparator<Item> {
        public int compare(Item i1, Item i2) {
            if (i1.name > i2.name) {
                return 1;
            }
            if (i1.name < i2.name) {
                return -1;
            }
            if (i1.name.equals(i2.name)) {
                return 0;
            }
        }
    }

    /**
     * Collections.sort(list, new Comparator() {
     * @Override
     * public int compare(Item i1, Item i2) {
     *  return i2 - i1 逆序
     *  return i1 - i2 正序
     * }};
     */
}