/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.SpelElementen;

import infz.d.project.Enums.Afbeelding;
import infz.d.project.Enums.Status;
import infz.d.project.GUI.Vakje;
import infz.d.project.Interfaces.VluchtenBewegen;

import java.util.Random;

import infz.d.project.Interfaces.VluchtenBewegenAlgoritme;
/**
 *
 * @author Method
 */
public abstract class Spookje extends Poppetje {
    public String naam;
    public Vakje startPositie;
    public Vakje laatsteVakje;
    public Status status;
    protected boolean teleportCooldown = false;
    public VluchtenBewegen vluchtenBewegen;
    
    public Spookje() { }
    
    public void setVakje(Vakje vakje){
       this.laatsteVakje = this.vakje;
       this.vakje = vakje;
    }
    
    public Vakje getVakje(){
        return this.vakje;
    }
    
    public Status getStatus() {
        return this.status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
        this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.BANG);
    }
    
    // Indien we willen zoeken, dan gaan we in random richtingen.
    public int randomRichting() {
        Random rand = new Random();
        int tempNummer = rand.nextInt(4) + 1;
        
        return tempNummer;
    }
    
    public void checkPacman() { 
        if(this.vakje.getPacman() != null){
            if(!this.vakje.getPacman().getKracht() && vakje.getResetProcess() != true) {
                vakje.setResetProcess(true);
                vakje.getSpelbord().resetPacman();
                vakje.getSpelbord().resetAlleSpookjes();
                vakje.pacman = null; // getter setter?
                vakje.getSpelbord().setSpelInformatie(0, -1, "");
                
                vakje.setResetProcess(false);
            }
        }
    }

    public void bewegen(){}
    

}
