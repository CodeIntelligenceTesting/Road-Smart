package org.codeintelligence.processing;

import org.codeintelligence.models.Road;

public class RoadDataProcessor {

    private Road road;

    public RoadDataProcessor(Road road){
        this.road = road;
    }

    public Double computeLength(){
        String name = road.getName();

        if (name.isBlank()){
            System.out.println("ERROR: Invalid road length");
            return 0.0;
        }
        else if(name.equals("i95")){
            return 100.0;
        }
        else if (name.equals("pch")){
            return 500.0;
        }
        else if (name.equals("Bundesautobahn 7")){
            return 963.0;
        }
        else {
            return 123.0;
        }
    }

    public Double computeSpeedLimit(){
        String country = road.getCountry();

        if (country.isBlank()){
            System.out.println("ERROR: Invalid country");
            return 0.0;
        }

        else if (country.toLowerCase().equals("usa")){
            return 100.0;
        }
        else {
            return 125.0;
        }
    }
}
