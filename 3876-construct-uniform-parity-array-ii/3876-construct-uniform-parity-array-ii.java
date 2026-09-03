import java.util.Arrays;

class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int[] arr = nums1.clone();
        Arrays.sort(arr);

        for (int target = 0; target <= 1; target++) {
            boolean seenOdd = false, seenEven = false;
            boolean ok = true;

            for (int x : arr) {
                int p = ((x % 2) + 2) % 2;
                boolean canA = (p == target);
                int neededParity = p ^ target;
                boolean canB = (neededParity == 1) ? seenOdd : seenEven;

                if (!canA && !canB) {
                    ok = false;
                    break;
                }

                if (p == 1) seenOdd = true;
                else seenEven = true;
            }

            if (ok) return true;
        }

        return false;
    }
}