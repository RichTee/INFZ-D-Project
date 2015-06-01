/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

/**
 *
 * @author Sebastiaan
 */
public class SpelInformatie {

    private Spel        spel;
    private int         huidigeAantalBolletjes = 0;
    private int         totaalAantalBolletjes = 0;

    public SpelInformatie(Spel spel) {
        this.spel = spel;
    }

    public void reset() {
        spel.resetLevens();
        spel.resetScore();
        this.huidigeAantalBolletjes = this.totaalAantalBolletjes;
    }
    
    public void setScore(int score){
        spel.setScore(score);
        this.huidigeAantalBolletjes--;
        checkKers();
        checkOfGewonnen();
    }

    public void setScoreZonderBolletje(int score){
        spel.setScore(score);
    }
    
    public void setLevens(int leven){
        System.out.println("Called");
        spel.setLevens(leven);
    }
    
    public void setTotaalAantalBolletjesIncrement(int aantal){
        totaalAantalBolletjes += aantal;
        huidigeAantalBolletjes = totaalAantalBolletjes;
    }
    
    public void setTotaalAantalBolletjes(int aantal) {
        this.totaalAantalBolletjes = aantal;
    }
    
    public void checkOfGewonnen() {
        if (this.huidigeAantalBolletjes == 0) {
            System.out.println("GEWONNEN");

        }
    }

    public void checkKers() {
        if (this.huidigeAantalBolletjes == ((this.totaalAantalBolletjes / 2) + 1)) {
            System.out.println("KERS");
        }
    }

}
