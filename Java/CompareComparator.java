public class ComparableComparator {
    class Item implements Comparable<Item> {
        private String name;

        public Item(String name) {
            this.name = name;
        }

        /**
         * Item i1, Item i2
         * i2 为比较的那个元素
         * 返回-1 代表 i1放在前，返回1 代表 i1放在后
         * 当希望较大元素放在前边时，因为移动的是i1
         * 则 return i2 - i1
         *
         * 反之 return i1 - i2
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
     *  移动的是i1
     *  return i2 - i1 逆序
     *  return i1 - i2 正序
     * }};
     */
}