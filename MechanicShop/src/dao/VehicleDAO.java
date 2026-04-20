package dao; 

 

import model.Vehicle; 

import exception.DatabaseException; 

import java.sql.*; 

import java.util.ArrayList; 

import java.util.List; 

 

public class VehicleDAO { 

 

    public void add(Vehicle v) throws DatabaseException { 

        String sql = "INSERT INTO vehicles (make, model, year, license_plate, customer_id) VALUES (?, ?, ?, ?, ?)"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setString(1, v.make); 

            stmt.setString(2, v.model); 

            stmt.setInt(3, v.year); 

            stmt.setString(4, v.licensePlate); 

            stmt.setInt(5, v.customerId); 

            stmt.executeUpdate(); 

            System.out.println("Vehicle added successfully!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to add vehicle: " + e.getMessage()); 

        } 

    } 

 

    public List<Vehicle> getAll() throws DatabaseException { 

        List<Vehicle> list = new ArrayList<>(); 

        String sql = "SELECT * FROM vehicles"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             Statement stmt = conn.createStatement(); 

             ResultSet rs = stmt.executeQuery(sql)) { 

            while (rs.next()) { 

                list.add(new Vehicle( 

                    rs.getInt("id"), rs.getString("make"), 

                    rs.getString("model"), rs.getInt("year"), 

                    rs.getString("license_plate"), rs.getInt("customer_id") 

                )); 

            } 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to fetch vehicles: " + e.getMessage()); 

        } 

        return list; 

    } 

 

    public List<Vehicle> getByCustomer(int customerId) throws DatabaseException { 

        List<Vehicle> list = new ArrayList<>(); 

        String sql = "SELECT * FROM vehicles WHERE customer_id = ?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setInt(1, customerId); 

            ResultSet rs = stmt.executeQuery(); 

            while (rs.next()) { 

                list.add(new Vehicle( 

                    rs.getInt("id"), rs.getString("make"), 

                    rs.getString("model"), rs.getInt("year"), 

                    rs.getString("license_plate"), rs.getInt("customer_id") 

                )); 

            } 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to fetch vehicles: " + e.getMessage()); 

        } 

        return list; 

    } 

 

    public void update(Vehicle v) throws DatabaseException { 

        String sql = "UPDATE vehicles SET make=?, model=?, year=?, license_plate=? WHERE id=?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setString(1, v.make); 

            stmt.setString(2, v.model); 

            stmt.setInt(3, v.year); 

            stmt.setString(4, v.licensePlate); 

            stmt.setInt(5, v.id); 

            stmt.executeUpdate(); 

            System.out.println("Vehicle updated!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to update vehicle: " + e.getMessage()); 

        } 

    } 

 

    public void delete(int id) throws DatabaseException { 

        String sql = "DELETE FROM vehicles WHERE id = ?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setInt(1, id); 

            stmt.executeUpdate(); 

            System.out.println("Vehicle deleted!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to delete vehicle: " + e.getMessage()); 

        } 

    } 

} 