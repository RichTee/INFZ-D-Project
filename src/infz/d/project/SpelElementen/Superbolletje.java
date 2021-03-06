/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

import infz.d.project.Enums.Afbeelding;
import infz.d.project.GUI.Vakje;
import java.awt.Graphics;

import java.io.File;

/**
 *
 * @author Sebastiaan
 */
public class Superbolletje extends SpelElement {
    
    public Superbolletje(Vakje vakje){
        this.vakje = vakje;
        this.punten = 10;
        this.elementNaam = "superbolletje";
        this.image = this.vakje.getImageLoader().selectStatischeSpelElementAfbeelding(Afbeelding.Statisch.SUPERBOLLETJE);
    }
    
    @Override
    public void draw(Graphics g) {
        row = vakje.getXPositie();
        column = vakje.getYPositie();

        g.drawImage(this.image, column * CELL, row * CELL, 35, 35, null);
    }
    
}
