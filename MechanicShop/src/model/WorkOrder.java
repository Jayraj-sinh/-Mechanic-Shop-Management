package model; 

 

public class WorkOrder implements Serviceable { 

    public int id; 

    public int vehicleId; 

    public int mechanicId; 

    public String status; 

    public double totalCost; 

 

    public WorkOrder(int id, int vehicleId, int mechanicId, 

                     String status, double totalCost) { 

        this.id = id; 

        this.vehicleId = vehicleId; 

        this.mechanicId = mechanicId; 

        this.status = status; 

        this.totalCost = totalCost; 

    } 

 

    @Override 

    public double calculateTotal() { 

        return totalCost; 

    } 

 

    @Override 

    public String getStatus() { 

        return status; 

    } 

 

    @Override 

    public void markComplete() { 

        this.status = "completed"; 

    } 

 

    @Override 

    public String toString() { 

        return "Order ID: " + id + " | Vehicle ID: " + vehicleId + 

               " | Mechanic ID: " + mechanicId + " | Status: " + status + 

               " | Total: $" + totalCost; 

    } 

} 