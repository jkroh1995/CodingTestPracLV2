import java.util.ArrayList;
import java.util.List;

class Solution {
    List<String> alphabet = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G", "H"
            , "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"));
    List<Integer>answers;
    int index = 0;

    public int[] solution(String msg) {
        int[] answer = {};

        List<String>input = new ArrayList<>(List.of(msg.split("")));
        answers = new ArrayList<>();

        while(!input.isEmpty()){
            String word = input.remove(0);

            makeAnswers(input, word);
        }
        
        answer = new int[answers.size()];
        for(int i=0;i<answers.size();i++){
            answer[i]=answers.get(i);
        }

        return answer;
    }

    private void makeAnswers(List<String> input, String word) {
        if(input.isEmpty()){
            answers.add(alphabet.indexOf(word)+1);
            return;
        }
        String next = word + input.get(0);
        if(alphabet.contains(next)){
            input.remove(0);
            makeWord(input, next);
            return;
        }
        answers.add(alphabet.indexOf(word)+1);
        alphabet.add(next);
    }
}
