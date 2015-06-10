/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

import infz.d.project.GUI.Vakje;
import java.awt.Graphics;
import java.io.File;

import infz.d.project.Interfaces.AchtervolgenBewegenAlgoritme;

/**
 *
 * @author Method
 */
public class AchtervolgendSpookje extends Spookje implements AchtervolgenBewegenAlgoritme{
    private File imgPinky = new File("src/afbeelding/pinky.png");
    private File imgClyde = new File("src/afbeelding/clyde.png");
    
    public AchtervolgendSpookje(Vakje vakje, String naam) {
        this.vakje = vakje;
        this.naam = naam;
        this.punten = 200;
        this.elementNaam = "spookje";

        if (naam.equals("pinky")) {
            converteerFileNaarImage(imgPinky);
        } else if (naam.equals("clyde")) {
            converteerFileNaarImage(imgClyde);
        }
    }
    
    @Override
    public void bewegen() {
        // Interval vereist zodat ze niet sneller lopen dan mogelijk, bijv 3 vakjes per x seconden.
        
        switch(randomRichting()){
            case 1:
                System.out.println("NOORD");
                vakje.spookjeRichting("NOORD", this);
                break;
            case 2:
                System.out.println("OOST");
                vakje.spookjeRichting("OOST", this);
                break;
            case 3:
                System.out.println("ZUID");
                vakje.spookjeRichting("ZUID", this);
                break;
            case 4:
                System.out.println("WEST");
                vakje.spookjeRichting("WEST", this);
                break;
            default:
                System.out.println("Tempnummer is wrong");
                break;
        }
    }
    
    @Override
    public void draw(Graphics g) {
        this.row = vakje.getXPositie();
        this.column = vakje.getYPositie();

        g.drawImage(this.image, column * CELL, row * CELL, 35, 35, null);
    }
}
