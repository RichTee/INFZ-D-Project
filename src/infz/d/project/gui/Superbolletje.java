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
public class Superbolletje extends SpelElement {
    
    public Superbolletje(Vakje vakje){
        this.vakje = vakje;
        this.punten = 10;
    }
    
    @Override
    public void draw(Graphics g) {
        row = vakje.getXPositie();
        column = vakje.getYPositie();

        try {
            g.drawImage(ImageIO.read(new File("afbeelding/superbolletje.png")), column * CELL, row * CELL, 50, 50, null);
        } catch (IOException ex) {
            Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
