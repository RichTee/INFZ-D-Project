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
import infz.d.project.Enums.Status;
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
    private Richting            richting;
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
    
    public Richting getRichting() {
        return richting;
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
        
        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(richting);
        
        if(vakje == null)
            return;
        
        // bewegen
        if (this.vakje.getKanTeleporteren() && !teleportCooldown) {
            checkSpookje();
            checkElement();
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } else {
            switch (richting) {
                case NOORD:
                case OOST:
                case ZUID:
                case WEST:
                    this.vakje.pacman = null;
                    vakje.setElement("pacman", this);
                    this.setVakje(vakje);
                    if(richting == Richting.NOORD)  
                        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANNOORD);
                    else if (richting == Richting.OOST)
                        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANOOST);
                    else if (richting == Richting.ZUID)
                        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANZUID);
                    else if (richting == Richting.WEST)
                        this.image = this.vakje.getImageLoader().selectPacmanAfbeelding(Afbeelding.Pacman.PACMANWEST);
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
        
        if (this.vakje.getKanTeleporteren() && !teleportCooldown) {
            checkSpookje();
            checkElement();
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } 
                
    }
    
    private void checkSpookje() {
        if (vakje.getSpookje("inky") != null || vakje.getSpookje("blinky") != null
                || vakje.getSpookje("pinky") != null || vakje.getSpookje("clyde") != null) {

            if (this.getKracht()) {
                vakje.puntenOptellenVanVakje(vakje);
                if (vakje.getSpookje("inky") != null) {
                    spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("inky"));
                    vakje.setSpookjeNull(vakje.getSpookje("inky"));
                }
                if (vakje.getSpookje("blinky") != null) {
                    spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("blinky"));
                    vakje.setSpookjeNull(vakje.getSpookje("blinky"));
                }
                if (vakje.getSpookje("pinky") != null) {
                    spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("pinky"));
                    vakje.setSpookjeNull(vakje.getSpookje("pinky"));
                }
                if (vakje.getSpookje("clyde") != null) {
                    spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("clyde"));
                    vakje.setSpookjeNull(vakje.getSpookje("clyde"));
                }
                this.vakje.getSpelbord().tekenOpnieuw();
                this.vakje.getSpelbord().setSpelInformatie(spelPunten, 0, "");
            } else if ((!this.getKracht()) && vakje.getResetProcess() != true) {
                if (vakje.getSpookje("inky") != null) {
                    System.out.println("Opgegeten door Inky!");
                    vakje.setResetProcess(true);
                    vakje.getSpelbord().resetPacman();
                    vakje.getSpelbord().setSpelInformatie(0, -1, "");
                    
                } else if (vakje.getSpookje("blinky") != null) {
                    System.out.println("Opgegeten door Blinky!");
                    vakje.setResetProcess(true);
                    vakje.getSpelbord().resetPacman();
                    vakje.getSpelbord().setSpelInformatie(0, -1, "");
                    
                } else if (vakje.getSpookje("pinky") != null) {
                    System.out.println("Opgegeten door Pinky!");
                    vakje.setResetProcess(true);
                    vakje.getSpelbord().resetSpookje(this.vakje.getSpookje("pinky"));
                    vakje.getSpelbord().resetPacman();
                    vakje.getSpelbord().setSpelInformatie(0, -1, "");
                    
                } else if (vakje.getSpookje("clyde") != null) {
                    System.out.println("Opgegeten door Clyde!");
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
                spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                this.vakje.getSpelbord().maakKersInLegeVakje();
                this.vakje.setElement("pad", null);
                this.vakje.getSpelbord().setSpelInformatie(spelPunten, 0, "bolletje");
                break;
            case "kers":
                spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                this.vakje.setElement("pad", null);
                this.vakje.getSpelbord().setSpelInformatie(spelPunten, 0, "");
                break;
            case "superbolletje":
                spelPunten = this.vakje.puntenOptellenVanVakje(vakje);
                this.vakje.setElement("pad", null);
                this.vakje.getSpelbord().geefPacmanSuperkracht(this);
                this.vakje.getSpelbord().setSpelInformatie(spelPunten, 0, "");
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
}
