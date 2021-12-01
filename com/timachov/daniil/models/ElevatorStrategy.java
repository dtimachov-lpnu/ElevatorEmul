package com.timachov.daniil.models;

import com.timachov.daniil.models.Elevator.Request;

public interface ElevatorStrategy {
    public Request getTargetRequest(Elevator elevator);
}
