package pupil.math;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.*;

public class PupilMath {
    private static final Random rand = new Random();
    private static final DecimalFormat format=new DecimalFormat("0.00");
    public static void main(String[] args) {


    }


    //随机加、减、乘、除操作
    static String randMulDvi(int nums, double result, String formula) {
        int c = nums / 2;
        System.out.println(c);
        for (int j = 1; j < nums - c; j++) {
            int i = Math.abs(rand.nextInt() % 3);
            switch (i) {
                case 0:         //除
                    double a = randNum();
                    if(a == 0) a = 1;
                    result = (float)(result / a);
                    formula = formula + "÷" + a;
//                    System.out.println(formula + " = " + result);
                    break;
                case 1:         //乘
                    double b = randNum();
                    result = result * b;
                    formula = formula + "×" + b;
//                    System.out.println(formula + " = " + result);
                    break;
                default:
                    break;
            }
        }

        return randAddSub(c, result, formula);
    }
    private static String randAddSub(int nums, double result, String formula) {
        for (int j = 1; j < nums; j++) {
            int i = Math.abs(rand.nextInt() % 2);
            switch (i) {
                case 0:         //减
                    double c = randNum();
                    result = result - c;
                    formula = formula + "-" + c;
//                    System.out.println(formula + " = " + result);
                    break;
                case 1:         //加
                    double d = randNum();
                    result = result + d;
                    formula = formula + "+" + d;
//                    System.out.println(formula + " = " + result);
                    break;
                default:
                    break;
            }
        }
        System.out.println(formula + " = " + format.format(result));
        return  formula + " = " + format.format(result);
    }

    static int randNum() {
        return Math.abs(rand.nextInt() % 50);
    }

    public static String splitResult(String formula) {
        String result = null;
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if((int)c == 61) {
                result = formula.substring(i + 1);
            }
        }
        return result;
    }

    public static String splitFormula(String formulas) {
        String formula = null;
        for (int i = 0; i < formulas.length(); i++) {
            char c = formulas.charAt(i);
            if((int)c == 61) {
                formula = formulas.substring(0, i);
            }
        }
        return formula;
    }

}
