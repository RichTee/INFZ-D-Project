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

/**
 *
 * @author Method
 */
public class Vakje {
    int xPositie;
    int yPositie;
    private final int CELL = 50;
    String element;
    ArrayList<SpelElement> spelElementList = new ArrayList<>();
    Muur muur;
    Spookje spookje;
    Pacman pacman;

    public Vakje(int xPositie, int yPositie, String element) {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.element = element;
    }
    
    public void pacmanRichting(int row, int column) {
        row = xPositie + row;
        column = yPositie + column;
        Vakje buur;
        if(/*buur.isMuur() 8 */true) {
            
        } else {
            
        }
    }

    // return of vakje een muur
    public boolean isMuur() {
        if(element.equals("muur"))
            return true;
        else
            return false;
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
    
    // Logischer in Spelbord voor minder Memory intake en makkelijkere toegang.
    private void vulSpelElementList() {
        muur = new Muur(this);
        spookje = new Spookje();
        pacman = new Pacman(this);
        
        spelElementList.add(muur);      // 1
        spelElementList.add(spookje);   // 4
        spelElementList.add(pacman);    // 5
        
    }

    // Draw Logic | Vakje moet zich kleuren op basis van inhoud.
    public void draw(Graphics g){
        vulSpelElementList();   // We vullen een array met classes erin
        
        switch(element){
            case "pad":
                break;
            case "muur":
                if(spelElementList.contains(muur))
                    muur.draw(g);
                break;
            case "bolletje":
                break;
            case "superbolletje":
                break;
            case "spookje":
                break;
            case "pacman":
                if(spelElementList.contains(pacman))
                    //pacman.draw(g);
                break;
            default:
                break;
        }
    }
    
    // For funsies (Misschien later wanneer Spookjes moeten gloeien op interval)
    private Color randomKleur(){
        Random rand = new Random();
        
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        
        Color randomColor = new Color(r, g, b);
        return randomColor;
    }
}
