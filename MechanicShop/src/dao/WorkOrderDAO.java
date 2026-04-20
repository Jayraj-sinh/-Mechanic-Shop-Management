package dao; 

 

import model.WorkOrder; 

import exception.DatabaseException; 

import java.sql.*; 

import java.util.ArrayList; 

import java.util.List; 

 

public class WorkOrderDAO { 

 

    public void add(WorkOrder wo) throws DatabaseException { 

        String sql = "INSERT INTO work_orders (vehicle_id, mechanic_id, status, total_cost) VALUES (?, ?, ?, ?)"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setInt(1, wo.vehicleId); 

            stmt.setInt(2, wo.mechanicId); 

            stmt.setString(3, wo.status); 

            stmt.setDouble(4, wo.totalCost); 

            stmt.executeUpdate(); 

            System.out.println("Work order created!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to create work order: " + e.getMessage()); 

        } 

    } 

 

    public List<WorkOrder> getAll() throws DatabaseException { 

        List<WorkOrder> list = new ArrayList<>(); 

        String sql = "SELECT * FROM work_orders"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             Statement stmt = conn.createStatement(); 

             ResultSet rs = stmt.executeQuery(sql)) { 

            while (rs.next()) { 

                list.add(new WorkOrder( 

                    rs.getInt("id"), rs.getInt("vehicle_id"), 

                    rs.getInt("mechanic_id"), rs.getString("status"), 

                    rs.getDouble("total_cost") 

                )); 

            } 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to fetch work orders: " + e.getMessage()); 

        } 

        return list; 

    } 

 

    public void completeOrder(int id, double totalCost) throws DatabaseException { 

        String sql = "UPDATE work_orders SET status='completed', total_cost=? WHERE id=?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setDouble(1, totalCost); 

            stmt.setInt(2, id); 

            stmt.executeUpdate(); 

            System.out.println("Work order #" + id + " marked complete! Total: $" + totalCost); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to complete order: " + e.getMessage()); 

        } 

    } 

 

    public void delete(int id) throws DatabaseException { 

        String sql = "DELETE FROM work_orders WHERE id = ?"; 

        try (Connection conn = DatabaseConnection.getConnection(); 

             PreparedStatement stmt = conn.prepareStatement(sql)) { 

            stmt.setInt(1, id); 

            stmt.executeUpdate(); 

            System.out.println("Work order deleted!"); 

        } catch (SQLException e) { 

            throw new DatabaseException("Failed to delete work order: " + e.getMessage()); 

        } 

    } 

} 