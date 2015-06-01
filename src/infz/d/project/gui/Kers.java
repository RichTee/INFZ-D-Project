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
public class Kers extends SpelElement {
    private int         row;
    private int         column;

    public Kers(Vakje vakje) {
        this.vakje = vakje;
        this.punten = 100;
    }
    
    public void setVakje(Vakje vakje) {
        this.vakje = vakje;
    }
    
    public Vakje getVakje(){
        return vakje;
    }

    @Override
    public void draw(Graphics g) {
        row = vakje.getXPositie();
        column = vakje.getYPositie();

        g.setColor(Color.green);
        g.fillOval(column * CELL, row * CELL, CELL / 2, CELL / 2);

    }
}
