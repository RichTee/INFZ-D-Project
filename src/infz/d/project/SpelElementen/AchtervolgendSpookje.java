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
import infz.d.project.Interfaces.AchtervolgenBewegenAlgoritme;
import infz.d.project.Interfaces.AchtervolgenBewegen;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Method
 */
public class AchtervolgendSpookje extends Spookje {
    private Vakje pacmanRichting;
    private AchtervolgenBewegen bewegen = new AchtervolgenBewegen();
    private boolean zoekenIsBezig = false;
    
    public AchtervolgendSpookje(Vakje vakje, String naam) {
        this.vakje = vakje;
        this.startPositie = vakje;
        this.naam = naam;
        this.punten = 200;
        this.elementNaam = "spookje";

        if (naam.equals("pinky")) {
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYNOORD);
        } else if (naam.equals("clyde")) {
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDENOORD);
        }
    }

    @Override
    public void bewegen() {
        if(this.vakje == null) // moet nooit kunnen
            return;
        
        if(this.vakje.getPacman() != null)
            return;
        
        if(zoekenIsBezig)
            return;
        
        checkPacman();
        
        Iterator iter = this.vakje.getBuurLijst().entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry pair = (Map.Entry) iter.next();
            if (pair.getValue() == (this.status == Status.BANG ? vluchtenBewegen.geefCell(this.vakje) : bewegen.geefCell(this.vakje))) {
                zoekenIsBezig = true;
                
                if((this.vakje == vluchtenBewegen.geefCell(this.vakje)))
                    return;
                System.out.println("Reached");
                switch (pair.getKey().toString()) {
                    case "NOORD":
                        if (vakje.getBuurLijst().containsKey(Richting.NOORD)) {
                            Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.NOORD);
                            bewegenSetters(Richting.NOORD);
                        }
                        break;
                    case "OOST":
                        if (vakje.getBuurLijst().containsKey(Richting.OOST)) {
                            Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.OOST);
                            bewegenSetters(Richting.OOST);
                        }
                        break;
                    case "ZUID":
                        if (vakje.getBuurLijst().containsKey(Richting.ZUID)) {
                            Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.ZUID);
                            bewegenSetters(Richting.ZUID);
                        }
                        break;
                    case "WEST":
                        if (vakje.getBuurLijst().containsKey(Richting.WEST)) {
                            Vakje vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.WEST);
                            bewegenSetters(Richting.WEST);
                        }
                        break;
                    default:
                        System.out.println("AchtervolgendSpookje - bewegen: " + pair.getKey().toString() + " niet gevonden!");
                        break;
                }
            }
            if(!(this.vakje.getPacman() == null) || (this.vakje == vluchtenBewegen.geefCell(this.vakje))){
                System.out.println("Reached");
                break;
            }
        }

        zoekenIsBezig = false;
        
        checkPacman();
        
        this.vakje.getSpelbord().tekenOpnieuw();
    }

    private void bewegenSetters(Richting richting) {
        Vakje vakje = null;
        this.vakje.setSpookjeNull(this);
        
        switch (richting.toString()) {
            case "NOORD":
                if (naam.equals("pinky"))
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYNOORD);
                
                if (naam.equals("clyde"))
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDENOORD);
                
                vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.NOORD);
                break;
            case "OOST":
                if (naam.equals("pinky")) 
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYOOST);
                
                if (naam.equals("clyde"))
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDEOOST);
                
                vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.OOST);
                break;
            case "ZUID":
                if (naam.equals("pinky"))
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYZUID);
                
                if (naam.equals("clyde"))
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDEZUID);
               
                vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.ZUID);
                break;
            case "WEST":
                if (naam.equals("pinky"))
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYWEST);
               
                if (naam.equals("clyde"))
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDEWEST);
                
                vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.WEST);
                break;
            default:
                System.out.println("AchtervolgendSpookje - " + this.naam + " - bewegenSetters: " + richting.toString() + " niet gevonden!" );
                break;
        }
        vakje.setSpookje(this);
        this.setVakje(vakje);
    }
    
    @Override
    public void draw(Graphics g) {
        this.row = vakje.getXPositie();
        this.column = vakje.getYPositie();

        g.drawImage(this.image, column * CELL, row * CELL, 35, 35, null);
    }
}
