/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Graphics;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 *
 * @author Method
 */
public abstract class Spookje extends Poppetje {
    String naam;
    public Spookje() { }
    
    public void setVakje(Vakje vakje){
       this.vakje = vakje;
    }
     
    public int getXPositie() {
        return row;
    }
    
    public int getYPositie() {
        return column;
    }
    // Indien we willen zoeken, dan gaan we in random richtingen.
    public int randomRichting() {
        Random rand = new Random();
        int tempNummer = rand.nextInt(4) + 1;
        
        return tempNummer;
    }
    
    public void bewegen(){}
    

}
