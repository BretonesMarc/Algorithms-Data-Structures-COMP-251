import java.util.*;

public class A1_Q3 {

    public static int elements(int[] sizes) {
        int n = sizes.length;
        Set<Integer> set = new HashSet<>();
        int maxCount = 0, i = 0, j = 0;
        while (j < n) {
            if (set.contains(sizes[j])) {
                set.remove(sizes[i]);
                i++;
            } else {
                set.add(sizes[j]);
                maxCount = Math.max(maxCount, set.size());
                j++;
            }
        }
        return maxCount;
    }
}