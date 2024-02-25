/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_book_store;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MoSala7
 */
public interface DBMInterface {
    
    public void addBook(Book book);
    public void removeBook(int ISBN);
    public void updateBook(int ISBN ,int quantity, int price);
    public void sellBook(int ISBN ,int quantity);
    public ArrayList<Book> displayBooks();
    
}
