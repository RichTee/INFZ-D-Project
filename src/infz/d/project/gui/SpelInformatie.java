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

    Spel spel;
    private int huidigeAantalBolletjes = 0;
    private int totaalAantalBolletjes = 0;

    public SpelInformatie(Spel spel) {
        this.spel = spel;
    }

    public void setScore(int punten) {
        spel.setScore(punten);
        checkKers();
        checkOfGewonnen();
    }

    public void setLevens() {
        spel.setLevens(-1);
    }

    public void reset() {
        spel.resetLevens();
        spel.resetScore();
        this.huidigeAantalBolletjes = this.totaalAantalBolletjes;
    }

    public void setTotaalAantalBolletjesIncrement(int aantal) {
        this.totaalAantalBolletjes += aantal;
        this.huidigeAantalBolletjes = this.totaalAantalBolletjes;
    }

    public void setKers() {

    }

    public void setTotaalAantalBolletjes(int totaalAantalBolletjes) {
        this.totaalAantalBolletjes = totaalAantalBolletjes;
    }

    public void setHuidigeAantalBolletjes(int huidigeBolletjes) {
        this.huidigeAantalBolletjes += huidigeBolletjes;
    }

    public int getTotaalAantalBolletjes() {
        return totaalAantalBolletjes;
    }

    public void checkOfGewonnen() {
        if (this.huidigeAantalBolletjes == 0) {
            System.out.println("GEWONNEN");

        }
         else{
            System.out.println("OKE" + this.huidigeAantalBolletjes);
        }
    }

    public void checkKers() {
        if (this.huidigeAantalBolletjes == ((this.totaalAantalBolletjes / 2) + 1)) {
            System.out.println("KERS");
        }
        else{
            System.out.println("OKE" + this.huidigeAantalBolletjes);
        }
    }

}
