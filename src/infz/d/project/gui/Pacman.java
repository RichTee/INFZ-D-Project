/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Method
 */
public class Pacman extends Poppetje {
    private boolean heeftSuperKracht = false;
    private int count = 0;
    private final int STAP = 1;
    private final int STIL = 0;
    private int row;
    private int column;
    
    public Pacman(Vakje vak) {
        this.vakje = vak;
    }
    
    public void setKracht(boolean kracht){
        this.heeftSuperKracht = kracht;
    }
    
    public boolean getKracht(){
        return heeftSuperKracht;
    }

    public void setVakje(Vakje vakje) {
        System.out.println("Vakje: " + vakje.getXPositie() + " " + vakje.getYPositie());
        this.vakje = vakje;
    }
    
    public Vakje getVakje(){
    return vakje;
    }

    public int pacmanKolom() {
        return this.column;
    }

    public int pacmanRow() {
        return this.row;
    }

    public void bewegen(Richting richting) {
        switch (richting) {
            case NOORD:
                vakje.pacmanRichting(-STAP, STIL);
                break;
            case OOST:
                vakje.pacmanRichting(STIL, STAP);
                break;
            case ZUID:
                vakje.pacmanRichting(STAP, STIL);
                break;
            case WEST:
                vakje.pacmanRichting(STIL, -STAP);
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        
        row = vakje.getXPositie();
        column = vakje.getYPositie();
        if (count == 1) {
            System.out.println("Boolean: " + count);
            count = 0;

        } else {
            System.out.println("Boolean: " + count);
            count++;
        }


        try {
            g.drawImage(ImageIO.read(new File("afbeelding/Pacman_1.png")), column * CELL, row * CELL, 50, 50, null);
        } catch (IOException ex) {
            Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Moet in speelbord
}
