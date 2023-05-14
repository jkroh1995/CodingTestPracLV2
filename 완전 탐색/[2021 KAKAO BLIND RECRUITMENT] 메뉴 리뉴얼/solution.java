import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        Set<String> answerList = new HashSet<>();
        for (int count : course) {
            Map<String, Integer> orderCount = new HashMap<>();
            makeOrderCount(orders, count, orderCount);

            int min = getMinCount(orderCount);
            makeAnswerList(answerList, orderCount, min);
        }
        
        String [] answer = answerList.toArray(new String[0]);
        Arrays.sort(answer);

        return answer;
    }

    private void makeAnswerList(Set<String> answerList, Map<String, Integer> orderCount, int min) {
        for(String order : orderCount.keySet()){
            if(orderCount.get(order) == min){
                answerList.add(order);
            }
        }
    }

    private int getMinCount(Map<String, Integer> orderCount) {
        int min = 2;

        for (String order : orderCount.keySet()) {
            if (orderCount.get(order) >= min) {
                min = orderCount.get(order);
            }
        }
        return min;
    }

    private void makeOrderCount(String[] orders, int count, Map<String, Integer> orderCount) {
        for (String order : orders) {
            String[] orderArr = order.split("");
            Arrays.sort(orderArr);
            List<String> orderSortedList = new ArrayList<>(List.of(orderArr));
            makeOrderCount("", orderSortedList, orderCount, count, 0);
        }
    }

    private void makeOrderCount(String goal, List<String> orderSortedList, Map<String, Integer> orderCount, int count, int index) {
        if (goal.length() == count) {
            orderCount.put(goal, orderCount.getOrDefault(goal, 0) + 1);
            return;
        }
        if (orderSortedList.isEmpty()) {
            return;
        }

        for (int i = index; i < orderSortedList.size(); i++) {
            String nextGoal = goal + orderSortedList.get(i);
            makeOrderCount(nextGoal, orderSortedList, orderCount, count, i + 1);
        }
    }
}
