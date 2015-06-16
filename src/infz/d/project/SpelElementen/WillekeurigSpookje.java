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
        
        checkPacman();
        
        if(this.vakje.getKanTeleporteren() && !teleportCooldown){
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } else {
            switch(willekeurigBewegen()){
                case NOORD:
                    if(vakje.getBuurLijst().containsKey(Richting.NOORD)){
                        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.NOORD);
                        this.vakje.setSpookjeNull(this);
                        vakje.setSpookje(this);
                        this.setVakje(vakje); 
                    }
                    break;
                case OOST:
                    if(vakje.getBuurLijst().containsKey(Richting.OOST)){
                        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.OOST);
                        this.vakje.setSpookjeNull(this);
                        vakje.setSpookje(this);
                        this.setVakje(vakje); 
                    }
                    break;
                case ZUID:
                    if(vakje.getBuurLijst().containsKey(Richting.ZUID)){
                        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.ZUID);
                        this.vakje.setSpookjeNull(this);
                        vakje.setSpookje(this);
                        this.setVakje(vakje); 
                    }
                    break;
                case WEST:
                    if(vakje.getBuurLijst().containsKey(Richting.WEST)){
                        Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.WEST);
                        this.vakje.setSpookjeNull(this);
                        vakje.setSpookje(this);
                        this.setVakje(vakje); 
                    }
                    break;
                default:
                    System.out.println("Tempnummer is wrong");
                    break;
            }
            teleportCooldown = false;
        }
        
        checkPacman();
        
        this.vakje.getSpelbord().tekenOpnieuw();
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
