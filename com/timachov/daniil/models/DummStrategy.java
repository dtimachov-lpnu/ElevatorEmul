package com.timachov.daniil.models;

import com.timachov.daniil.models.Elevator.Request;

public class DummStrategy implements ElevatorStrategy {

    @Override
    public Request getTargetRequest(Elevator elevator) {
        return elevator.requestQueue.get(0);
    }
    
};