/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

import infz.d.project.Enums.Afbeelding;
import infz.d.project.Enums.Richting;
import infz.d.project.Enums.Status;

import infz.d.project.GUI.Vakje;
import infz.d.project.Interfaces.VluchtenBewegen;
import infz.d.project.Interfaces.WillekeurigBewegen;

import java.awt.Graphics;


/**
 *
 * @author Method
 */
public class WillekeurigSpookje extends Spookje {
    private WillekeurigBewegen willekeurigBewegen;
    
    public WillekeurigSpookje(Vakje vakje, String naam) {
        this.vakje = vakje;
        this.startPositie = vakje;
        this.laatsteVakje = vakje;
        this.naam = naam;
        this.punten = 200;
        this.elementNaam = "spookje";
        if(this.status == null)
            this.status = Status.LEVEND;
        
        if (naam.equals("inky")) {
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.INKYOOST);
        } else if (naam.equals("blinky")) {
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BLINKYOOST);
        }
    }

    @Override
    public void bewegen() {
        if(this.vakje.getSpelbord().getPacman() != null ? this.vakje.getSpelbord().getPacman().getKracht() : false) {
            this.status = Status.BANG;
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BANG);
        } else{
            this.status = Status.LEVEND;
        }
        
        checkPacman();

        if (this.vakje.getKanTeleporteren() && !teleportCooldown) {
            this.vakje.setSpookjeNull(this);
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } else {
            if(this.status != Status.BANG){
                willekeurigBewegen = new WillekeurigBewegen();
                willekeurigBewegen();
            } else {
                willekeurigBewegen();
                //vluchtenBewegen = new VluchtenBewegen();
                //vluchtenBewegen.geefCell(this.vakje);
            }
            teleportCooldown = false;
        }

        checkPacman();

        if (this.vakje.getKanTeleporteren() && !teleportCooldown) {
            this.vakje.setSpookjeNull(this);
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } 
        
        this.vakje.getSpelbord().tekenOpnieuw();
    }

    private void willekeurigBewegen() {
        switch (willekeurigBewegen.willekeurigBewegen(vakje, laatsteVakje)) {
            case NOORD:
                if (vakje.getBuurLijst().containsKey(Richting.NOORD)) {
                    Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.NOORD);
                    this.vakje.setSpookjeNull(this);
                    vakje.setSpookje(this);
                    this.setVakje(vakje);
                    if (naam.equals("inky") && getStatus() != Status.BANG) {
                        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.INKYNOORD);
                    }
                    if (naam.equals("blinky") && getStatus() != Status.BANG) {
                        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BLINKYNOORD);
                    }
                }
                break;
            case OOST:
                if (vakje.getBuurLijst().containsKey(Richting.OOST)) {
                    Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.OOST);
                    this.vakje.setSpookjeNull(this);
                    vakje.setSpookje(this);
                    this.setVakje(vakje);
                    if (naam.equals("inky") && getStatus() != Status.BANG) {
                        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.INKYOOST);
                    }
                    if (naam.equals("blinky") && getStatus() != Status.BANG) {
                        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BLINKYOOST);
                    }
                }
                break;
            case ZUID:
                if (vakje.getBuurLijst().containsKey(Richting.ZUID)) {
                    Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.ZUID);
                    this.vakje.setSpookjeNull(this);
                    vakje.setSpookje(this);
                    this.setVakje(vakje);
                    if (naam.equals("inky") && getStatus() != Status.BANG) {
                        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.INKYZUID);
                    }
                    if (naam.equals("blinky") && getStatus() != Status.BANG) {
                        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BLINKYZUID);
                    }
                }
                break;
            case WEST:
                if (vakje.getBuurLijst().containsKey(Richting.WEST)) {
                    Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.WEST);
                    this.vakje.setSpookjeNull(this);
                    vakje.setSpookje(this);
                    this.setVakje(vakje);
                    if (naam.equals("inky") && getStatus() != Status.BANG) {
                        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.INKYWEST);
                    }
                    if (naam.equals("blinky") && getStatus() != Status.BANG) {
                        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BLINKYWEST);
                    }
                }
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
