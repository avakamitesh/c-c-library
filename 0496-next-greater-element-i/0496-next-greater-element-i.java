
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            Stack<Integer> stack = new Stack<>();

            for (int j = nums2.length - 1; j >= 0; j--) {
                while (!stack.isEmpty() && stack.peek() <= nums2[j]) {
                    stack.pop();
                }

                if (nums2[j] == nums1[i]) {
                    result[i] = stack.isEmpty() ? -1 : stack.peek();
                    break;
                }

                stack.push(nums2[j]);
            }
        }
        return result;
    }
}