package com.mark.leetcode.stack;

import java.util.*;

/**
 * Stack
 *
 * @author Mark
 * @date 2021-07-25 13:39
 */
public class _Stack_String {
    // _394_decodeString
    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        char[] sArray = s.toCharArray();
        int num = 0;
        for (char c : sArray) {
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                strStack.push(String.valueOf(c));
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + Integer.parseInt(String.valueOf(c));
            } else if (c == '[') {
                strStack.push("[");
                numStack.push(num);
                num = 0;
            } else if (c == ']') {
                StringBuilder str = new StringBuilder();
                while (!"[".equals(strStack.peek())) {
                    str.insert(0, strStack.pop());
                }
                strStack.pop(); // pop "["
                int repeat = numStack.pop();
                StringBuilder repeatStr = new StringBuilder();
                while (repeat-- > 0) {
                    repeatStr.append(str);
                }
                strStack.push(repeatStr.toString());
            }
        }
        while (!strStack.isEmpty()) {
            res.insert(0, strStack.pop());
        }
        return res.toString();
    }

    // _726_countOfAtoms
    // 思路一、可以倒序求值，路子走远了，还不一定好写（DXX）
    // 思路二、面对对象，创建原子类
    public String countOfAtoms(String formula) {
        if (formula == null || "".equals(formula)) {
            return "";
        }
        // 用栈来保存括号里面的化学元素
        Deque<Atom> stack = new ArrayDeque<>();
        // 临时栈
        Deque<Atom> tmpStack = new ArrayDeque<>();
        // 存储每个化学原子的数量
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        char[] chs = formula.toCharArray();
        int len = chs.length;
        String k;
        int num, cnt, j, i = 0;
        // 记录括号的数量
        int bracketNo = 0;
        while (i < len) {
            if (chs[i] == '(') {
                bracketNo++;
                i++;
            } else if (chs[i] == ')') {
                // 读取后面的数字
                i++;
                num = 0;
                while (i < len && isNum(chs[i])) {
                    num = num * 10 + (chs[i] - '0');
                    i++;
                }
                num = num == 0 ? 1 : num;

                // 具有相同左括号编号的元素出栈
                while (!stack.isEmpty() && stack.peek().bracketNo == bracketNo) {
                    Atom o = stack.pop();
                    if (o.bracketNo > 1) {
                        // 结果继续进栈
                        o.bracketNo--;
                        o.cnt = o.cnt * num;
                        tmpStack.push(o);
                    } else {
                        cnt = treeMap.getOrDefault(o.name, 0);
                        treeMap.put(o.name, cnt + o.cnt * num);
                    }
                }

                while (!tmpStack.isEmpty()) {
                    stack.push(tmpStack.pop());
                }

                bracketNo--;
            } else {
                // 提取 原子名称
                j = i++;
                while (i < len && isLowerCase(chs[i])) {
                    i++;
                }
                k = formula.substring(j, i);

                // 提取 原子的数量
                num = 0;
                while (i < len && isNum(chs[i])) {
                    num = num * 10 + (chs[i] - '0');
                    i++;
                }
                num = num == 0 ? 1 : num;

                if (bracketNo > 0) {
                    stack.push(new Atom(k, num, bracketNo));
                } else {
                    // 没有左括号直接存 Map
                    cnt = treeMap.getOrDefault(k, 0);
                    treeMap.put(k, cnt + num);
                }
            }
        }

        // res process
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            k = entry.getKey();
            cnt = entry.getValue();
            if (cnt > 1) {
                res.append(k).append(cnt);
            } else {
                res.append(k);
            }
        }
        return res.toString();
    }

    private boolean isLowerCase(char c) {
        return 'a' <= c && c <= 'z';
    }

    private boolean isNum(char c) {
        return '0' <= c && c <= '9';
    }

    static class Atom {
        // 化学原子的名称
        String name;
        // 化学原子的数量
        int cnt;
        // 从左到右 左括号的编号（从 1 开始）
        int bracketNo;

        public Atom(String name, int cnt, int bracketNo) {
            this.name = name;
            this.cnt = cnt;
            this.bracketNo = bracketNo;
        }
    }

    public static void main(String[] args) {
        new _Stack_String().countOfAtoms("Mg(OH(SO3)2)2");
    }
}
