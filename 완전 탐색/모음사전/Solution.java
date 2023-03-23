import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(String word) {
        int answer = 0;
        String [] words = new String[]{"A","E","I","O","U"};
        List<String>result = new ArrayList<>();
        dfs(answer, words, "", result);
        return result.indexOf(word)+1;
    }

    private void dfs(int answer, String[] words, String current, List<String> result) {
        if(current.length() == 5){
            return;
        }

        for(String word : words){
            String next = current+word;
            result.add(next);
            answer++;
            dfs(answer, words, next, result);
        }
    }
}
