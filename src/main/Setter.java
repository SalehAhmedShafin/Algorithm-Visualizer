/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Asus
 */
public class Setter {
    
    public int x;
     public int y;
    
    
    public void Seltter(){
        
    }
    
    public void setx(int x){
        this.x=x; 
        System.out.println("Insert");
    }
    
    public int getx(){
        System.out.println("Out");
        return x;
    }
    
    public void sety(int y){
        this.y=y; 
        System.out.println("Insert y");
    }
    
    public int gety(){
        System.out.println("Out y");
        return y;
    }
      
}
