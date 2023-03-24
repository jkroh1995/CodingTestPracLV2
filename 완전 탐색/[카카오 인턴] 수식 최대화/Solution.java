import java.util.*;

class Solution {
    public long solution(String expression) {
        Set<String> operators = new HashSet<>();

        for(String word : expression.split("")){
            if(word.equals("*") || word.equals("+") || word.equals("-")){
                operators.add(word);
            }
        }
        List<String>nums = new ArrayList<>();

        StringTokenizer stringTokenizer =new StringTokenizer(expression, "*+-", true);
        while(stringTokenizer.hasMoreTokens()){
            nums.add(stringTokenizer.nextToken());
        }

        List<Long>answerList = new ArrayList<>();

        makeAnswerList(answerList, operators, nums, "");
        Collections.sort(answerList);
        return answerList.get(answerList.size()-1);
    }

    private void makeAnswerList(List<Long> answerList, Set<String> operators, List<String> nums, String operator) {
        List<String>nextNums = new ArrayList<>(nums);
      
        for(int i=0;i<nextNums.size();i++){
            if(nextNums.get(i).equals(operator)){
                if(operator.equals("*")){
                    long num = Long.parseLong(nextNums.get(i-1))*Long.parseLong(nextNums.get(i+1));
                    nextNums.remove(i-1);
                    nextNums.remove(i-1);
                    nextNums.remove(i-1);
                    nextNums.add(i-1, String.valueOf(num));
                    i--;
                }
                if(operator.equals("+")){
                    long num = Long.parseLong(nextNums.get(i-1))+Long.parseLong(nextNums.get(i+1));
                    nextNums.remove(i-1);
                    nextNums.remove(i-1);
                    nextNums.remove(i-1);
                    nextNums.add(i-1, String.valueOf(num));
                    i--;
                }
                if(operator.equals("-")){
                    long num = Long.parseLong(nextNums.get(i-1))-Long.parseLong(nextNums.get(i+1));
                    nextNums.remove(i-1);
                    nextNums.remove(i-1);
                    nextNums.remove(i-1);
                    nextNums.add(i-1, String.valueOf(num));
                    i--;
                }
            }
        }
        if(operators.isEmpty()){
            answerList.add(Math.abs(Long.parseLong(nextNums.get(0))));
            return;
        }
        for(int i=0;i<operators.size();i++){
            List<String>tmp = new ArrayList<>(operators);
            String op = tmp.remove(i);
            Set<String>nextOperators = new HashSet<>(tmp);
            makeAnswerList(answerList, nextOperators, nextNums, op);
        }
    }
}
