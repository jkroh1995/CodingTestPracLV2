import java.util.*;

class Solution {
    public int solution(int[] cards) {
        Set<Integer> collectedCardIndex = new HashSet<>();
        List<Integer> arrList = new ArrayList<>();
        
        for (int i = 0; i < cards.length; i++) {
            if (!collectedCardIndex.contains(i)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                collectedCardIndex.add(i);
                int next = cards[i] - 1;
                cards[i] = 0;
                
                while (cards[next] != 0 && !collectedCardIndex.contains(next)) {
                    list.add(next);
                    collectedCardIndex.add(next);
                    int prev = next;
                    next = cards[next] - 1;
                    cards[prev] = 0;
                }
                arrList.add(list.size());
            }
        }
        
        Collections.sort(arrList);
        if (arrList.size() == 1) {
            return 0;
        }
        return arrList.get(arrList.size() - 1) * arrList.get(arrList.size() - 2);
    }
}
