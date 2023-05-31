import java.util.*;
import java.util.regex.Pattern;

class Number implements Comparable<Number> {

    String number;

    Number(String number) {
        this.number = number;
    }

    @Override
    public int compareTo(Number otherNumber) {
        if (this.length() == otherNumber.length()) {
            return otherNumber.integerValue() - this.integerValue();
        }
        int num1 = Integer.parseInt(this.number + otherNumber.number);
        int num2 = Integer.parseInt(otherNumber.number + this.number);
        return num2 - num1;
    }

    private int integerValue() {
        return Integer.parseInt(number);
    }

    private int length() {
        return number.length();
    }

    @Override
    public String toString() {
        return number;
    }
}

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        for (int i = 9; i >= 0; i--) {
            PriorityQueue<Number> queue = new PriorityQueue<>();
            for (int number : numbers) {
                String num = String.valueOf(number);
                if (num.charAt(0) - '0' == i) {
                    queue.add(new Number(num));
                }
            }

            while (!queue.isEmpty()) {
                answer += queue.poll().toString();
            }
        }
        String pattern = "^[0]*$";
        if (Pattern.matches(pattern, answer)) {
            return "0";
        }
        return answer;
    }
}
