package org.codeintelligence.processing;

import org.codeintelligence.models.Road;

import java.util.Random;

public class RoadDataProcessor {

    private Road road;

    public RoadDataProcessor(Road road){
        this.road = road;
    }

    public String computeLength(){
        String name = road.getName();
        Random random = new Random();

        return String.format("%.1f", 100.0 + (1000.0)*random.nextDouble());
    }

    public Double computeSpeedLimit(){
        String country = road.getCountry();

        if (country.equals("")){
            System.out.println("ERROR: Invalid country");
            return 0.0;
        }
        else if (country.toLowerCase().equals("usa") || country.toLowerCase().equals("canada")){
            return 100.0;
        }
        else {
            return 125.0;
        }
    }
}
