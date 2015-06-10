/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.SpelElementen;

import infz.d.project.GUI.Vakje;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Method
 */
public class Muur extends SpelElement {
    //ArrayList<String> lines = new ArrayList<String>();
    private Color muurKleur = Color.decode("#003399");
    private File afbeelding = new File("src/afbeelding/muur.png");

    public Muur(Vakje vak) {
        this.vakje = vak;
        this.elementNaam = "muur";
        converteerFileNaarImage(afbeelding);
    }
    
    @Override
    public void draw(Graphics g) {
        row = vakje.getXPositie();
        column = vakje.getYPositie();

        g.drawImage(this.image, column * CELL, row * CELL, 35, 35, null);
    }
}
