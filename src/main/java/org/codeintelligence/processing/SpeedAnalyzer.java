package org.codeintelligence.processing;

import java.util.ArrayList;
import java.util.List;

public class SpeedAnalyzer {

    public List speedLimitConverter(int[] limitsInKilometers) {
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<Integer> output = new ArrayList<>();
        // copy the array
        int pos = 0;
        while (pos >= 0 && pos < limitsInKilometers.length) {
            integers.add(limitsInKilometers[pos]);
            pos = limitsInKilometers[pos] + 1;
        }
        // process the copy
        for (int i: integers){
            output.add(Math.floorDiv(i, 2));
        }
        return output;
    }


}
