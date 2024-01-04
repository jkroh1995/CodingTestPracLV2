import java.util.Arrays;
import java.util.regex.Pattern;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, (file1, file2) -> {
            String firstHead = "";
            String firstNumber = "";
            String secondHead = "";
            String secondNumber = "";

            boolean getFirstNumber = false;
            String pattern = "^[0-9]*$";
            for (String word : file1.split("")) {
                if(getFirstNumber && !Pattern.matches(pattern, word)){
                    break;
                }
                if (!getFirstNumber && !Pattern.matches(pattern, word)) {
                    firstHead += word;
                    continue;
                }
                if (Pattern.matches(pattern, word)) {
                    getFirstNumber = true;
                    firstNumber += word;
                }
            }

            boolean getSecondNumber = false;
            for (String word : file2.split("")) {
                if(getSecondNumber && !Pattern.matches(pattern,word)){
                    break;
                }
                if (!getSecondNumber && !Pattern.matches(pattern, word)) {
                    secondHead += word;
                    continue;
                }
                if (Pattern.matches(pattern, word)) {
                    getSecondNumber = true;
                    secondNumber += word;
                }
            }

            firstHead = firstHead.toLowerCase();
            secondHead = secondHead.toLowerCase();

            int firstNumberValue = Integer.parseInt(firstNumber);
            int secondNumberValue = Integer.parseInt(secondNumber);

            if(!firstHead.equals(secondHead)){
                return firstHead.compareTo(secondHead);
            }

            return firstNumberValue - secondNumberValue;
        });
        return files;
    }
}
