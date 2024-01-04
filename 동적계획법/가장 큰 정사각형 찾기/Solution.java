class Solution {
    public int solution(int[][] board) {
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                //해당 칸에 1이 적혀있을 때만 적용해야 함
                if(board[i][j] == 1){
                    int min = board[i-1][j-1];
                    if(board[i-1][j] < min){
                        min = board[i-1][j];
                    }
                    if(board[i][j-1] < min){
                        min = board[i][j-1];
                    }
                    board[i][j] = min + 1;
                }
            }
        }

        int max = 0;
        for(int i = 0;i< board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                if(board[i][j] > max){
                    max = board[i][j];
                }
            }
        }

        return max * max;
    }
}
