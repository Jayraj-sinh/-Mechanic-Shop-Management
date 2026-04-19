package model; 

 

public class Mechanic extends Person { 

    public String specialization; 

    public double hourlyRate; 

 

    public Mechanic(int id, String name, String phone, String email, 

                    String specialization, double hourlyRate) { 

        super(id, name, phone, email); 

        this.specialization = specialization; 

        this.hourlyRate = hourlyRate; 

    } 

 

    @Override 

    public String getRole() { 

        return "Mechanic"; 

    } 

 

    public String getSpecialization() { 

        return specialization; 

    } 

 

    @Override 

    public String toString() { 

        return "ID: " + id + " | Name: " + name + " | Specialization: " + 

               specialization + " | Rate: $" + hourlyRate + "/hr"; 

    } 

} 