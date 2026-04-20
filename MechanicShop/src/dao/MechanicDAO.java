package dao; 

 

import model.Mechanic; 

import exception.DatabaseException; 

import java.sql.*; 

import java.util.ArrayList; 

import java.util.List; 

 

public class MechanicDAO { 

 

    public void add(Mechanic m) throws DatabaseException { 

        String sql = "INSERT INTO mechanics (name, phone, email, specialization, hourly_rate) VALUES (?, ?, ?, ?, ?)"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setString(1, m.name); 

            stmt.setString(2, m.phone); 

            stmt.setString(3, m.email); 

            stmt.setString(4, m.specialization); 

            stmt.setDouble(5, m.hourlyRate); 

            stmt.executeUpdate(); 

            System.out.println("Mechanic added!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to add mechanic: " + e.getMessage()); 

        } 

    } 

 

    public List<Mechanic> getAll() throws DatabaseException { 

        List<Mechanic> list = new ArrayList<>(); 

        String sql = "SELECT * FROM mechanics"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             Statement stmt = conn.createStatement(); 

             ResultSet rs = stmt.executeQuery(sql)) { 

            while (rs.next()) { 

                list.add(new Mechanic( 

                    rs.getInt("id"), rs.getString("name"), 

                    rs.getString("phone"), rs.getString("email"), 

                    rs.getString("specialization"), rs.getDouble("hourly_rate") 

                )); 

            } 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to fetch mechanics: " + e.getMessage()); 

        } 

        return list; 

    } 

 

    public void update(Mechanic m) throws DatabaseException { 

        String sql = "UPDATE mechanics SET name=?, phone=?, email=?, specialization=?, hourly_rate=? WHERE id=?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setString(1, m.name); 

            stmt.setString(2, m.phone); 

            stmt.setString(3, m.email); 

            stmt.setString(4, m.specialization); 

            stmt.setDouble(5, m.hourlyRate); 

            stmt.setInt(6, m.id); 

            stmt.executeUpdate(); 

            System.out.println("Mechanic updated!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to update mechanic: " + e.getMessage()); 

        } 

    } 

 

    public void delete(int id) throws DatabaseException { 

        String sql = "DELETE FROM mechanics WHERE id = ?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setInt(1, id); 

            stmt.executeUpdate(); 

            System.out.println("Mechanic deleted!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to delete mechanic: " + e.getMessage()); 

        } 

    } 

} 