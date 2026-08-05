import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
    
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }
      
        Set<Integer> suspicious = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        suspicious.add(k);
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph.get(curr)) {
                if (!suspicious.contains(next)) {
                    suspicious.add(next);
                    queue.add(next);
                }
            }
        }
        
        
        boolean canRemove = true;
        for (int[] inv : invocations) {
            int a = inv[0], b = inv[1];
            if (!suspicious.contains(a) && suspicious.contains(b)) {
                canRemove = false;
                break;
            }
        }
        
        
        List<Integer> result = new ArrayList<>();
        if (!canRemove) {
            
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
        } else {
            
            for (int i = 0; i < n; i++) {
                if (!suspicious.contains(i)) {
                    result.add(i);
                }
            }
        }
        
        return result;
    }
}