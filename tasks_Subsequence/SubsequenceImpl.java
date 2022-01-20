package com.tsystems.javaschool.tasks;


import java.util.List;


//Subsequence's class that implements the interface "Subsequence"
public class SubsequenceImpl implements Subsequence {

    public boolean find(List x, List y) {
        try {       //Catching for incorrect parameters
            if (y.containsAll(x)) {     //Preliminary checking
                int barrier = 0;        //This variable is controlling the order
                int count = 0;          //This variable is counting the successful findings
                int binary;             //This variable is indicating a successful finding
                for (int i = 0; i < x.size(); i++) {
                    binary = 0;
                    for (int j = barrier; j < y.size(); j++) {
                        if (x.get(i).equals(y.get(j))) {                    //Finding of an equity
                            barrier = j + 1;
                            count++;
                            binary = 1;
                            break;
                        }
                    }
                    if ((binary == 0) || (barrier >= y.size())) break;      //Efficient finishing the search
                }
                if (count == x.size()) return true;             //The conditions are satisfied
                else return false;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }
}
