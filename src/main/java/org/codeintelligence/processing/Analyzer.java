package org.codeintelligence.processing;

import java.util.ArrayList;

public class Analyzer {

    public void complexProcessing(int[] input) {
        ArrayList<Integer> integers = new ArrayList<>();
        // copy the array
        int pos = 0;
        while (pos >= 0 && pos < input.length) {
            integers.add(input[pos]);
            pos = input[pos] + 1;
        }
        // process the copy
    }

}
