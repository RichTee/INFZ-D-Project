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
    
    public Muur(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(muurKleur);
        g.fillRect(column*CELLSIZE, row*CELLSIZE, CELLSIZE, CELLSIZE);  
    }
}
