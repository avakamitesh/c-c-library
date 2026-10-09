class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0; 

        int i = 0;
        int n = s.length();
        while (i < n) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2;               
                } else {
                    insertions++;         
                    i++;
                }

                if (open > 0) {
                    open--;               
                } else {
                    insertions++;        
                }
            }
        }

        insertions += open * 2;
        return insertions;
    }
}