
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode12
 * @Description TODO
 * @Author point
 * @Date 2023/10/22 22:41
 * @Version 1.0
 */
class Solution {
    String getstr(int n, char one, char two, char three) {
        if (n == 4) {
            return Character.toString(one) + two;
        }
        if (n == 9) {
            return Character.toString(one) + three;
        }
        if (n < 4) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                stringBuilder.append(one);
            }
            return stringBuilder.toString();
        }
        if (n < 9) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(two);
            for (int i = 0; i < n - 5; i++) {
                stringBuilder.append(one);
            }
            return stringBuilder.toString();
        }
        return "";
    }

    public String intToRoman(int num) {
        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');

        StringBuilder stringBuilder = new StringBuilder();
        int Mnumber = num / 1000;
        for (int i = 0; i < Mnumber; i++) {
            stringBuilder.append(map.get(1000));
        }
        num = num % 1000;
        int bai = num / 100;
        stringBuilder.append(getstr(bai, 'C', 'D', 'M'));
        num = num % 100;
        int shi = num / 10;
        stringBuilder.append(getstr(shi, 'X', 'L', 'C'));
        num = num % 10;
        stringBuilder.append(getstr(num, 'I', 'V', 'X'));
        return stringBuilder.toString();
    }
}

public class LeetCode12 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.intToRoman(9));
    }
}
