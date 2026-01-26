package edu.ucsd.spendingtracker.datasource;


import edu.ucsd.spendingtracker.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SqlDataSource implements IDataSource {
   private final String url = "jdbc:sqlite:spending.db";


   public SqlDataSource() {
       try (Connection conn = DriverManager.getConnection(url)) {
           conn.createStatement().execute(
                   "CREATE TABLE IF NOT EXISTS expenses (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, category TEXT, amount REAL)");
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }


   @Override
   public List<Expense> getExpenses() {
       List<Expense> list = new ArrayList<>();
       try (Connection conn = DriverManager.getConnection(url);
               ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM expenses")) {
           while (rs.next()) {
               list.add(new Expense(rs.getInt("id"), rs.getString("name"),
                       Category.valueOf(rs.getString("category")), rs.getDouble("amount")));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return list;
   }


   @Override
   public void addExpense(Expense e) {
       try (Connection conn = DriverManager.getConnection(url);
               PreparedStatement ps = conn
                       .prepareStatement("INSERT INTO expenses(name, category, amount) VALUES(?,?,?)")) {
           ps.setString(1, e.getName());
           ps.setString(2, e.getCategory().name());
           ps.setDouble(3, e.getAmount());
           ps.executeUpdate();
       } catch (SQLException err) {
           err.printStackTrace();
       }
   }


   @Override
   public void deleteExpense(int id) {
       try (Connection conn = DriverManager.getConnection(url);
               PreparedStatement ps = conn.prepareStatement("DELETE FROM expenses WHERE id = ?")) {
           ps.setInt(1, id);
           ps.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
}
