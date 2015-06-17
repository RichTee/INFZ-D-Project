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
import infz.d.project.Interfaces.AchtervolgenBewegen;
import infz.d.project.Interfaces.AchtervolgenBewegenAlgoritme;
import infz.d.project.Interfaces.VluchtenBewegen;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Method
 */
public class AchtervolgendSpookje extends Spookje {
    private Vakje pacmanRichting;
    private AchtervolgenBewegen bewegen;
    private boolean zoekenIsBezig = false;
    private String strategie;
    private int count = 0;

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

    public void veranderStrategie(String s){
        strategie = s;
    }
    
    @Override
    public void bewegen() {
        if(this.vakje == null) // moet nooit kunnen
            return;
        
        if(this.vakje.getPacman() != null) {
            checkPacman();
            return;
        }
        
        if(zoekenIsBezig)
            return;
        
        if(this.vakje.getSpelbord().getPacman() != null ? this.vakje.getSpelbord().getPacman().getKracht() : false) {
            this.status = Status.BANG;
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BANG);
        } else {
            this.status = Status.LEVEND;
        }
        
        
        Iterator iter = this.vakje.getBuurLijst().entrySet().iterator();
        if (this.vakje.getKanTeleporteren() && !teleportCooldown) {
            this.vakje.setSpookjeNull(this);
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        }
        
        if(strategie.equals("volg")){
            bewegen = new AchtervolgenBewegen();
            zoekenIsBezig = true;
            while (iter.hasNext()) {
             Vakje target;
            Map.Entry pair = (Map.Entry) iter.next();
            if(this.status == Status.BANG) {
                vluchtenBewegen = new VluchtenBewegen();
                target = vluchtenBewegen.geefCell(this.vakje);
            } else
                target = bewegen.geefCell(this.vakje);
            if (pair.getValue() == target ) {
                
                if((this.vakje == target))
                    return;
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

                if(!(this.vakje.getPacman() == null)){
                    break;
                }
            }
            teleportCooldown = false;
        } else {
            System.out.println("Random");
            switch(willekeurigBewegen()){
                case NOORD:
                    bewegenSetters(Richting.NOORD);
                    break;
                case OOST:
                    bewegenSetters(Richting.OOST);
                    break;
                case ZUID:
                    bewegenSetters(Richting.ZUID);
                    break;
                case WEST:
                    bewegenSetters(Richting.WEST);
                    break;
                default:
                    System.out.println("Tempnummer is wrong");
                    break;
            }
            teleportCooldown = false;
        }

        zoekenIsBezig = false;
        
        checkPacman();
        
        if (this.vakje.getKanTeleporteren() && !teleportCooldown) {
            this.vakje.setSpookjeNull(this);
            this.vakje.teleporteerBewegend(this);
            teleportCooldown = true;
        } 
        
        this.vakje.getSpelbord().tekenOpnieuw();
    }

    private Richting willekeurigBewegen() {
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
    
    private void bewegenSetters(Richting richting) {
        Vakje vakje = null;
        this.vakje.setSpookjeNull(this);
        
        switch (richting.toString()) {
            case "NOORD":
                if (naam.equals("pinky") && this.status != Status.BANG)
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYNOORD);
                
                if (naam.equals("clyde") && this.status != Status.BANG)
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDENOORD);
                
                vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.NOORD);
                break;
            case "OOST":
                if (naam.equals("pinky") && this.status != Status.BANG) 
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYOOST);
                
                if (naam.equals("clyde") && this.status != Status.BANG)
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDEOOST);
                
                vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.OOST);
                break;
            case "ZUID":
                if (naam.equals("pinky") && this.status != Status.BANG)
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYZUID);
                
                if (naam.equals("clyde") && this.status != Status.BANG)
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDEZUID);
               
                vakje = (Vakje) this.vakje.getBuurLijst().get(Richting.ZUID);
                break;
            case "WEST":
                if (naam.equals("pinky") && this.status != Status.BANG)
                    this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKYWEST);
               
                if (naam.equals("clyde") && this.status != Status.BANG)
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
