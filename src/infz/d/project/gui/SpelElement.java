/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Method
 */
public abstract class SpelElement {
    public final int        CELL = 50;
    public int              punten = 0;
    public int              row;
    public int              column;
    public Vakje            vakje;

    public SpelElement() { }
    
    public int getPunten() {
        return punten;
    }
    
    public void draw(Graphics g) {}
}
