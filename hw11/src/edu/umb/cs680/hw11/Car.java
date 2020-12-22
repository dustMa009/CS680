package edu.umb.cs680.hw11;

import java.util.ArrayList;
import java.util.Comparator;

public class Car {
    private String make, model;
    private int mileage, year;
    private float price;
    private int dominate = 0;
    public Car(String make, String model, int mileage, int year,
               float price){
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }
    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public float getPrice() {
        return price;
    }

    public void setDominateCount(ArrayList<Car> cars) {
        for (Car x : cars) {
            if ((this.price > x.getPrice()) && (this.mileage >= x.getMileage())) {
                this.dominate++;
            } else if ((this.price == x.getPrice()) && (this.mileage > x.getMileage())) {
                this.dominate++;
            }
        }
    }

    public float getDominateCount(){
        return this.dominate;
    }

}
