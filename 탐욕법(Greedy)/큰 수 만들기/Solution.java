class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        int index = 0;
        int length = number.length() - k;

        // 만들어야 하는 길이만큼 반복
        for (int i = 0; i < length; i++) {
            int max = 0;
            for (int j = index; j <= k + i; j++) {
                // 9를 만나면 그대로 반복 종료
                if(number.charAt(j)-'0' == 9){
                    max = number.charAt(j) - '0';
                    // 시작점을 이번 회차에 뽑은 숫자의 다음으로 변경
                    index = j+1;
                    break;
                }
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
