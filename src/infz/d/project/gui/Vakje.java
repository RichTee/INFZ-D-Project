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
                        System.out.println("Vakje Element : " + vakje.getElement());
                        vakje.setElement(this.getElement());
                        this.setElement("pad");
                        pacman.setVakje(vakje);
                        spelbord.tekenOpnieuw();
                        break;
                    }
                }
            }
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

    public void setElement(String element) {
        this.element = element;
    }
    
    // Logischer in Spelbord voor minder Memory intake en makkelijkere toegang.
    private void vulSpelElementList() {
        muur = new Muur(this);
        spookje = new Spookje(this);
        pacman = new Pacman(this);
      
        spelElementList.add(muur);      // 1
        spelElementList.add(spookje);   // 4
        spelElementList.add(pacman);    // 5

    }

    // Draw Logic | Vakje moet zich kleuren op basis van inhoud.
    public void draw(Graphics g) {
       vulSpelElementList();   // We vullen een array met classes erin

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
                break;
            case "superbolletje":
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
