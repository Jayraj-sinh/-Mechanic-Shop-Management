package model; 

 

public class Customer extends Person { 

    public String address; 

 

    public Customer(int id, String name, String phone, String email, String address) { 

        super(id, name, phone, email); 

        this.address = address; 

    } 

 

    @Override 

    public String getRole() { 

        return "Customer"; 

    } 

 

    public String getAddress() { 

        return address; 

    } 

 

    @Override 

    public String toString() { 

        return "ID: " + id + " | Name: " + name + " | Phone: " + phone + 

               " | Email: " + email + " | Address: " + address; 

    } 

} 