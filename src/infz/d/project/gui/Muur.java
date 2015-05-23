/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Method
 */
public class Muur extends SpelElement {
    ArrayList<String> lines = new ArrayList<String>();
    int row;
    int column;
    Color muurKleur = Color.decode("#003399");
    
    public Muur(Vakje vak) {
        this.vakje = vak;
    }
    
    @Override
    public void draw(Graphics g) {
        row = vakje.getXPositie();
        column = vakje.getYPositie();
        
        // debug
        if(row == 3 && column == 2){
            g.setColor(Color.RED);
        } else {
            g.setColor(muurKleur);
        }
        
        g.fillRect(column * CELL, row * CELL, CELL, CELL);  
    }
}
