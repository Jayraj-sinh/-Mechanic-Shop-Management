package dao; 

 

import model.Customer; 

import exception.CustomerNotFoundException; 

import exception.DatabaseException; 

import java.sql.*; 

import java.util.ArrayList; 

import java.util.List; 

 

public class CustomerDAO { 

 

    // ADD a new customer 

    public void add(Customer c) throws DatabaseException { 

        String sql = "INSERT INTO customers (name, phone, email, address) VALUES (?, ?, ?, ?)"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setString(1, c.name); 

            stmt.setString(2, c.phone); 

            stmt.setString(3, c.email); 

            stmt.setString(4, c.address); 

            stmt.executeUpdate(); 

            System.out.println("Customer added successfully!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to add customer: " + e.getMessage()); 

        } 

    } 

 

    // GET ALL customers 

    public List<Customer> getAll() throws DatabaseException { 

        List<Customer> list = new ArrayList<>(); 

        String sql = "SELECT * FROM customers"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             Statement stmt = conn.createStatement(); 

             ResultSet rs = stmt.executeQuery(sql)) { 

            while (rs.next()) { 

                list.add(new Customer( 

                    rs.getInt("id"), 

                    rs.getString("name"), 

                    rs.getString("phone"), 

                    rs.getString("email"), 

                    rs.getString("address") 

                )); 

            } 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to fetch customers: " + e.getMessage()); 

        } 

        return list; 

    } 

 

    // GET customer by ID 

    public Customer getById(int id) throws CustomerNotFoundException, DatabaseException { 

        String sql = "SELECT * FROM customers WHERE id = ?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setInt(1, id); 

            ResultSet rs = stmt.executeQuery(); 

            if (rs.next()) { 

                return new Customer( 

                    rs.getInt("id"), 

                    rs.getString("name"), 

                    rs.getString("phone"), 

                    rs.getString("email"), 

                    rs.getString("address") 

                ); 

            } else { 

                throw new CustomerNotFoundException("No customer found with ID: " + id); 

            } 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to find customer: " + e.getMessage()); 

        } 

    } 

 

    // UPDATE a customer 

    public void update(Customer c) throws DatabaseException { 

        String sql = "UPDATE customers SET name=?, phone=?, email=?, address=? WHERE id=?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setString(1, c.name); 

            stmt.setString(2, c.phone); 

            stmt.setString(3, c.email); 

            stmt.setString(4, c.address); 

            stmt.setInt(5, c.id); 

            stmt.executeUpdate(); 

            System.out.println("Customer updated successfully!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to update customer: " + e.getMessage()); 

        } 

    } 

 

    // DELETE a customer 

    public void delete(int id) throws DatabaseException { 

        String sql = "DELETE FROM customers WHERE id = ?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setInt(1, id); 

            stmt.executeUpdate(); 

            System.out.println("Customer deleted successfully!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to delete customer: " + e.getMessage()); 

        } 

    } 

} 