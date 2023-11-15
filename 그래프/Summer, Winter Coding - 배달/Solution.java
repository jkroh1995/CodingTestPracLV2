class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[][] village = new int[N][N];

        // 각 노드 간 연결 거리를 최댓 값으로 설정 - 여러 경로로 연결되어 있을 수 있기 때문에 최소 경로를 구하기 위한 수단
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i !=j) {
                    village[i][j] = K + 500000;
                }
            }
        }

        for (int[] eachRoad : road) {
            if(village[eachRoad[0] - 1][eachRoad[1] - 1] > eachRoad[2]){
                village[eachRoad[0] - 1][eachRoad[1] - 1] = eachRoad[2];
                village[eachRoad[1] - 1][eachRoad[0] - 1] = eachRoad[2];
            }
        }

        boolean[] visited = new boolean[N];
        visited[0] = true;
        int[] dijkstra = village[0];

        // 다익스트라 알고리즘
        for (int i = 1; i < N; i++) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int j = 1; j < N; j++) {
                if (!visited[j] && dijkstra[j] < min) {
                    min = dijkstra[j];
                    index = j;
                }
            }
            visited[index] = true;

            for(int k =0;k<N;k++){
                if(dijkstra[k] > village[index][k] + dijkstra[index]){
                    dijkstra[k] = village[index][k] + dijkstra[index];
                }
            }
        }

        for (int num : dijkstra) {
            if (num <= K) {
                answer++;
            }
        }
        return answer;
    }
}
