class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        int edge = d;

        for(int i = 0 ;i<=d;i+=k){
            for(int j = edge ; j>=0; j--){
                if(Math.pow(j,2) + Math.pow(i,2) <= Math.pow(d,2)){
                    edge = j;
                    answer += j/k + 1; // k만큼 늘어나기 때문에 k로 나누고 0일 때 1을 더함
                    break;
                }
            }
        }
      
        return answer;
    }
}
