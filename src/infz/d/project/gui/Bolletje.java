/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Method
 */
public class Bolletje extends SpelElement {
    private int row;
    private int column;
    
    public Bolletje(Vakje vakje) {
        this.vakje = vakje;
        this.punten = 10;
    }
    
    @Override
    public void draw(Graphics g) {
        row = vakje.getXPositie();
        column = vakje.getYPositie();

        g.setColor(Color.YELLOW);
        g.fillOval(column * CELL, row * CELL, CELL / 2, CELL / 2);
        
    }
}
