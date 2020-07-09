/*
 * 后缀表达式实现字符串的计算；
 * 可以包含小数的加减乘除，没有对除零进行处理。
 */
package com.computer;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SuffixMath {

    public static void main(String[] args) {
        String formula = "1.2+2.5/32-41+105";
        List<String> tolist = toList(formula);
        System.out.println(Calculating(toSuffix(tolist)));
    }

    //转List
    public static List<String> toList(String formula) {
        int a = -1;
        List<String> items = new LinkedList<>();
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if( !((48 <= c && c <=57) || (int)c == 46)) {
                items.add(formula.substring(a + 1, i));
                a = i;
                items.add("" + c);
            }
        }
        items.add(formula.substring(a + 1, formula.length()));
        return items;
    }

    //转后缀
    public static List<String> toSuffix(List<String> items) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new LinkedList<>();
        for (String item : items) {
            if(item.equals("(")) {
                s1.push(item);
            }else if(item.equals(")")){
                while(!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            }else if(item.equals("/") || item.equals("*") || item.equals("+") || item.equals("-")) {
                if(s1.isEmpty() || s1.peek().equals("(")) {
                    s1.push(item);
                }else if(compare(s1.peek()) < compare(item)) {
                    s1.push(item);
                }else {
                    while(compare(s1.peek()) >= compare(item)) {
                        s2.add(s1.pop());
                        if(s1.isEmpty()) break;
                    }
                    s1.push(item);
                }
            }else {
                s2.add(item);
            }
        }

        while(!s1.isEmpty()) {
            s2.add(s1.pop());
        }

        System.out.println("后缀表达式：" + s2);
        return s2;
    }

    //比较优先级
    public static int compare(String operation){
        return switch (operation) {
            case "/" -> 1;
            case "*" -> 1;
            case "-" -> 0;
            case "+" -> 0;
            default -> 0;
        };
    }

    public static double Calculating(List<String> suffixList) {
        Stack<String> s = new Stack<>();
        double a;
        double b;
        for (String item : suffixList) {
            if(!(item.equals("/") || item.equals("*") || item.equals("+") || item.equals("-"))) {
                s.push(item);
            }else {
                switch (item) {
                    case "/":
                        a = Double.parseDouble(s.pop());
                        b = Double.parseDouble(s.pop());
                        s.push(String.valueOf(b/a));
                        break;
                    case "*":
                        a = Double.parseDouble(s.pop());
                        b = Double.parseDouble(s.pop());
                        s.push(String.valueOf(b*a));
                        break;
                    case "-":
                        a = Double.parseDouble(s.pop());
                        b = Double.parseDouble(s.pop());
                        s.push(String.valueOf(b-a));
                        break;
                    case "+":
                        a = Double.parseDouble(s.pop());
                        b = Double.parseDouble(s.pop());
                        s.push(String.valueOf(b+a));
                        break;
                    default:break;

                }
            }
        }
        return Double.parseDouble(s.pop());
    }

}
