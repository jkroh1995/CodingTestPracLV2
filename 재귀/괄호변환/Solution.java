class Solution {
    public String solution(String p) {
        return recursive(p);
    }

    private String recursive(String p) {
        if (p.equals("")) {
            return "";
        }

        String[] arr = p.split("");
        int leftCount = 0;
        int rightCount = 0;
        int index = 0;
        String u = "";
        String v = "";
        boolean isCorrect = true;
        while (true) {
            if (index >= arr.length) {
                break;
            }
            if (leftCount > rightCount) {
                isCorrect = false;
            }
            if (leftCount == rightCount && leftCount != 0) {
                break;
            }
            String next = arr[index];
            u += next;
            index++;
            if (next.equals(")")) {
                leftCount++;
                continue;
            }
            rightCount++;
        }

        for (int i = index; i < arr.length; i++) {
            v += arr[i];
        }

        if (isCorrect) {
            return u + recursive(v);
        }
        u = u.substring(1, u.length() - 1);
        String nextU = "";
        String[] nextArr = u.split("");
        for(String word : nextArr){
            if(word.equals(")")){
                nextU+="(";
                continue;
            }
            if(word.equals("(")){
                nextU+=")";
            }
        }
        return "(" + recursive(v) + ")" + nextU;
    }
}
