package model; 

 

public class Service { 

    public int id; 

    public String name; 

    public double price; 

    public int durationMinutes; 

 

    public Service(int id, String name, double price, int durationMinutes) { 

        this.id = id; 

        this.name = name; 

        this.price = price; 

        this.durationMinutes = durationMinutes; 

    } 

 

    @Override 

    public String toString() { 

        return "ID: " + id + " | " + name + " | $" + price + 

               " | " + durationMinutes + " min"; 

    } 

} 