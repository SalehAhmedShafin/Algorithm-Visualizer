/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author Asus
 */
public class Main {
   
   public static void main(String[] args) throws InterruptedException {   
       
    Home btn = new Home();
    Setter st = new Setter();
    btn.setVisible(true);
    
    
    
    JButton button=btn.jButton2;
        button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              
             st.setx(1);
              
          }
        });
        
    JButton buttons= btn.jButton3;
        buttons.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              
             st.sety(1);
              
          }
        });
   
    
    while(st.getx()==0)
    {
        
        if(st.getx()>0)
            break;
            
    }
    
    FoodFillVisualization ffv= new FoodFillVisualization ();
    ffv.setVisible(true);
    System.out.println("2nd Loop ");

    while(st.gety()==0)
    {
        
        if(st.gety()>0)
            break;
            
    }
    
    UnionFindVisualization uf = new UnionFindVisualization();
    uf.setVisible(true);
   
    
}
    
}
