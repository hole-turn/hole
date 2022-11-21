package com.xlh.test;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author: xielinhao
 * @title: H
 * @projectName: hole
 * @description:
 * @date: 14:21 2022/10/8
 */
public class H {
    public static void main(String[] args) {
        isValid("{[]}");
    }

    public static boolean isValid(String s) {
        // 括号之间的对应规则
        HashMap<Character, Character> mappings = new HashMap<Character, Character>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (mappings.containsKey(chars[i])) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(chars[i])) {
                    return false;
                }
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }

}
