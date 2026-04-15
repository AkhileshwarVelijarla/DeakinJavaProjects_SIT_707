package sit707_week6;

public class LoopUtils {

    public static int sumUpTo(int n) {
        int sum = 0;
        int i = 1;

        while (i <= n) {
            sum += i;
            i++;
        }

        return sum;
    }

    public static int countEvenNumbersUpTo(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                count++;
            }
        }

        return count;
    }
}