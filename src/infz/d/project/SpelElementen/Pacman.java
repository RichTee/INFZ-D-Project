/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import infz.d.project.Enums.Richting;
import infz.d.project.GUI.Vakje;
/**
 *
 * @author Method
 */
public class Pacman extends Poppetje {
    private boolean             heeftSuperKracht = false;
    private final File          afbeeldingNoord = new File("src/afbeelding/PacmanNoord.png");
    private final File          afbeeldingOost = new File("src/afbeelding/PacmanOost.png");
    private final File          afbeeldingZuid = new File("src/afbeelding/PacmanZuid.png");
    private final File          afbeeldingWest = new File("src/afbeelding/PacmanWest.png");
    private Image[]             afbeeldingPad;
    
    public Pacman(Vakje vak) {
        this.vakje = vak;
        this.elementNaam = "pacman";
        
        afbeeldingPad = new Image[4];
        converteerFileNaarImage(afbeeldingNoord);
        afbeeldingPad[0] = this.image;
        converteerFileNaarImage(afbeeldingOost);
        afbeeldingPad[1] = this.image;
        converteerFileNaarImage(afbeeldingZuid);
        afbeeldingPad[2] = this.image;
        converteerFileNaarImage(afbeeldingWest);
        afbeeldingPad[3] = this.image;
    }
    
    public void setOnverslaanbaar(boolean kracht){
        this.heeftSuperKracht = kracht;
    }
    
    public boolean getKracht(){
        return heeftSuperKracht;
    }

    public void setVakje(Vakje vakje) {
        this.vakje = vakje;
    }
    
    public Vakje getVakje(){
        return vakje;
    }
    
    public void bewegen(Richting richting) {
        switch (richting) {
            case NOORD:
                vakje.pacmanRichting("NOORD");
                this.setImage(afbeeldingPad[0]);
                break;
            case OOST:
                vakje.pacmanRichting("OOST");
                 this.setImage(afbeeldingPad[1]);
                break;
            case ZUID:
                vakje.pacmanRichting("ZUID");
                 this.setImage(afbeeldingPad[2]);
                break;
            case WEST:
                vakje.pacmanRichting("WEST");
                 this.setImage(afbeeldingPad[3]);
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        this.row = vakje.getXPositie();
        this.column = vakje.getYPositie();
        
        g.drawImage(this.image, column * CELL, row * CELL, 35, 35, null);
    }

    // Moet in speelbord
}
