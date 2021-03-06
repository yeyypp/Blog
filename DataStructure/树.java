public class Main {
    /**
     * n为节点总数
     * n0为度为0的节点树及叶子节点树
     * n1为度为1的节点数
     * n2为度为2的节点数
     * 则有：
     *      n = n0 + n1 + n2
     *      n0 = n2 + 1
     * 如果高度为h，则最多有 2^h - 1 个节点，第i层最多有2^(i - 1)个节点
     *
     * 几种常用的树
     * 完全二叉树：只有最下面两层的节点的度数小于2，其余各层的度数都等于2，并且最下面一层的节点都集中在最左边的若干位置上
     * 二叉搜索树：左子树所有节点小于根节点，右子树大于，且左右子树均为二叉搜索树，一般来说给出先序和中序或者中序和后序，可以决定一棵树。
     *            一般的查找时间为logN，N为节点总数，logN即为树的高度H。但当出现特殊情况时，查询时间可能为N。
     *            查找：比较节点的值大的向右，小的向左
     *            插入：比较节点的值，与查找类似，找到父节点，然后在父节点下插入，肯定是叶子节点
     * 平衡二叉搜索树：一般为avl树和红黑树，通过旋转来保持平衡，及左右子树高度小于等于1
     *                  红黑树的平衡要求没有avl树的要求严格，所以实现起来相对容易
     * B树：一般常用的为B+树，作为数据库存储结构，B树是一种平衡树，且每个节点包含一定数量的键，每个除叶子节点，都有最少两个子节点
     * 前缀树(Trie树)：又称字典树一个节点的所有子孙都有相同的前缀也就是这个节点对应的字符串
     */



}