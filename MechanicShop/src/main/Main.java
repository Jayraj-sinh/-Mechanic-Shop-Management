package main;

import dao.*; 

import model.*; 

import exception.*; 

import java.util.List; 

import java.util.Scanner; 

 

public class Main { 

    static Scanner scanner = new Scanner(System.in); 

    static CustomerDAO customerDAO = new CustomerDAO(); 

    static VehicleDAO vehicleDAO = new VehicleDAO(); 

    static MechanicDAO mechanicDAO = new MechanicDAO(); 

    static WorkOrderDAO workOrderDAO = new WorkOrderDAO(); 

 

    public static void main(String[] args) { 

        System.out.println("=============================="); 

        System.out.println("  Mechanic Shop Management"); 

        System.out.println("=============================="); 

        boolean running = true; 

        while (running) { 

            System.out.println("\n--- MAIN MENU ---"); 

            System.out.println("1. Manage Customers"); 

            System.out.println("2. Manage Vehicles"); 

            System.out.println("3. Manage Mechanics"); 

            System.out.println("4. Manage Work Orders"); 

            System.out.println("0. Exit"); 

            System.out.print("Choose: "); 

            int choice = Integer.parseInt(scanner.nextLine()); 

            switch (choice) { 

                case 1: customerMenu(); break; 

                case 2: vehicleMenu(); break; 

                case 3: mechanicMenu(); break; 

                case 4: workOrderMenu(); break; 

                case 0: running = false; break; 

                default: System.out.println("Invalid option."); 

            } 

        } 

        System.out.println("Goodbye!"); 

    } 

 

    // ===== CUSTOMER MENU ===== 

    static void customerMenu() { 

        System.out.println("\n-- Customer Menu --"); 

        System.out.println("1. Add Customer"); 

        System.out.println("2. View All Customers"); 

        System.out.println("3. Update Customer"); 

        System.out.println("4. Delete Customer"); 

        System.out.println("5. Find Customer by ID"); 

        System.out.print("Choose: "); 

        int c = Integer.parseInt(scanner.nextLine()); 

        try { 

            switch (c) { 

                case 1: 

                    System.out.print("Name: "); String name = scanner.nextLine(); 

                    System.out.print("Phone: "); String phone = scanner.nextLine(); 

                    System.out.print("Email: "); String email = scanner.nextLine(); 

                    System.out.print("Address: "); String address = scanner.nextLine(); 

                    if (name.isEmpty()) throw new InvalidInputException("Name cannot be empty!"); 

                    customerDAO.add(new Customer(0, name, phone, email, address)); 

                    break; 

                case 2: 

                    List<Customer> customers = customerDAO.getAll(); 

                    if (customers.isEmpty()) System.out.println("No customers found."); 

                    else customers.forEach(System.out::println); 

                    break; 

                case 3: 

                    System.out.print("Enter Customer ID to update: "); 

                    int uid = Integer.parseInt(scanner.nextLine()); 

                    Customer existing = customerDAO.getById(uid); 

                    System.out.print("New Name (" + existing.name + "): "); String nn = scanner.nextLine(); 

                    System.out.print("New Phone (" + existing.phone + "): "); String np = scanner.nextLine(); 

                    System.out.print("New Email (" + existing.email + "): "); String ne = scanner.nextLine(); 

                    System.out.print("New Address (" + existing.address + "): "); String na = scanner.nextLine(); 

                    existing.name = nn.isEmpty() ? existing.name : nn; 

                    existing.phone = np.isEmpty() ? existing.phone : np; 

                    existing.email = ne.isEmpty() ? existing.email : ne; 

                    existing.address = na.isEmpty() ? existing.address : na; 

                    customerDAO.update(existing); 

                    break; 

                case 4: 

                    System.out.print("Enter Customer ID to delete: "); 

                    customerDAO.delete(Integer.parseInt(scanner.nextLine())); 

                    break; 

                case 5: 

                    System.out.print("Enter Customer ID: "); 

                    System.out.println(customerDAO.getById(Integer.parseInt(scanner.nextLine()))); 

                    break; 

            } 

        } catch (InvalidInputException e) { 

            System.out.println("Input Error: " + e.getMessage()); 

        } catch (CustomerNotFoundException e) { 

            System.out.println("Not Found: " + e.getMessage()); 

        } catch (DatabaseException e) { 

            System.out.println("Database Error: " + e.getMessage()); 

        } 

    } 

 

    // ===== VEHICLE MENU ===== 

