import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(int n) {
        if(n==1){
            return new int[]{1};
        }
        int[] answer = {};

        int [][] triangle = new int[n][n];

        makeTriangle(n, triangle, 1);

        List<Integer>list = new ArrayList<>();
        for(int i = 0 ;i<n;i++){
            for(int j=0;j<n;j++){
                if(triangle[i][j]!=0){
                    list.add(triangle[i][j]);
                }
            }
        }

        answer = list.stream()
                .mapToInt(num -> num)
                .toArray();

        return answer;
    }

    private void makeTriangle(int n, int[][] triangle, int start) {
        if(start>n/2){
            return;
        }

        for(int i = 2*(start-1); i< n-(start-1); i++){ // 가장 왼쪽 새로로 채움
            if(i==0){
                triangle[2*(start-1)][start-1]=1;
                continue;
            }
            if(triangle[i][start-1]!=0){
                return;
            }
            triangle[i][start-1]= triangle[i-1][start-1]+1;
        }
        
        for(int i = start; i< n-2*(start-1); i++){ //가장 아랫 줄 가로로 채움
            if(triangle[n -start][i]!=0){
                return;
            }
            triangle[n -start][i]= triangle[n -start][i-1]+1;
        }

        for(int i = n -2*start; i>=start; i--){ // 대각선 채움
            if(triangle[i+(start-1)][i]!=0){
                return;
            }
            triangle[i+(start-1)][i] = triangle[i+start][i+1]+1;
        }

        makeTriangle(n, triangle, start+1);
    }
}
