import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer>priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(int energy : enemy){
            n-=energy;
            priorityQueue.add(energy);
            if(cantClearStage(n)){
                if(remainPass(k)){
                    if(priorityQueue.isEmpty()){
                        break;
                    }
                    n+=priorityQueue.poll();
                    k--;
                }
                else{
                    break;
                }
            }
            answer++;
        }
        return answer;
    }

    private static boolean remainPass(int k) {
        return k > 0;
    }

    private static boolean cantClearStage(int n) {
        return n < 0;
    }
}
