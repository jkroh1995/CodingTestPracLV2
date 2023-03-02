import java.util.*;
import java.util.stream.Collectors;

class Solution {

    Set<Integer> maxSet = new HashSet<>();

    public int solution(int k, int[][] dungeons) {
        List<int[]> dungeonList = new ArrayList<>(Arrays.asList(dungeons));
        calculateMax(k, dungeonList, 0);
        List<Integer> sortedSet = new ArrayList<>(maxSet);
        Collections.sort(sortedSet);
        return sortedSet.get(sortedSet.size() - 1);
    }

    private void calculateMax(int k, List<int[]> dungeons, int max) {
        if (k <= 0 || dungeons.size()==0) {
            maxSet.add(max);
            return;
        }
        for (int i = 0; i < dungeons.size(); i++) {
            if (k < dungeons.get(i)[0]) {
                maxSet.add(max);
                continue;
            }
            int newK = k - dungeons.get(i)[1]; // 새롭게 넣어줄 K 생성
            List<int[]> copiedDungeon = new ArrayList<>(dungeons); // 새롭게 넣어줄 List 생성
            copiedDungeon.remove(i); // 이번에 방문한 던전은 제거
            int newMax = max + 1; // 새롭게 넣어줄 max 생성
            calculateMax(newK, copiedDungeon, newMax);
        }
    }
}
