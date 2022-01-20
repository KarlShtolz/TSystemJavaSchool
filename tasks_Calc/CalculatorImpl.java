package com.tsystems.javaschool.tasks;

import java.util.*;
//Calculator's class that implements the interface "Calculator"
public class CalculatorImpl implements Calculator {
    //Implemented method
    public String evaluate(String statement) {
        try {       //If the method catches an exception the application will return null
            char[] toChars = statement.toCharArray();       //Transforming the incoming String to char array
            ArrayList<String> items = new ArrayList<String>();
            for (int i = 0; i < toChars.length; i++) {      //Cleaning off the spaces
                if (Character.isSpaceChar(toChars[i])) {
                    continue;
                }
                if ((toChars[i] == '(' && i != 0) && Character.isDigit(toChars[i - 1])) throw new Exception();  //Catching the input error
                if ((toChars[i] == ')' && i != toChars.length - 1) && Character.isDigit(toChars[i + 1]))
                    throw new Exception();
                String temp = "" + toChars[i];
                if (Character.isDigit(toChars[i])) {        //Forming numbers from digits
                    for (int j = i + 1; j < toChars.length; j++) {
                        if (Character.isDigit(toChars[j])) {
                            temp += toChars[j];
                            i++;
                        } else break;
                    }
                }
                items.add(temp);
            }

            int count = items.size();
            Stack<Integer> lBrace = new Stack<Integer>();       //Stack of the opening brackets
            int brace = 0;
            int j = -1;
            //Splitting the expression by the brackets
            for (int i = 0; i < count; i++) {
                j++;
                if (items.get(j).equals("(")) lBrace.push(j);
                if (items.get(j).equals(")")) {
                    brace = lBrace.pop();
                    ArrayList<String> subList = new ArrayList<String>();
                    for (int k = brace + 1; k < j; k++) {
                        subList.add(items.get(k));
                    }
                    items.set(brace, basicCalc(subList));       //Replacing the expression in the brackets by its result
                    for (int k = j; k >= brace + 1; k--) {
                        items.remove(k);
                    }
                    j = brace;
                }
            }
            return "" + Math.round(Double.valueOf(basicCalc(items)) * 10000) / 10000.0000;  //Rounding the result
        } catch (Exception e) {
            return null;
        }
    }


    //Supporting method for calculating in the brackets
    private String basicCalc(ArrayList<String> list) {
        if (list.get(0).equals("-")) list.add(0, "0");      //Handling the first negative number
        int count = list.size();
        String result;
        int j = 0;
        for (int i = 1; i < count; i++) {       //High-priority calculations
            j++;
            if (list.get(j - 1).equals("*")) {
                result = "" + (Double.valueOf(list.get(j - 2)) * Double.valueOf(list.get(j)));
                list.set(j - 2, result);
                list.remove(j);
                list.remove(j - 1);
                j = j - 2;
            } else if (list.get(j - 1).equals("/")) {
                result = "" + (Double.valueOf(list.get(j - 2)) / Double.valueOf(list.get(j)));
                list.set(j - 2, result);
                list.remove(j);
                list.remove(j - 1);
                j = j - 2;
            }
        }
        count = list.size();
        j = 0;
        for (int i = 1; i < count; i++) {       //Low-priority calculations
            j++;
            if (list.get(j - 1).equals("+")) {
                result = "" + (Double.valueOf(list.get(j - 2)) + Double.valueOf(list.get(j)));
                list.set(j - 2, result);
                list.remove(j);
                list.remove(j - 1);
                j = j - 2;
            } else if (list.get(j - 1).equals("-")) {
                result = "" + (Double.valueOf(list.get(j - 2)) - Double.valueOf(list.get(j)));
                list.set(j - 2, result);
                list.remove(j);
                list.remove(j - 1);
                j = j - 2;
            }
        }
        return list.get(0);
    }
}