    static void vehicleMenu() { 

        System.out.println("\n-- Vehicle Menu --"); 

        System.out.println("1. Add Vehicle"); 

        System.out.println("2. View All Vehicles"); 

        System.out.println("3. View Vehicles by Customer"); 

        System.out.println("4. Update Vehicle"); 

        System.out.println("5. Delete Vehicle"); 

        System.out.print("Choose: "); 

        int c = Integer.parseInt(scanner.nextLine()); 

        try { 

            switch (c) { 

                case 1: 

                    System.out.print("Make: "); String make = scanner.nextLine(); 

                    System.out.print("Model: "); String model = scanner.nextLine(); 

                    System.out.print("Year: "); int year = Integer.parseInt(scanner.nextLine()); 

                    System.out.print("License Plate: "); String plate = scanner.nextLine(); 

                    System.out.print("Customer ID: "); int cid = Integer.parseInt(scanner.nextLine()); 

                    vehicleDAO.add(new Vehicle(0, make, model, year, plate, cid)); 

                    break; 

                case 2: 

                    vehicleDAO.getAll().forEach(System.out::println); 

                    break; 

                case 3: 

                    System.out.print("Customer ID: "); 

                    vehicleDAO.getByCustomer(Integer.parseInt(scanner.nextLine())).forEach(System.out::println); 

                    break; 

                case 4: 

                    System.out.print("Vehicle ID to update: "); int vid = Integer.parseInt(scanner.nextLine()); 

                    System.out.print("New Make: "); String nm = scanner.nextLine(); 

                    System.out.print("New Model: "); String nmod = scanner.nextLine(); 

                    System.out.print("New Year: "); int ny = Integer.parseInt(scanner.nextLine()); 

                    System.out.print("New Plate: "); String np2 = scanner.nextLine(); 

                    System.out.print("Customer ID: "); int ncid = Integer.parseInt(scanner.nextLine()); 

                    vehicleDAO.update(new Vehicle(vid, nm, nmod, ny, np2, ncid)); 

                    break; 

                case 5: 

                    System.out.print("Vehicle ID to delete: "); 

                    vehicleDAO.delete(Integer.parseInt(scanner.nextLine())); 

                    break; 

            } 

        } catch (DatabaseException e) { 

            System.out.println("Database Error: " + e.getMessage()); 

        } 

    } 

 

    // ===== MECHANIC MENU ===== 

    static void mechanicMenu() { 

        System.out.println("\n-- Mechanic Menu --"); 

        System.out.println("1. Add Mechanic"); 

        System.out.println("2. View All Mechanics"); 

        System.out.println("3. Update Mechanic"); 

        System.out.println("4. Delete Mechanic"); 

        System.out.print("Choose: "); 

        int c = Integer.parseInt(scanner.nextLine()); 

        try { 

            switch (c) { 

                case 1: 

                    System.out.print("Name: "); String mn = scanner.nextLine(); 

                    System.out.print("Phone: "); String mp = scanner.nextLine(); 

                    System.out.print("Email: "); String me = scanner.nextLine(); 

                    System.out.print("Specialization: "); String ms = scanner.nextLine(); 

                    System.out.print("Hourly Rate: "); double mr = Double.parseDouble(scanner.nextLine()); 

                    mechanicDAO.add(new Mechanic(0, mn, mp, me, ms, mr)); 

                    break; 

                case 2: 

                    mechanicDAO.getAll().forEach(System.out::println); 

                    break; 

                case 3: 

                    System.out.print("Mechanic ID to update: "); int mid = Integer.parseInt(scanner.nextLine()); 

                    System.out.print("New Name: "); String nmn = scanner.nextLine(); 

                    System.out.print("New Phone: "); String nmp = scanner.nextLine(); 

                    System.out.print("New Email: "); String nme = scanner.nextLine(); 

                    System.out.print("New Specialization: "); String nms = scanner.nextLine(); 

                    System.out.print("New Hourly Rate: "); double nmr = Double.parseDouble(scanner.nextLine()); 

                    mechanicDAO.update(new Mechanic(mid, nmn, nmp, nme, nms, nmr)); 

                    break; 

                case 4: 

                    System.out.print("Mechanic ID to delete: "); 

                    mechanicDAO.delete(Integer.parseInt(scanner.nextLine())); 

                    break; 

            } 

        } catch (DatabaseException e) { 

            System.out.println("Database Error: " + e.getMessage()); 

        } 

    } 

 

    // ===== WORK ORDER MENU ===== 

    static void workOrderMenu() { 

        System.out.println("\n-- Work Order Menu --"); 

        System.out.println("1. Create Work Order"); 

        System.out.println("2. View All Work Orders"); 

        System.out.println("3. Complete a Work Order"); 

        System.out.println("4. Delete Work Order"); 

        System.out.print("Choose: "); 

        int c = Integer.parseInt(scanner.nextLine()); 

        try { 

            switch (c) { 

                case 1: 

                    System.out.print("Vehicle ID: "); int vid = Integer.parseInt(scanner.nextLine()); 

                    System.out.print("Mechanic ID: "); int mechId = Integer.parseInt(scanner.nextLine()); 

                    workOrderDAO.add(new WorkOrder(0, vid, mechId, "pending", 0)); 

                    break; 

                case 2: 

                    workOrderDAO.getAll().forEach(System.out::println); 

                    break; 

                case 3: 

                    System.out.print("Work Order ID: "); int wid = Integer.parseInt(scanner.nextLine()); 

                    System.out.print("Total Cost: $"); double cost = Double.parseDouble(scanner.nextLine()); 

                    workOrderDAO.completeOrder(wid, cost); 

                    break; 

                case 4: 

                    System.out.print("Work Order ID to delete: "); 

                    workOrderDAO.delete(Integer.parseInt(scanner.nextLine())); 

                    break; 

            } 

        } catch (DatabaseException e) { 

            System.out.println("Database Error: " + e.getMessage()); 

        } 

    } 

} 