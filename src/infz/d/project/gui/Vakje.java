/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Method
 */
public class Vakje {
    int xPositie;
    int yPositie;
    private final int CELL = 50;
    String element;
    SpelElement spelelement;
    Pacman pacman;
    
    public Vakje(int xPositie, int yPositie, String element) {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.element = element;
    }
    
    public void pacmanRichting(int row, int column) {
        row = xPositie + row;
        column = yPositie + column;
        Vakje buur;
        if(/*buur.isMuur() 8 */true) {
            
        } else {
            
        }
    }

    // return of vakje een muur
    public boolean isMuur() {
        if(element.equals("muur"))
            return true;
        else
            return false;
    }
    
    // Positie van vakje(X)
    public int getXPositie() {
        return xPositie;
    }
    
    // Positie van vakje(Y)
    public int getYPositie() {
        return yPositie;
    }
    
    // Return SpelElement (muur of poppetje..)
    public String getElement() {
        return element;
    }
    
    // Draw Logic | Vakje moet zich kleuren op basis van inhoud.
    public void draw(Graphics g){
        g.setColor(randomKleur());
        g.fillRect(xPositie * CELL /*Positie * cellGrootte*/, 
                   yPositie * CELL/*Positie * cellHoogte*/, 
                   CELL/*Breedte*/, 
                   CELL/*Hoogte*/);
    }
    
    // For funsies
    private Color randomKleur(){
        Random rand = new Random();
        
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        
        Color randomColor = new Color(r, g, b);
        return randomColor;
    }
}
