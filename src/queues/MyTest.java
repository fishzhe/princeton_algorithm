package queues;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by zheyu on 10/16/16.
 */
public class MyTest {
    @Test
    public void test() {
        System.out.println(0.0 == -0.0);
    }

    public boolean isIsomorphic(String s, String t) {
        if ((s == null && t == null) || (s.length() == 0 && t.length() == 0)) {
            return true;
        }

        Map<Character, Integer> sMap = new HashMap<Character, Integer>();
        Map<Character, Integer> tMap = new HashMap<Character, Integer>();

        // when initialize i, use Integer.
        for (int i = 0; i < s.length(); ++i) {
            char sChar = s.charAt(i), tChar = t.charAt(i);
            if (!sMap.containsKey(sChar)) {
                sMap.put(sChar, i);
            }
            if (!tMap.containsKey(tChar)) {
                tMap.put(tChar, i);
            }
            if (sMap.put(sChar, i).intValue() != tMap.put(tChar, i).intValue()) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test2() {
        Integer i = Integer.valueOf(1), j = Integer.valueOf(1);
        System.out.println(i == j) ;
    }

    @Test
    public void testRand() {
        int[] nums = {1, 2, 3, 4};
        Random random = ThreadLocalRandom.current();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = random.nextInt(i + 1);
            int tmp = nums[i];
            nums[i] = nums[index];
            nums[index] = tmp;
        }

        for (int i : nums) {
            System.out.print(i + "  ");
        }

    }
}
