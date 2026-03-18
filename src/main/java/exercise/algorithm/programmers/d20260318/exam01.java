/**
 * 2022 KAKAO BLIND RECRUITMENT
 * 주차 요금 계산
 */
package exercise.algorithm.programmers.d20260318;

import java.util.*;
import java.util.stream.Collectors;

//올림 방식 a + b 의 올림 ? -> (a + b - 1) / b

public class exam01 {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(solution2(fees, records)));

        fees = new int[]{120, 0, 60, 591};
        records = new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"};
        System.out.println(Arrays.toString(solution2(fees, records)));

        fees = new int[]{1, 461, 1, 10};
        records = new String[]{"00:00 1234 IN"};
        System.out.println(Arrays.toString(solution2(fees, records)));
    }

    static int basicTime;
    static int basicFair;
    static int perTime;
    static int perFair;

    private static int calcDuration(String inTime, String time) {
        int h1 = Integer.parseInt(inTime.substring(0, 2));
        int h2 = Integer.parseInt(time.substring(0, 2));

        int m1 = Integer.parseInt(inTime.substring(3, 5));
        int m2 = Integer.parseInt(time.substring(3, 5));


        if (m2 >= m1) {
            return (h2 - h1) * 60 + m2 - m1;
        } else {
            return ((h2 - 1) - h1) * 60 + (m2 + 60) - m1;
        }
    }

    private static int calc(int totalDuration) {
        if (totalDuration <= basicTime) {
            return basicFair;
        }

        return (int) (basicFair + Math.ceil((double) (totalDuration - basicTime) / perTime) * perFair);
    }

    public static int[] solution2(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFair = fees[1];
        perTime = fees[2];
        perFair = fees[3];

        Map<String, String> map = new HashMap<>();
        Map<Integer, Integer> resultMap = new HashMap<>();

        for (String record : records) {
            String[] splitStr = record.split(" ");

            String time = splitStr[0];
            String carNum = splitStr[1];
            String inOut = splitStr[2];

            if ("IN".equals(inOut)) {
                map.put(carNum, time);
            } else if ("OUT".equals(inOut)) {
                String inTime = map.get(carNum);
                map.remove(carNum);
                resultMap.put(
                        Integer.parseInt(carNum)
                        , resultMap.getOrDefault(Integer.parseInt(carNum), 0) + calcDuration(inTime, time)
                );
            } else {
                return new int[0];
            }
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String carNum = entry.getKey();
            String inTime = entry.getValue();
            resultMap.put(
                    Integer.parseInt(carNum)
                    , resultMap.getOrDefault(Integer.parseInt(carNum), 0) + calcDuration(inTime, "23:59")
            );
        }

        // Stream을 한 번만 사용하여 정렬 -> 요금 계산 -> int 배열 변환을 한 번에 처리
        return resultMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .mapToInt(entry -> calc(entry.getValue()))
                .toArray();
    }
}
