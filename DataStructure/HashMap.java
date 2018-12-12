import java.util.Iterator;
import java.util.Map;

/**
 * (1) HashMap是非线程安全的，key，value均可以为null
 * 当两个线程同时put两个key，而刚好经过计算后key的hash值定位到数组中同一位置，此时一个线程在准备put时暂停，另一个
 * 线程完成put，阻塞的线程再次运行时，完成put操作，会导致覆盖掉另一个key。
 *
 * resize方法中会造成死循环
 *
 * (2) 内部实现为Entry俩链表数组，每一个Entry是一个key-value对
 *
 */

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 5);
        map.containsKey(2);
        map.containsValue(5);

        Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Integer, Integer> entry = iterator.next();
            Integer key = entry.getKey();
            Integer value = entry.getValue();
        }

        Iterator<Integer> iterator2 = map.keySet().iterator();
        while (iterator2.hasNext()) {
            Integer key = iterator2.next();
            Integer value = map.get(key);
        }
    }
}

