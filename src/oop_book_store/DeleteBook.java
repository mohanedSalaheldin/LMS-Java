/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_book_store;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static oop_book_store.UpdateBook.customizeButtons;

/**
 *
 * @author MoSala7
 */
public class DeleteBook implements ActionListener{
    JFrame frame;
    JPanel leftPanel;
    JPanel rightPanel;

    public DeleteBook() {
        frame = new JFrame();
        frame.setTitle("LMS");
        frame.setSize(900,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(null);
        frame.setLayout(null);
        rightPanelCustomization();
        leftPanelCustomization();
        frame.setVisible(true);
    }
    JButton addBook;
    JButton updateBook;
    JButton deleteBook;
    JButton sellBook;
    JButton displayBooks;
    JButton home;
    JButton exit;
    /*
        - this method to Perform all operations on the Right panel
    */ 
    public void leftPanelCustomization(){
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(92,219,148));
        leftPanel.setBounds(0, 0,220, 600);
        leftPanel.setLayout(null);
        // Adding button
        /***************************************************************************************************/
        addBook = new JButton("ADD BOOK");
        addBook.setBounds(10, 45, 200, 60);
        addBook.addActionListener(this);
        customizeButtons(addBook);
        leftPanel.add(addBook);
        /***************************************************************************************************/
        updateBook = new JButton("UPDATE BOOK");
        updateBook.setBounds(10, 115, 200, 60);
        updateBook.addActionListener(this);
        customizeButtons(updateBook);
        leftPanel.add(updateBook);
        /***************************************************************************************************/
        sellBook = new JButton("SELL BOOK");
        sellBook.setBounds(10, 185, 200, 60);
        sellBook.addActionListener(this);
        customizeButtons(sellBook);
        leftPanel.add(sellBook);
        /***************************************************************************************************/
        displayBooks = new JButton("DISPLAY BOOKS");
        displayBooks.setBounds(10, 255, 200, 60);
        displayBooks.addActionListener(this);
        customizeButtons(displayBooks);
        leftPanel.add(displayBooks);
        /***************************************************************************************************/
        deleteBook = new JButton("DELETE BOOK");
        deleteBook.setBounds(10, 325, 200, 60);
        deleteBook.addActionListener(this);
        customizeButtons(deleteBook);
        chosedBtn(deleteBook);
        leftPanel.add(deleteBook);
        /***************************************************************************************************/
        home = new JButton("HOME");
        home.setBounds(10, 395, 200, 60);
        home.addActionListener(this);
        customizeButtons(home);
        leftPanel.add(home);
        /***************************************************************************************************/
        exit = new JButton("EXIT");
        exit.setBounds(10, 465, 200, 60);
        exit.addActionListener(this);
        customizeButtons(exit);
        leftPanel.add(exit);
        /***************************************************************************************************/
      
        frame.add(leftPanel);
    }
    
    
    
    
    /*
        - this method to Perform all operations on the Right panel
    */ 
    
    JLabel bookISBN;
    JTextField isbnField;
    JButton saveButton;
    public void rightPanelCustomization(){
        
         // Creating the Panel
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(237,245,224));
        rightPanel.setBounds(220, 0,680, 600);
        rightPanel.setLayout(null);
        
        bookISBN = new JLabel("BOOK'S ISBN TO BE DELETE: ");
        bookISBN.setBounds(215, 200, 250, 20);
        modifyFont(bookISBN);
        rightPanel.add(bookISBN);
        isbnField = new JTextField();
        isbnField.setFont(new Font("Tajawal", Font.BOLD, 14));
        modifyFont(isbnField);
        isbnField.setBounds(215, 240, 250, 40);
        rightPanel.add(isbnField);
        
        saveButton = new JButton("SAVE");
        saveButton.setBounds(290, 310, 100, 50);
        saveButton.addActionListener(this);
        customizeButtons(saveButton);
//        saveButton.setForeground(new Color(5,57,107));
        rightPanel.add(saveButton);
        
        frame.add(rightPanel);
    }
    public  static void customizeButtons(JButton button){
        button.setFont(new Font("Abril Fatface", Font.BOLD, 18));
        button.setFocusable(false);
        button.setRolloverEnabled(false);
        button.setBorder(new RoundedBorder(15));
        button.setBackground(new Color(92,219,148));
        button.setForeground(new Color(5,57,107));
    }
    public static void chosedBtn(JButton btn){
        btn.setBackground(new Color(141,228,175));
        btn.setForeground(new Color(56,149,131));
    }
    
    public static void modifyFont(JComponent c){
        c.setFont(new Font("Tajawal", Font.BOLD, 16));
        c.setForeground(new Color(5,57,107));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == addBook ){
            frame.dispose();
            new AddBook();
        }
        if(ae.getSource() == updateBook ){
            frame.dispose();
            new UpdateBook();
        }
        if(ae.getSource() == deleteBook ){
            frame.dispose();
            new DeleteBook();
        }
        if(ae.getSource() == sellBook ){
            frame.dispose();
            new SellBook();
        }
        if(ae.getSource() == displayBooks ){
            frame.dispose();
            new Display();
        }
        if(ae.getSource() == home ){
            frame.dispose();
            new Start();
        }
        if(ae.getSource() == exit ){
            System.exit(0);
        }
        DBManipulating dbm = new DBManipulating();
        if(ae.getSource() == saveButton ){
            dbm.removeBook(Integer.parseInt(isbnField.getText()));
        }
        
        
    }
    
    
}
