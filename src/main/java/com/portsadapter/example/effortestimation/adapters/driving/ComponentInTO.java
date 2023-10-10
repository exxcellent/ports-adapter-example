package com.portsadapter.example.effortestimation.adapters.driving;


public class ComponentInTO {
    private String name;
    private Double effortEstimate;

    // Default constructor for deserialization
    public ComponentInTO() {
    }

    public ComponentInTO(String name, Double effortEstimate) {
        this.name = name;
        this.effortEstimate = effortEstimate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getEffortEstimate() {
        return effortEstimate;
    }

    public void setEffortEstimate(Double effortEstimate) {
        this.effortEstimate = effortEstimate;
    }
}
