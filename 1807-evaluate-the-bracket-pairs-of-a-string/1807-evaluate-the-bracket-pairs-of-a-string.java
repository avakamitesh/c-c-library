class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> kv : knowledge) {
            map.put(kv.get(0), kv.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean inBracket = false;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                inBracket = true;
                key.setLength(0);
            } else if (c == ')') {
                inBracket = false;
                String k = key.toString();
                result.append(map.getOrDefault(k, "?"));
            } else if (inBracket) {
                key.append(c);
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}