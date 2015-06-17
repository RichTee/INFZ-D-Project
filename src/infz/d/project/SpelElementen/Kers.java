/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

import infz.d.project.Enums.Afbeelding;
import infz.d.project.GUI.Vakje;
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
    public Kers(Vakje vakje) {
        this.vakje = vakje;
        this.punten = 100;
        this.elementNaam = "kers";
        this.image = this.vakje.getImageLoader().selectStatischeSpelElementAfbeelding(Afbeelding.Statisch.KERS);
    }

    @Override
    public void draw(Graphics g) {
        row = vakje.getXPositie();
        column = vakje.getYPositie();
        
        g.drawImage(this.image, column * CELL, row * CELL, 35, 35, null);
    }
}
