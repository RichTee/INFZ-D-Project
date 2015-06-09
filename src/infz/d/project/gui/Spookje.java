/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.util.Random;

/**
 *
 * @author Method
 */
public abstract class Spookje extends Poppetje {
    public String naam;
    
    public Spookje() { }
    
    public void setVakje(Vakje vakje){
       this.vakje = vakje;
    }
    
    public Vakje getVakje(){
        return this.vakje;
    }
    // Indien we willen zoeken, dan gaan we in random richtingen.
    public int randomRichting() {
        Random rand = new Random();
        int tempNummer = rand.nextInt(4) + 1;
        
        return tempNummer;
    }
    
    public void bewegen(){}
    

}
