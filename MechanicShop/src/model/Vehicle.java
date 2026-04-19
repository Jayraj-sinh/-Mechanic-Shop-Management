package model; 

 

public class Vehicle { 

    public int id; 

    public String make; 

    public String model; 

    public int year; 

    public String licensePlate; 

    public int customerId; 

 

    public Vehicle(int id, String make, String model, int year, 

                   String licensePlate, int customerId) { 

        this.id = id; 

        this.make = make; 

        this.model = model; 

        this.year = year; 

        this.licensePlate = licensePlate; 

        this.customerId = customerId; 

    } 

 

    @Override 

    public String toString() { 

        return "ID: " + id + " | " + year + " " + make + " " + model + 

               " | Plate: " + licensePlate + " | CustomerID: " + customerId; 

    } 

} 