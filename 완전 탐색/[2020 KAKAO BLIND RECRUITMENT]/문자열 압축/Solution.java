import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        int maxLength = s.length() / 2;
        int length = 1;
        while (length <= maxLength) {
            Queue<String> queue = new LinkedList<>();
            String tmp = "";
            makeQueue(s, length, queue);

            String first = queue.poll();
            int count = 1;
            while(!queue.isEmpty()){
                String next = queue.poll();
                if(first.equals(next)){
                    count++;
                    if(queue.isEmpty()){
                        tmp+= count + first;
                    }
                    continue;
                }
                if(count == 1){
                    tmp += first;
                    first = next;
                    if(queue.isEmpty()){
                        tmp+=first;
                    }
                    continue;
                }
                tmp+= count + first;
                first= next;
                if(queue.isEmpty()){
                    tmp+=first;
                }
                count=1;

            }
            if(tmp.length()<answer){
                answer = tmp.length();
            }
            length++;
        }
        return answer;
    }

    private void makeQueue(String s, int length, Queue<String> queue) {
        for (int i = 0; i + length < s.length(); i += length) {
            String departedString = s.substring(i, i + length);
            queue.add(departedString);
            if (i + length * 2 >= s.length()) {
                departedString = s.substring(i + length);
                queue.add(departedString);
            }
        }
    }
}
