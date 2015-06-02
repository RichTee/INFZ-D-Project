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
public abstract class Poppetje extends SpelElement{
    
    public Poppetje() { }
    
    public enum Richting {
        NOORD,
        OOST,
        ZUID,
        WEST,
    };
    
    public void bewegen(Richting richting) { }
}
