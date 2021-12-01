package com.timachov.daniil.models;

import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;

public class Elevator {
    public static class Request {
        public Person person;
        public Floor srcFloor, dstFloor;

        public Request(Person person_, Floor srcFloor_, Floor dstFloor_) {
            this.person = person_;
            this.srcFloor = srcFloor_;
            this.dstFloor = dstFloor_;
        }
    };

    private float maxWeight;
    private float speed;
    private ElevatorStrategy moveStrategy;
    private List<Person> currentLoad;
    private int currentFloor;
    private boolean isBusy;
    public List<Request> requestQueue;
    private Building building; 

    public Elevator(Building building_, float maxWeight_, float speed_, ElevatorStrategy strategy_) {
        this.building = building_;
        this.maxWeight = maxWeight_;
        this.speed = speed_;
        this.moveStrategy = strategy_;
        this.currentLoad = new LinkedList<>();
        this.currentFloor = 0;
        this.isBusy = false;
        this.requestQueue = new LinkedList<>();
    }

    void run() {
        while (!requestQueue.isEmpty()) {
            Request targetRequest = moveStrategy.getTargetRequest(this);
            int targetFloor = targetRequest.dstFloor.getStage();
            int diff = targetFloor - this.currentFloor;
            
            this.isBusy = true;
            System.out.println("Moving " + diff);
            try {
                Thread.sleep(1000 * Math.abs(diff));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.isBusy = false;

            currentFloor = targetFloor;
            if (currentLoad.contains(targetRequest.person))
                currentLoad.remove(targetRequest.person);
            else {
                float currentLoadKg = currentLoad
                    .stream()
                    .map(p -> p.getWeight())
                    .collect(Collectors.summingDouble(Float::floatValue))
                    .floatValue();
                if (targetRequest.person != null) {
                    if (currentLoadKg + targetRequest.person.getWeight() <= maxWeight) {
                        currentLoad.add(targetRequest.person);
                        targetRequest.srcFloor.popPerson();
                    }
                }
            }

            requestQueue.remove(0);
        }
    }
};
