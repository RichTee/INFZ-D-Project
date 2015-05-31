/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import sun.java2d.loops.FillRect;

/**
 *
 * @author Method
 */
public class Vakje {
    int xPositie;
    int yPositie;
    String element;
    ArrayList<Vakje> buurVakje = new ArrayList<>();
    ArrayList<SpelElement> spelElementList = new ArrayList<>();
    Spelbord spelbord;
    Muur muur;
    Spookje spookje;
    Pacman pacman;
    Bolletje bolletje;
    Superbolletje superbolletje;
    
    public Vakje(int xPositie, int yPositie, String element, Spelbord spelBord) {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.element = element;
        this.spelbord = spelBord;
        vulSpelElementList();
    }

    public void voegtBuurToe(Vakje vakje) {
        buurVakje.add(vakje);
    }

    public void pacmanRichting(int xpos, int ypos) {
        
        xpos = xpos + xPositie;
        ypos = ypos + yPositie;
        
        for (Vakje vakje : buurVakje) {
            if (vakje.getXPositie() == xpos) {
                if (vakje.getYPositie() == ypos) {
                    if (!vakje.isMuur()) {
                        checkElement(vakje);
                    }
                }
            }
        }
    }

    private void checkElement(Vakje vakje){
        if (vakje.getElement().equals("bolletje"))
        {
            spelbord.setScore(vakje.getSpelElement().getPunten());
            spelbord.checkOfKersKan();
            vakje.setElement(this.getElement(), pacman);
            this.setElement("pad", null);
            pacman.setVakje(vakje);
            spelbord.tekenOpnieuw();

        } else if (vakje.getElement().equals("superbolletje")) {
            spelbord.geefPacmanSuperkracht();
            vakje.setElement(this.getElement(), pacman);
            this.setElement("pad", null);
            pacman.setVakje(vakje);
            spelbord.tekenOpnieuw();

        } else if (vakje.getElement().equals("pad")) {
            vakje.setElement(this.getElement(), null);
            this.setElement("pad", null);
            pacman.setVakje(vakje);
            spelbord.tekenOpnieuw();

        } else if (vakje.getElement().equals("spookje") && pacman.getKracht() == true) {
            spelbord.setScore(vakje.getSpelElement().getPunten());
            vakje.setElement(this.getElement(), pacman);
            this.setElement("pad", null);
            pacman.setVakje(vakje);
            spelbord.tekenOpnieuw();

        } else if (vakje.getElement().equals("spookje") && pacman.getKracht() == false) {
            System.out.println("VERLOREN");
            spelbord.setLevens();
            spelbord.resetPoppetje();
            spelbord.tekenOpnieuw();

        }
    }
    // return of vakje een muur
    public boolean isMuur() {
        if (element.equals("muur")) {
            return true;
        } else {
            return false;
        }
    }

    // Positie van vakje(X)
    public int getXPositie() {
        return xPositie;
    }

    // Positie van vakje(Y)
    public int getYPositie() {
        return yPositie;
    }

    // Return SpelElement (muur of poppetje..)
    public String getElement() {
        return element;
    }

    public void setElement(String element, SpelElement spelElement) {
        if (spelElement == null) {
            this.element = element;
        } else {
            if (element.equals("pacman")) {
                this.element = element;
                this.pacman = (Pacman) spelElement;
                spelElementList.add(spelElement);
            } else if (element.equals("bolletje")) {
                this.element = element;
                spelElementList.add(spelElement);
            } else if (element.equals("spookje")) {
                this.element = element;
                spelElementList.add(spelElement);
            } else if (element.equals("superbolletje")) {
                this.element = element;
                spelElementList.add(spelElement);
            }
            // Andere SpelElementen later.
        }
    }
    
    
    public SpelElement getSpelElement(){
        if(element.equals("bolletje")){
        return bolletje; // Moet globaal, SpelElement, niet specifiek.
        }
        else if(element.equals("superbolletje")){
        return superbolletje;
        }
        else if(element.equals("spookje")){
        return spookje;
        }
        else return null;
    }
    // Logischer in Spelbord voor minder Memory intake en makkelijkere toegang.
    private void vulSpelElementList() {
        muur = new Muur(this);
        spookje = new Spookje(this);
        pacman = new Pacman(this);
        bolletje = new Bolletje(this);
        superbolletje = new Superbolletje(this);
      
        spelElementList.add(muur);          // 1
        spelElementList.add(bolletje);      // 2
        spelElementList.add(superbolletje); // 3
        spelElementList.add(spookje);       // 4
        spelElementList.add(pacman);        // 5
       
    }

    // Draw Logic | Vakje moet zich kleuren op basis van inhoud.
    public void draw(Graphics g) {
//       vulSpelElementList();   // We vullen een array met classes erin

        switch (element) {
            case "pad":
                // g.setColor(Color.orange);
                // g.fillRect(yPositie * 50/*yPositie*/, xPositie * 50/*xPositie*/, 50, 50);
                break;
            case "muur":
                if (spelElementList.contains(muur)) {
                    muur.draw(g);
                }
                break;
            case "bolletje":
                if (spelElementList.contains(bolletje)){
                    bolletje.draw(g);
                }
                break;
            case "superbolletje":
                if (spelElementList.contains(superbolletje)){
                    superbolletje.draw(g);
                }
                break;
            case "spookje":
                if (spelElementList.contains(spookje)) {
                    spookje.draw(g);
                }
                break;
            case "pacman":
                if (spelElementList.contains(pacman)) {
                    pacman.draw(g);
                }
                break;
            default:
                break;
        }
    }
}
