/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import infz.d.project.Enums.Afbeelding;
import infz.d.project.Enums.Richting;
import infz.d.project.GUI.Vakje;
/**
 *
 * @author Method
 */
public class Pacman extends Poppetje {
    private boolean             heeftSuperKracht = false;
    private boolean             teleportCooldown = false;
            
    public Pacman(Vakje vak) {
        this.vakje = vak;
        this.elementNaam = "pacman";
        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANWEST);
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
    
    @Override
    public void bewegen(Richting richting) {
        if(this.vakje.getKanTeleporteren() && !teleportCooldown){
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } else {
            switch (richting) {
                case NOORD:
                    vakje.pacmanRichting(richting);
                    this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANNOORD);
                    break;
                case OOST:
                    vakje.pacmanRichting(richting);
                     this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANOOST);
                    break;
                case ZUID:
                    vakje.pacmanRichting(richting);
                     this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANZUID);
                    break;
                case WEST:
                    vakje.pacmanRichting(richting);
                     this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANWEST);
                    break;
                default:
                    break;
            }
        teleportCooldown = false;
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
