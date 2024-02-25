/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_book_store;

import java.sql.*;
import java.util.List;

/**
 *
 * @author MoSala7
 */
public class Oop_Book_Store {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Start();
//        new AddBook();
//        new UpdateBook();
//        new DeleteBook();
//        new SellBook();
//        new Display();
//        Book b1 = new Book(1001,"OOP","MOSALAH","big MO",2002,100,202);
        DBManipulating dbm = new DBManipulating();


    }
    
}
