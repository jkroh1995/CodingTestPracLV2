import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Time implements Comparable<Time> {
    int start;
    int end;

    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time o) {
        return this.start - o.start;
    }
}

class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<Time> priorityQueue = new PriorityQueue<>();
        for(String [] eachTime : book_time){
            int start = Integer.parseInt(eachTime[0].split(":")[0])*60 + Integer.parseInt(eachTime[0].split(":")[1]);
            int end = Integer.parseInt(eachTime[1].split(":")[0]) * 60 + Integer.parseInt(eachTime[1].split(":")[1]);
            priorityQueue.add(new Time(start, end));
        }

        List<Time> room = new ArrayList<>();
        room.add(priorityQueue.poll());

        while(!priorityQueue.isEmpty()){
            Time next = priorityQueue.poll();
            boolean needNewRoom = true;
            for(Time current : room){
                if(current.end + 10 <= next.start){
                    needNewRoom = false;
                    room.remove(current);
                    room.add(next);
                    break;
                }
            }
            if(needNewRoom){
                room.add(next);
            }
        }

        return room.size();
    }
}
