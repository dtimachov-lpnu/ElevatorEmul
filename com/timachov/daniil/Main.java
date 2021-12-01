package com.timachov.daniil;

import com.timachov.daniil.models.*;

class Main
{
    public static void main(String[] args) {
        Building building = new Building(3);
        building.getFloor(0).addPerson(new Person(100));
        building.getFloor(0).addPerson(new Person(50));
        building.getFloor(0).addPerson(new Person(60));
        building.getFloor(0).addPerson(new Person(38));

        building.getFloor(0).enqueueElevator(building.getElevator(0), building.getFloor(2));
        building.getFloor(0).enqueueElevator(building.getElevator(0), building.getFloor(1));
        building.getFloor(0).enqueueElevator(building.getElevator(0), building.getFloor(0));
        building.getFloor(0).enqueueElevator(building.getElevator(0), building.getFloor(1));
        building.getFloor(0).enqueueElevator(building.getElevator(0), building.getFloor(0));
        building.getFloor(0).enqueueElevator(building.getElevator(0), building.getFloor(2));

        building.runAllElevators();
    }
};