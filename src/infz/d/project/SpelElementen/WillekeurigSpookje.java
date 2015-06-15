/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

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
    private final File      imgInky = new File("src/afbeelding/inky.png");
    private final File      imgBlinky = new File("src/afbeelding/blinky.png");
    
    public WillekeurigSpookje(Vakje vakje, String naam) {
        this.vakje = vakje;
        this.startPositie = vakje;
        this.laatsteVakje = vakje;
        this.naam = naam; 
        this.punten = 200;
        this.elementNaam = "spookje";

        if (naam.equals("inky")) {
            converteerFileNaarImage(imgInky);
        } else if (naam.equals("blinky")) {
            converteerFileNaarImage(imgBlinky);
        }
    }

    @Override
    public void bewegen() {  
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
