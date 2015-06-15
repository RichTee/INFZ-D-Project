/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

import infz.d.project.Enums.Afbeelding;
import infz.d.project.Enums.Richting;

import infz.d.project.GUI.Vakje;

import infz.d.project.Interfaces.WillekeurigBewegenAlgoritme;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Method
 */
public class WillekeurigSpookje extends Spookje implements WillekeurigBewegenAlgoritme{
    private boolean             teleportCooldown = false;
    
    public WillekeurigSpookje(Vakje vakje, String naam) {
        this.vakje = vakje;
        this.startPositie = vakje;
        this.laatsteVakje = vakje;
        this.naam = naam; 
        this.punten = 200;
        this.elementNaam = "spookje";

        if (naam.equals("inky")) {
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.INKY);
        } else if (naam.equals("blinky")) {
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BLINKY);
        }
    }

    @Override
    public void bewegen() {
        if(this.vakje.getKanTeleporteren() && !teleportCooldown){
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } else {
            switch(willekeurigBewegen()){
                case NOORD:
                    vakje.spookjeRichting(Richting.NOORD, this);
                    break;
                case OOST:
                    vakje.spookjeRichting(Richting.OOST, this);
                    break;
                case ZUID:
                    vakje.spookjeRichting(Richting.ZUID, this);
                    break;
                case WEST:
                    vakje.spookjeRichting(Richting.WEST, this);
                    break;
                default:
                    System.out.println("Tempnummer is wrong");
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

    @Override
    public Richting willekeurigBewegen() {
        Random random = new Random();
        ArrayList<Richting> openRichting = new ArrayList<>();
        Iterator iter = this.vakje.getBuurLijst().entrySet().iterator();

        while(iter.hasNext()) {
            Map.Entry pair = (Map.Entry)iter.next();
            if(pair.getValue() != laatsteVakje) {
                switch(pair.getKey().toString()){
                    case "NOORD":
                        openRichting.add(Richting.NOORD);
                        break;
                    case "OOST":
                        openRichting.add(Richting.OOST);
                        break;
                    case "ZUID":
                        openRichting.add(Richting.ZUID);
                        break;
                    case "WEST":
                        openRichting.add(Richting.WEST);
                        break;
                }
            }
        }
        
        int tempNummer = random.nextInt(openRichting.size());
        
        return openRichting.get(tempNummer);
    }
}
