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
 * @author Sebastiaan
 */
public class Kers extends SpelElement {
    private int         row;
    private int         column;
    private File afbeelding = new File("afbeelding/kers.png");
    
    public Kers(Vakje vakje) {
        this.vakje = vakje;
        this.punten = 100;
        converteerFileNaarImage(afbeelding);
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
        
        g.drawImage(this.image, column * CELL, row * CELL, 50, 50, null);
    }
}
