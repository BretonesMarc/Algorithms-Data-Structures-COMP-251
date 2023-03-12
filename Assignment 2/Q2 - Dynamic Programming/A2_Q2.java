import java.util.*;

public class A2_Q2 {

    public static int change(int[] denominations) {
        int n = denominations.length;
        int maxSum = denominations[n - 1] + denominations[n - 2];
        int[] dp = new int[maxSum + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= maxSum; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= denominations[j]) {
                    dp[i] = Math.min(dp[i], dp[i - denominations[j]] + 1);
                }
            }
        }
        for (int i = 1; i <= maxSum; i++) {
            int greedyCount = 0;
            int amount = i;
            for (int j = n - 1; j >= 0; j--) {
                greedyCount += amount / denominations[j];
                amount %= denominations[j];
            }
            if (greedyCount != dp[i]) {
                return i;
            }
        }
        return -1;
    }

}
