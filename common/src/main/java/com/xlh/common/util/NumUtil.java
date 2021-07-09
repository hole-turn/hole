package com.xlh.common.util;

import java.util.HashSet;
import java.util.Set;

public class NumUtil {

    /**
     * 32位无符号整数
     *
     * @param num
     * @return
     */
    public static int product32Num(short num) {
        int originNum = num & 0xffff;
        return originNum & 0xffff;
    }

    public static int num32() {
        return product32Num((short) getNumber());
    }


    public static void main(String[] args) {
        int a = product32Num((short) getNumber());
        System.out.print(a);
    }

    /**
     * 得到一个0-9的随机数
     */
    private static int getRandomNumber() {
        return (int) ((Math.random() * 100) % 10);
    }

    /**
     * 得到一个四位无重复数字的数
     */
    private static int getNumber() {
        Set<Integer> set = new HashSet<Integer>();
        while (true) {
            int a = getRandomNumber();
            //Set里面的元素是不重复的，如果重复是存不进去的。
            set.add(new Integer(a));
            if (set.size() > 3) {
                break;
            }
        }
        int index = (int) ((Math.random() * 100) % 4);
        if (index == 0) {
            index += 1;
        }
        Integer[] arr = new Integer[set.size()];
        set.toArray(arr);
        String s = "";
        //如果第一位是0，则随机和后面三位交换
        if (arr[0].intValue() == 0) {
            Integer temp = arr[0];
            arr[0] = arr[index];
            arr[index] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            s += arr[i].intValue();
        }
        return Integer.parseInt(s);
    }
}
