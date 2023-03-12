import java.util.*;

public class A2_Q4 {

    static double swapCounter = 0;

	public static double swaps(int[] passengers) {
		if (passengers.length <= 1) {
			return 0;
		}
		
		int mid = passengers.length / 2;
		int[] left = Arrays.copyOfRange(passengers, 0, mid);
		int[] right = Arrays.copyOfRange(passengers, mid, passengers.length);

		double swaps = swaps(left) + swaps(right);

		int i = 0, j = 0, k = 0;
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				passengers[k] = left[i];
				i++;
			} else {
				passengers[k] = right[j];
				j++;
				swaps += left.length - i;
			}
			k++;
		}

		while (i < left.length) {
			passengers[k] = left[i];
			i++;
			k++;
		}

		while (j < right.length) {
			passengers[k] = right[j];
			j++;
			k++;
		}

		return swaps;
	}


    public static void mergeSort(int[] passengers, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(passengers, left, mid);
            mergeSort(passengers, mid + 1, right);
            merge(passengers, left, mid, right);
        }
    }

    public static void merge(int[] passengers, int left, int mid, int right) {
        int[] temp = new int[passengers.length];
        for (int i = left; i <= right; i++) {
            temp[i] = passengers[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                passengers[k] = temp[i];
                i++;
            } else {
                passengers[k] = temp[j];
                j++;
                swapCounter += mid - i + 1;
            }
            k++;
        }
        while (i <= mid) {
            passengers[k] = temp[i];
            i++;
            k++;
        }
    }
}