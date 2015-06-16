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
import java.util.Timer;
/**
 *
 * @author Method
 */
public class Pacman extends Poppetje {
    private boolean             heeftSuperKracht    = false;
    private boolean             teleportCooldown    = false;
    private int                 spelPunten          = 0;
    public  boolean             magLopen            = true;
    
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
        if(!magLopen)
            return;

        this.checkSpookje();
        
        // bewegen
        if (this.vakje.getKanTeleporteren() && !teleportCooldown) {
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } else {
            switch (richting) {
                case NOORD:
                    if(vakje.getBuurLijst().containsKey(Richting.NOORD)){
                        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.NOORD);
                        this.vakje.pacman = null;
                        vakje.setElement("pacman", this);
                        this.setVakje(vakje);
                        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANNOORD);   
                    }
                    break;
                case OOST:
                    if(vakje.getBuurLijst().containsKey(Richting.OOST)){
                        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.OOST);
                        this.vakje.pacman = null;
                        vakje.setElement("pacman", this);
                        this.setVakje(vakje);
                        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANOOST);   
                    }
                    break;
                case ZUID:
                    if(vakje.getBuurLijst().containsKey(Richting.ZUID)){
                        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.ZUID);
                        this.vakje.pacman = null;
                        vakje.setElement("pacman", this);
                        this.setVakje(vakje);
                        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANZUID);   
                    }
                    break;
                case WEST:
                    if(vakje.getBuurLijst().containsKey(Richting.WEST)){
                        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.WEST);
                        this.vakje.pacman = null;
                        vakje.setElement("pacman", this);
                        this.setVakje(vakje);
                        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANWEST);   
                    }
                    break;
                default:
                    break;
            }
            teleportCooldown = false;
        }
        // einde bewegen
        // checkSpookje
        this.checkSpookje();
        // einde checkSpookje
        // checkElement
        this.checkElement();
        // einde checkElement
                
    }
    
    private void checkSpookje() {
        if (vakje.getSpookje("inky") != null || vakje.getSpookje("blinky") != null
                || vakje.getSpookje("pinky") != null || vakje.getSpookje("clyde") != null) {

            if (this.getKracht()) {
                vakje.puntenOptellenVanVakje(vakje);
                if (vakje.getSpookje("inky") != null) {
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("inky"));
                    vakje.setSpookjeNull(vakje.getSpookje("inky"));
                }
                if (vakje.getSpookje("blinky") != null) {
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("blinky"));
                    vakje.setSpookjeNull(vakje.getSpookje("blinky"));
                }
                if (vakje.getSpookje("pinky") != null) {
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("pinky"));
                    vakje.setSpookjeNull(vakje.getSpookje("pinky"));
                }
                if (vakje.getSpookje("clyde") != null) {
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("clyde"));
                    vakje.setSpookjeNull(vakje.getSpookje("clyde"));
                }
                this.vakje.getSpelbord().tekenOpnieuw();
                this.vakje.getSpelbord().setSpelInformatie(punten, 0, "");
            } else if ((!this.getKracht()) && vakje.getResetProcess() != true) {
                if (vakje.getSpookje("inky") != null) {
                    vakje.setResetProcess(true);
                    vakje.getSpelbord().resetPacman();
                    vakje.getSpelbord().setSpelInformatie(0, -1, "");
                    
                } else if (vakje.getSpookje("blinky") != null) {
                    vakje.setResetProcess(true);
                    vakje.getSpelbord().resetPacman();
                    vakje.getSpelbord().setSpelInformatie(0, -1, "");
                    
                } else if (vakje.getSpookje("pinky") != null) {
                    vakje.setResetProcess(true);
                    vakje.getSpelbord().resetPacman();
                    vakje.getSpelbord().setSpelInformatie(0, -1, "");
                    
                } else if (vakje.getSpookje("clyde") != null) {
                    vakje.setResetProcess(true);
                    vakje.getSpelbord().resetPacman();
                    vakje.getSpelbord().setSpelInformatie(0, -1, "");
                }
                this.vakje.getSpelbord().tekenOpnieuw();
                vakje.setResetProcess(false);
            }
        }
    }

    private void checkElement() {
        switch (vakje.getElement()) {
            case "muur": // Indien muur = Doe niks
                break;
            case "pad":
                break;
            case "bolletje":
            case "kers":
                spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                this.vakje.getSpelbord().maakKersInLegeVakje();
                this.vakje.setElement("pad", null);
                this.vakje.getSpelbord().setSpelInformatie(spelPunten, 0, "bolletje");
                break;
            case "superbolletje":
                spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                this.vakje.setElement("pad", null);
                this.vakje.getSpelbord().geefPacmanSuperkracht(this);
                this.vakje.getSpelbord().setSpelInformatie(spelPunten, 0, "superbolletje");
                break;
            default:
                break;
        }
        this.vakje.getSpelbord().tekenOpnieuw();
    }

    @Override
    public void draw(Graphics g) {
        this.row = vakje.getXPositie();
        this.column = vakje.getYPositie();
        
        g.drawImage(this.image, column * CELL, row * CELL, 35, 35, null);
    }

    // Moet in speelbord
}
