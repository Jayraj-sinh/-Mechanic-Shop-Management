package model; 

 

public abstract class Person { 

    public int id; 

    public String name; 

    public String phone; 

    public String email; 

 

    public Person(int id, String name, String phone, String email) { 

        this.id = id; 

        this.name = name; 

        this.phone = phone; 

        this.email = email; 

    } 

 

    public abstract String getRole(); 

} 