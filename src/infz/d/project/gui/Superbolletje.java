/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Sebastiaan
 */
public class Superbolletje extends SpelElement {
    private int row;
    private int column;
    
    public Superbolletje(Vakje vakje){
    this.vakje = vakje;
    }
    
    @Override
    public void draw(Graphics g) {
        row = vakje.getXPositie();
        column = vakje.getYPositie();

        g.setColor(Color.red);
        g.fillOval(column * CELL, row * CELL, CELL / 2, CELL / 2);
        
    }
    
}
