class Solution {
    int[] answer = {0,0};
    public int[] solution(int[][] arr) {
        if(alreadyQuad(arr)){
            answer[arr[0][0]]++;
            return answer;
        }
        makeQuadTree(arr);
        return answer;
    }

    private boolean alreadyQuad(int [][] arr) {
        int index = arr[0][0] ;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                if(arr[i][j]!=index){
                    return false;
                }
            }
        }
        return true;
    }

    private void makeQuadTree(int[][] arr) {
        //한 조각이면 맞는 자리 숫자 올리고 종료
        if(arr.length==1){
            answer[arr[0][0]]++;
            return;
        }
        //한 덩어리를 4개로 나눔
        int [][] arr1 = makeQuadArray(arr, arr.length/2, arr.length/2);
        int [][] arr2 = makeQuadArray(arr, arr.length/2, arr.length);
        int [][] arr3 = makeQuadArray(arr, arr.length, arr.length/2);
        int [][] arr4 = makeQuadArray(arr, arr.length, arr.length);

        //각 4개가 적합한지 검사.
        extracted(arr1);
        extracted(arr2);
        extracted(arr3);
        extracted(arr4);
        //압축이 되면 answer에서 값 올리고 return
        //압축이 안되면 다시 넣음.
    }

    private void extracted(int[][] quadArr) {
        boolean isQuad = true;
        int firstIndex = quadArr[0][0];
        for(int i = 0; i< quadArr.length; i++){
            for(int j = 0; j< quadArr.length; j++){
                if(quadArr[i][j]!=firstIndex){
                    isQuad=false;
                    break;
                }
            }
        }
        if(isQuad){
            answer[firstIndex]++;
            return;
        }
        makeQuadTree(quadArr);
    }

    private int[][] makeQuadArray(int[][] arr, int row, int column) {
        int [][] newArr= new int[arr.length/2][arr.length/2];
        int newRow = 0;
        for(int i = row-arr.length/2;i<row;i++){
            int newColumn = 0;
            for(int j=column-arr.length/2;j<column;j++){
                    newArr[newRow][newColumn] = arr[i][j];
                    newColumn++;
            }
            newRow++;
        }
        return newArr;
    }

}
