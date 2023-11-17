class Solution {
    public long solution(int w, int h) {
        long answer = (long) w * (long) h;

        for (int i = 1; i <= w; i++) {
            long currentY = h - (long) h * (i - 1) / w;
            long nextY = h - (long) h * i / w;
            if ((h * i) % w != 0) {
                nextY--;
            }

            long emptyBox = currentY - nextY;

            answer -= emptyBox;
        }

        return answer;
    }
}
