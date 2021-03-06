/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.SpelElementen;

import infz.d.project.GUI.Vakje;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Method
 */
public abstract class SpelElement {
    public final int        CELL = 35;
    public int              punten = 0;
    public int              row;
    public int              column;
    public String           elementNaam;
    public Vakje            vakje;
    public Image            image;
    
    public SpelElement() { }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public int getPunten() {
        return punten;
    }
    
    public void draw(Graphics g) {}
    
    @Override
    public String toString() {
        return this.elementNaam;
    }
}
