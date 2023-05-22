import java.util.Arrays;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);

        int index = 0;
        for (String[] place : places) {
            String[][] room = makeRoom(place);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (room[i][j].equals("P")) {
                        getAnswer(answer, room, i, j, index);
                        if (answer[index] == 0) {
                            break;
                        }
                    }
                }
            }
            index++;
        }

        return answer;
    }

    private void getAnswer(int[] answer, String[][] room, int i, int j, int index) {
        if (nearPerson(room, i, j)) {
            answer[index] = 0;
        }
    }

    private boolean nearPerson(String[][] room, int i, int j) {
        if (i == 0) {
            if (j == 0) {
                return right(room, i, j) || below(room, i, j) || rightBelow(room, i, j);
            }
            if (j == 4) {
                return left(room, i, j) || below(room, i, j) || leftBelow(room, i, j);
            }
            return right(room, i, j) || left(room, i, j) || below(room, i, j) || rightBelow(room, i, j) || leftBelow(room, i, j);
        }
        if (i == 4) {
            if (j == 0) {
                return right(room, i, j) || upper(room, i, j) || rightUpper(room, i, j);
            }
            if (j == 4) {
                return left(room, i, j) || upper(room, i, j) || leftUpper(room, i, j);
            }
            return right(room, i, j) || left(room, i, j) || upper(room, i, j) || rightUpper(room, i, j) || leftUpper(room, i, j);
        }
        if (j == 0) {
            return upper(room, i, j) || below(room, i, j) || rightUpper(room, i, j) || right(room, i, j) || rightBelow(room, i, j);
        }
        if (j == 4) {
            return upper(room, i, j) || below(room, i, j) || leftUpper(room, i, j) || left(room, i, j) || leftBelow(room, i, j);
        }
        return leftUpper(room, i, j) || upper(room, i, j) || rightUpper(room, i, j) || left(room, i, j) || right(room, i, j) || leftBelow(room, i, j) || below(room, i, j) || rightBelow(room, i, j);
    }

    private boolean right(String[][] room, int i, int j) {
        if (j + 2 < 5) {
            return room[i][j + 1].equals("P") || (room[i][j + 2].equals("P") && !room[i][j + 1].equals("X"));
        }
        return room[i][j + 1].equals("P");
    }

    private boolean left(String[][] room, int i, int j) {
        if (j - 2 >= 0) {
            return room[i][j - 1].equals("P") || (room[i][j - 2].equals("P") && !room[i][j - 1].equals("X"));
        }
        return room[i][j - 1].equals("P");
    }

    private boolean upper(String[][] room, int i, int j) {
        if (i - 2 >= 0) {
            return room[i - 1][j].equals("P") || (room[i - 2][j].equals("P") && !room[i - 1][j].equals("X"));
        }
        return room[i - 1][j].equals("P");
    }

    private boolean below(String[][] room, int i, int j) {
        if (i + 2 < 5) {
            return room[i + 1][j].equals("P") || (room[i + 2][j].equals("P") && !room[i + 1][j].equals("X"));
        }
        return room[i + 1][j].equals("P");
    }

    private boolean rightUpper(String[][] room, int i, int j) {
        return room[i - 1][j + 1].equals("P") && (!room[i - 1][j].equals("X") || !room[i][j + 1].equals("X"));
    }

    private boolean leftUpper(String[][] room, int i, int j) {
        return room[i - 1][j - 1].equals("P") && (!room[i - 1][j].equals("X") || !room[i][j - 1].equals("X"));
    }

    private boolean rightBelow(String[][] room, int i, int j) {
        return room[i + 1][j + 1].equals("P") && (!room[i + 1][j].equals("X") || !room[i][j + 1].equals("X"));
    }

    private boolean leftBelow(String[][] room, int i, int j) {
        return room[i + 1][j - 1].equals("P") && (!room[i + 1][j].equals("X") || !room[i][j - 1].equals("X"));
    }

    private String[][] makeRoom(String[] place) {
        String[][] room = new String[5][5];
        for (int i = 0; i < room.length; i++) {
            room[i] = place[i].split("");
        }
        return room;
    }
}
