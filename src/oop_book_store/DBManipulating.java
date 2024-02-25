/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_book_store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MoSala7
 */
public class DBManipulating implements DBMInterface{

    
    public Connection connection;
    
    @Override
    public void addBook(Book book) {
        String localURL = "jdbc:ucanaccess://Books.accdb";
        try {
            connection = DriverManager.getConnection(localURL);
            if (connection == null){
                System.out.println("Connection Fialed");
                return;
            }
  
            String quary = "INSERT INTO booktable (ISBN,booktitle,bookauther,bookpulisher,pyear,quantity,price)"
                    + " VALUES (?,?,?,?,?,?,?)";
            
            try(PreparedStatement statement = connection.prepareStatement(quary)){
                statement.setLong(1, book.getIsbn());
                statement.setString(2, book.getTitle());
                statement.setString(3, book.getAuthor());
                statement.setString(4, book.getPublisher());
                statement.setInt(5, book.getYear());
                statement.setInt(6, book.getQuantity());
                statement.setInt(7, book.getPrice());

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null,
                        "BOOK ADDED SECCUSSFULY",
                        "DONE!",
                         JOptionPane.INFORMATION_MESSAGE);
                System.out.println("BOOK ADDED SECCUSSFULY");

            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch(SQLException e) {
            System.out.print("Do not connect to DB - Error:"+e);
        }
    }

    @Override
    public void removeBook(int ISBN) {
        
        String localURL = "jdbc:ucanaccess://Books.accdb";
        try {
            connection = DriverManager.getConnection(localURL);
            if (connection == null){
                System.out.println("Connection Fialed");
                return;
            }
  
            String quary = "DELETE FROM booktable WHERE ISBN=?";
            
            try(PreparedStatement statement = connection.prepareStatement(quary)){
                statement.setLong(1, ISBN);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null,
                        "BOOK DELETED SECCUSSFULY",
                        "DONE!",
                         JOptionPane.INFORMATION_MESSAGE);
                System.out.println("BOOK DELETED SECCUSSFULY");

            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch(SQLException e) {
            System.out.print("Do not connect to DB - Error:"+e);
        }
        
    }

    @Override
    public void updateBook(int ISBN ,int quantity, int price) {
        
        String localURL = "jdbc:ucanaccess://Books.accdb";
        try {
            connection = DriverManager.getConnection(localURL);
            if (connection == null){
                System.out.println("Connection Fialed");
                return;
            }
            
            
            
  
            String quary = "UPDATE booktable SET quantity=? ,price=? WHERE ISBN=?";
            
            try(PreparedStatement statement = connection.prepareStatement(quary)){
                statement.setLong(3, ISBN);
                statement.setInt(1, quantity);
                statement.setInt(2, price);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null,
                        "BOOK UPDATED SECCUSSFULY",
                        "DONE!",
                         JOptionPane.INFORMATION_MESSAGE);
                System.out.println("BOOK UPDATED SECCUSSFULY");

            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch(SQLException e) {
            System.out.print("Do not connect to DB - Error:"+e);
        }
        
    }

    @Override
    public ArrayList<Book> displayBooks() {
        ArrayList<Book> bookList= new ArrayList<>();
        
        String localURL = "jdbc:ucanaccess://Books.accdb";
        try {
            connection = DriverManager.getConnection(localURL);
            if (connection == null){
                System.out.println("Connection Fialed");
                
            }
  
            String quary = "SELECT booktitle,bookauther,pyear,quantity FROM booktable";
            
            try(PreparedStatement statement = connection.prepareStatement(quary)){

                ResultSet result = statement.executeQuery();
                while(result.next()){
//                    ISBN,booktitle,bookauther,bookpulisher,pyear,quantity,price
                    Book thisBook = new Book(0,result.getString("booktitle"),
                            result.getString("bookauther"),
                            "",
                            result.getInt("pyear"),
                            result.getInt("quantity"),
                            0);
                    bookList.add(thisBook);
                
                }
                System.out.println("BOOK DISPLAYED SECCUSSFULY");

            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch(SQLException e) {
            System.out.print("Do not connect to DB - Error:"+e);
        }
        return bookList;
        
    }

    @Override
    public void sellBook(int ISBN, int quantity) {
        int q = getQuantity(ISBN);
        String localURL = "jdbc:ucanaccess://Books.accdb";
        try {
            connection = DriverManager.getConnection(localURL);
            if (connection == null){
                System.out.println("Connection Fialed");
                return;
            }
            
            
  
            String quary = "UPDATE booktable SET quantity=?  WHERE ISBN=?";
            
            try(PreparedStatement statement = connection.prepareStatement(quary)){
                if(q>quantity){
                    statement.setLong(2, ISBN);
                    statement.setInt(1, (q - quantity));
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                        "BOOK SELLED SECCUSSFULY",
                        "DONE!",
                         JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("BOOK SELLED SECCUSSFULY");
                }else{
                    JOptionPane.showMessageDialog(null,
                        "THE STOCK IS NOT ENOUGH",
                        "SELL FAILED",
                         JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    
                
                

            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch(SQLException e) {
            System.out.print("Do not connect to DB - Error:"+e);
        }
        
        
    }
    public int getQuantity(long ISBN){
        int q = 0;
        String localURL = "jdbc:ucanaccess://Books.accdb";
        try {
            connection = DriverManager.getConnection(localURL);
            if (connection == null){
                System.out.println("Connection Fialed");
                
            }
  
            String quary = "SELECT ISBN,quantity FROM booktable";
            
            try(PreparedStatement statement = connection.prepareStatement(quary)){

                ResultSet result = statement.executeQuery();
                while(result.next()){
                    
                        if(result.getLong("ISBN")== ISBN){
                            q = result.getInt("quantity");
                        }
                }
                System.out.println("BOOK DISPLAYED SECCUSSFULY");

            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch(SQLException e) {
            System.out.print("Do not connect to DB - Error:"+e);
        }
        return q;
    }
    
    
    
    
}
