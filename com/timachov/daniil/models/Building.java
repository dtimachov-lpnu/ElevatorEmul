package com.timachov.daniil.models;

import java.util.List;
import java.util.ArrayList;

public class Building {
    private List<Floor> floors;
    private List<Elevator> elevators;

    public Building(int nFloors) {
        this.floors = new ArrayList<>(nFloors);
        for (var i = 0; i < nFloors; i++) {
            this.floors.add(new Floor(i));
        }

        this.elevators = new ArrayList<>(2);
        this.elevators.add(new Elevator(this, 600, 1, new DummStrategy()));
        this.elevators.add(new Elevator(this, 400, 1, new KindStrategy()));
    }

    public Floor getFloor(int stage) {
        return floors.get(stage);
    }

    public Elevator getElevator(int idx) {
        return this.elevators.get(idx);
    }

    public void runAllElevators() {
        for (Elevator elevator : elevators) {
            new Thread(() -> {
                elevator.run();
            }).start();
        }
    }
}
