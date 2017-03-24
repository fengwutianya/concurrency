package ch03;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by xuan on 2017/3/23 0023.
 */
public class SkipLIstDemo {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, i);
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            System.out.println(entry);
        }
    }
}
