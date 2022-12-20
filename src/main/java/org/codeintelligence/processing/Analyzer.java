package org.codeintelligence.processing;

import java.util.ArrayList;
import java.util.List;

public class Analyzer {

    public List complexProcessing(int[] input) {
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<Integer> output = new ArrayList<>();
        // copy the array
        int pos = 0;
        while (pos >= 0 && pos < input.length) {
            integers.add(input[pos]);
            pos = input[pos] + 1;
        }
        // process the copy
        for (int i: integers){
            output.add(2*i);
        }
        return output;
    }

}
