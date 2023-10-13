import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        // 특정 숫자의 짝꿍 숫자들을 담을 맵
        Map<Integer, Set<Integer>> partnerMap = new HashMap<>();
        
        // 모든 짝꿍 숫자들이 등장했던 횟수를 담을 맵
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int weight : weights) {
            // 짝꿍 숫자들을 담을 리스트 
            Set<Integer> list = new HashSet<>();
            for (int i = 2; i <= 4; i++) {
                for (int j = 2; j <= 4; j++) {
                    // 짝꿍이면
                    if ((weight * i) % j == 0) {
                        list.add((weight * i) / j);
                        countMap.put((weight * i) / j, 0);
                    }
                }
            }
            partnerMap.put(weight, list);
        }

        for(int weight : weights){
            Set<Integer>partners = partnerMap.get(weight);
            for(int partner : partners){
                answer+=countMap.get(partner);
            }
            countMap.put(weight, countMap.get(weight)+1);
        }
        return answer;
    }
}
