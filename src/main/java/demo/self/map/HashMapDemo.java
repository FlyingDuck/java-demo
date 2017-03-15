package demo.self.map;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by dongsj on 2017/3/14.
 */
public class HashMapDemo {

    private void mapInit() {
        Map<Integer, String> map = null;

        map = new HashMap<>(3);
        map = new HashMap<>(4);
        map = new HashMap<>(11);
    }


    private void mapPut() {
        Map<String, String> map = null;

        // 初始化大小设置为 3，按照HashMap的计算方式 threshold 为 4
        map = new HashMap<>(3);
        for (int count=1; count <= 5; count++) {
            if (5 == count) {
                map.put("Key-" + count, "Value-" + count);
            } else {
                map.put("Key-" + count, "Value-" + count);
            }
        }


    }




    public static void main(String[] args) {
        System.out.println("Learning HashMap");
        HashMapDemo demo = new HashMapDemo();

        // demo.mapInit();
        demo.mapPut();




    }


}
