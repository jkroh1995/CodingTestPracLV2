import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        Set<Set<Integer>> keySet = new HashSet<>();
        int size = relation[0].length;

        // 모든 키의 조합을 생성
        createCandidateKeySet(keySet, new HashSet<>(), size);

        // 최소성을 만족 시키기 위해 key가 적은 순서로 정렬을 위해 List로 변경
        List<Set<Integer>> keyList = new ArrayList<>(keySet);
        keyList.sort((Comparator.comparingInt(Set::size)));

        // 후보 키들의 조합을 담기 위한 Set
        Set<Set<Integer>> candidateKeySet = new HashSet<>();

        // 전체 키들을 순회
        for (Set<Integer> candidateKey : keyList) {
            boolean isAlreadyCandidateKey = false;

            // 현재까지 찾은 후보 키들을 순회
            for(Set<Integer> eachCandidateKey : candidateKeySet){
                // 이번에 알아보고자 하는 후보키가 이미 후보 키로 선정된 키를 포함하고 있다면(최소성을 만족시키지 못한다면) 이번 순서 생략
                if (candidateKey.containsAll(eachCandidateKey)) {
                    isAlreadyCandidateKey = true;
                    break;
                }
            }
            if(isAlreadyCandidateKey){
                continue;
            }

            // 이번 후보 키를 통해 검색되는 attribute의 집합
            Set<String> attributeSet = new HashSet<>();

            for(String [] attribute : relation){
                StringBuilder info = new StringBuilder();
                for(int key : candidateKey){
                    info.append(attribute[key]);
                }
                // 유일성을 만족시키지 못하면 종료
                if(attributeSet.contains(info.toString())){
                    break;
                }
                attributeSet.add(info.toString());
            }
            // 만일 구한 attribute 집합의 크기가 relation의 크기와 같다면 후보 키 set에 이번 키 추가
            if(attributeSet.size() == relation.length){
                candidateKeySet.add(candidateKey);
            }
        }

        return candidateKeySet.size();
    }

    private void createCandidateKeySet(Set<Set<Integer>> keySet, Set<Integer> objects, int size) {
        if (objects.size() == size) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (objects.contains(i)) {
                continue;
            }
            Set<Integer> nextSet = new HashSet<>(objects);
            nextSet.add(i);
            keySet.add(nextSet);
            createCandidateKeySet(keySet, nextSet, size);
        }
    }
}
