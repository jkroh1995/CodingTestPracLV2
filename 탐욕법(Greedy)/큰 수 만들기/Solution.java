class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        int index = 0;
        int length = number.length() - k;

        for (int i = 0; i < length; i++) {
            int max = 0;
            for (int j = index; j <= k + i; j++) {
                if(max < number.charAt(j) - '0'){
                    max = number.charAt(j) - '0';
                    index = j+1;
                }
            }
            answer.append(max);
        }
        return answer.toString();
    }
}
