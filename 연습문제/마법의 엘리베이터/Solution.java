class Solution {
    public int solution(int storey) {
        int answer = 0;
        while(storey!=0){
            if(storey % 10 !=0) {
                answer += Math.min(10 - storey%10, storey%10);

                if(storey%10 == 5){ // 1의 자리가 5인 경우
                    if((storey%100 - storey%10)/10>=5){ // 10의 자리가 5 이상일 때는 올려주는게 더 낫다
                        storey += storey%10;
                    }
                    else{ // 5미만이면 내려주는게 낫다
                        storey -= storey%10;
                    }
                }
                else{
                    storey += Math.min(10 - storey%10, storey%10);
                }
            }
            storey/=10;
        }
        return answer;
    }
}
