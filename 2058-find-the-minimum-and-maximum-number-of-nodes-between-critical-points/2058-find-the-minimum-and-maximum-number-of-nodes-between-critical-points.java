/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;       
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int pos = 1;

        while (curr != null && curr.next != null) {
            boolean isCritical =
                    (curr.val > prev.val && curr.val > curr.next.val) ||
                    (curr.val < prev.val && curr.val < curr.next.val);

            if (isCritical) {
                if (first == -1) {
                    first = pos;
                } else {
                    minDistance = Math.min(minDistance, pos - prevCritical);

                    maxDistance = Math.max(maxDistance, pos - first);
                }

                prevCritical = pos;
            }

            prev = curr;
            curr = curr.next;
            pos++;
        }

        if (maxDistance == -1) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}