import java.util.*;

class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] saleRate = new int[]{10, 20, 30, 40};
        List<int[]> list = new ArrayList<>();
        int[] result = new int[emoticons.length];
        makeAnswerList(result, emoticons, saleRate, list, 0, users);
        int maxEmoticonPlus = 0;
        for (int[] each : list) {
            if (each[0] > maxEmoticonPlus) {
                maxEmoticonPlus = each[0];
            }
        }

        int maxPrice = 0;
        for (int[] each : list) {
            if (each[0] == maxEmoticonPlus && each[1] > maxPrice) {
                maxPrice = each[1];
            }
        }
        return new int[]{maxEmoticonPlus, maxPrice};
    }

    private void makeAnswerList(int[] result, int[] emoticons, int[] saleRate, List<int[]> list, int index, int[][] users) {
        if (index == emoticons.length) {
            int[] answer = new int[2];
            for (int[] user : users) {
                int userWantSaleRate = user[0];
                int userLimitPrice = user[1];
                int price = 0;
                boolean emoticonPlus = false;

                for (int i = 0; i < result.length; i++) {
                    if (result[i] >= userWantSaleRate) {
                        price += emoticons[i] / 100 * (100 - result[i]);
                    }
                    if (price >= userLimitPrice) {
                        emoticonPlus = true;
                        answer[0]++;
                        break;
                    }
                }

                if (!emoticonPlus) {
                    answer[1] += price;
                }
            }
            list.add(answer);
            return;
        }

        for (int sale : saleRate) {
            int[] next = Arrays.copyOf(result, result.length);
            next[index] = sale;
            makeAnswerList(next, emoticons, saleRate, list, index + 1, users);
        }
    }
}
