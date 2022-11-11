package org.codeintelligence.models;

public class Road {

    private String name;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Integer speedLimit) {
        this.speedLimit = speedLimit;
    }

    private Integer length;
    private Integer speedLimit;

    public Road(String name, String country){
        this.name = name;
        this.country = country;
        this.length = 0;
        this.speedLimit = 0;
    }

    public Road(){
        this("", "");
    }
}
