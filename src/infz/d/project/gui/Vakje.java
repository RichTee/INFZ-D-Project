/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

/**
 *
 * @author Method
 */
public class Vakje {
    int xPositie;
    int yPositie;
    boolean muur;
    String element;
    SpelElement spelelement;
    Pacman pacman;
    
    public Vakje(int xPositie, int yPositie, String element) {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.element = element;
        if(element.equals("muur"))
            muur = true;
        else
            muur = false;
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
        return muur;
    }
    
    // Positie van vakje(X)
    public int getXPositie() {
        return xPositie;
    }
    
    // Positie van vakje(Y)
    public int getYPositie() {
        return yPositie;
    }
}
