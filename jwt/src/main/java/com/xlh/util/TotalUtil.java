package com.xlh.util;

/**
 * @author: xielinhao
 * @title: A
 * @projectName: hole
 * @description:
 * @date: 16:44 2021/11/14
 */

import java.util.*;

public class TotalUtil {

    public static void main(String[] args) {

        ArrayList<Integer> integers = splitTotal(559, 20, 3, 48);
        System.out.println(integers);

    }

    /**
     *
     * @param total
     *            总人数
     * @param splitCount
     *            个数
     * @param min
     *            最小人数
     * @param max
     *            最大人数
     */
    public static ArrayList<Integer> splitTotal(int total, int splitCount, int min, int max) {
        System.out.println("总人数：	" + total);
        System.out.println("个数：	" + splitCount);
        System.out.println("最小人数：	" + min);
        System.out.println("最大人数：	" + max);

        ArrayList<Integer> al = new ArrayList<Integer>();
        Random random = new Random();

        if ((splitCount & 1) == 1) {
            System.out.println("个数" + splitCount + "是奇数，单独生成");
            int num = 0;
            do {
                num = random.nextInt(max);
                // num = (total - num) % (splitCount / 2) + num; //
                // 将后面算法拆分时的余数加入到这个随机值中。
                System.out.println("单个的随机数值为：" + num);
            } while (num >= max || num <= min);

            total = 40000 - num;
            al.add(num);
        }
        int couples = splitCount >> 1;
        int perCoupleSum = total / couples;

        if ((splitCount & 1) == 1) {
            System.out.println("处理后剩余的人数为：" + total);
        }

        for (int i = 0; i < couples; i++) {
            Boolean finish = true;
            int num1 = 0;
            int num2 = 0;
            do {
                num1 = random.nextInt(max);
                num2 = perCoupleSum - num1;
                if (!al.contains(num1) && !al.contains(num2)) {
                    if (i == 0) {
                        num1 = (total - couples * perCoupleSum) + num1;
                    }
                }
            } while (num1 < min || num1 > max || num2 < min || num2 > max);
            al.add(num1);
            al.add(num2);
        }

        int check_num = 0;
        Integer.compare(1, 2);
        al.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });


        System.out.println(Arrays.toString(al.toArray()));

        for (int x : al) {
            check_num = check_num + x;
        }
        System.out.println("验证总和：" + check_num);
        return al;
    }

}