import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Node> set = new HashSet<>();

    }

    public static boolean hasCycle(Set set, Node node) {
        if (node == null || node.next == null) {
            return false;
        }
        while (node.next != null) {
            if (!set.contains(node)) {
                set.add(node);
                node = node.next;
            } else {
                return true;
            }
        }
        return false;
    }
}