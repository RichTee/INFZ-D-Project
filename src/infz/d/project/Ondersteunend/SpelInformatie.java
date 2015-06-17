/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Ondersteunend;

import infz.d.project.GUI.Spel;

/**
 *
 * @author Sebastiaan
 */
public class SpelInformatie {

    private Spel        spel;
    private int         huidigeAantalBolletjes = 0;
    private int         totaalAantalBolletjes = 0;
    private boolean     isGepauzeerd = false;
    
    public SpelInformatie(Spel spel) {
        this.spel = spel;
    }

    public void reset() {
        if(spel.getAantalLevens()==1){
           spel.resetLevens();
        }
        spel.resetScore();
        this.huidigeAantalBolletjes = 0;
    }
    
    public boolean getPauze() {
        return this.isGepauzeerd;
    }
    
    public void setPauze(boolean pauze) {
        this.isGepauzeerd = pauze;
    }
    
    public void nextLevelReset() {
        this.huidigeAantalBolletjes = 0;
        this.totaalAantalBolletjes = 0;
    }
    
    public void setScore(int score, String element){
        switch (element) {
            case "bolletje":
                this.huidigeAantalBolletjes--;
                checkKers();
                checkOfGewonnen();
                break;
            case "superbolletje":
            case "kers":
                break;
            default:
                break;      
        }
        spel.setScore(score);   
    }

    public void setScoreZonderBolletje(int score){
        spel.setScore(score);
    }
    
    public void setLevens(int leven){
        spel.setLevens(leven);
    }
    
    public void setTotaalAantalBolletjesIncrement(int aantal){
        totaalAantalBolletjes += aantal;
        huidigeAantalBolletjes = totaalAantalBolletjes;
    }
    
    public void setTotaalAantalBolletjes(int aantal) {
        this.totaalAantalBolletjes = aantal;
    }
    
    private void checkOfGewonnen() {
        if (this.huidigeAantalBolletjes == 0) {
            spel.heeftGewonnenOfVerloren("gewonnen");

        }
    }

    public boolean checkKers() {
        if (checkEvenAantalBolletjes()) {
            if (controleerRechtOpKers(0)) {     //indien het totaal aantal bolletjes oneven is en het getal gedeeld wordt door 2 dan rond java naar beneden af. 
                                                //vandaar dat we er 1 bij op tellen. anders verschijnt er voor de speler al een kers na 1 bolletje minder dan de helft
                return true;

            } else {
                return false;
            }
        } else {
            if (controleerRechtOpKers(1)) {
                return true;

            } else {
                return false;
            }
        }

    }

    private boolean checkEvenAantalBolletjes() {
        if (this.totaalAantalBolletjes % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean controleerRechtOpKers(int getal) {
        if (this.huidigeAantalBolletjes == ((this.totaalAantalBolletjes / 2)) + getal) {
            return true;
        }
        else {
            return false;
        }
    }

}
