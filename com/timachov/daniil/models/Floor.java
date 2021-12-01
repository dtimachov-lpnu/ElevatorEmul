package com.timachov.daniil.models;

import java.util.Queue;
import java.util.LinkedList;

import com.timachov.daniil.models.Elevator.Request;
import com.timachov.daniil.models.Logger.LoggingLevel;

public class Floor {
    private Queue<Person> peopleAwaiting;
    private Logger logger;
    private int stage;

    public Floor(int stage_) {
        this.logger = new Logger();
        this.logger.setLevel(LoggingLevel.Info);
        this.logger.setSink("stage_" + stage_ + ".log");
        this.peopleAwaiting = new LinkedList<>();
        this.stage = stage_;
    }

    public void addPerson(Person person) {
        this.peopleAwaiting.add(person);
        logger.logMessage("New person with weight " + person.getWeight() + " is coming to floor " + this.stage);
    }

    public void popPerson() {
        Person person = this.peopleAwaiting.remove();
        logger.logMessage("Person with weight " + person.getWeight() + " is leaving the floor " + this.stage);
    }

    public int getStage() {
        return this.stage;
    }

    public void enqueueElevator(Elevator elevator) {
        Request request = new Request(this.peopleAwaiting.peek(), this, this);
        elevator.requestQueue.add(request);
    }

    public void enqueueElevator(Elevator elevator, Floor dstFloor) {
        Request request = new Request(this.peopleAwaiting.peek(), this, dstFloor);
        elevator.requestQueue.add(request);
    }
};
