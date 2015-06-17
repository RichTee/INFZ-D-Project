/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Ondersteunend;

import static infz.d.project.Enums.Geluid.*;
import infz.d.project.GUI.Spel;
import infz.d.project.GUI.Spelbord;
import infz.d.project.SpelElementen.AchtervolgendSpookje;
import infz.d.project.SpelElementen.Pacman;
import infz.d.project.SpelElementen.SpelElement;
import infz.d.project.SpelElementen.Spookje;
import infz.d.project.SpelElementen.WillekeurigSpookje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.Timer;
/**
 *
 * @author Sebastiaan
 */
public class StopWatch {
    private Spelbord            spelbord;
    private Timer               tijdPacman;
    private Timer               tijdInky;
    private Timer               tijdBlinky;
    private Timer               tijdPinky;
    private Timer               tijdClyde;
    private SpookjeBeweegListener          spookjeListener;
    private stomSpookjeBeweegListener   stomSpookjeListener;
    private PacmanBeweegListener pacmanListener;
    private boolean             pacmanTimerIsBezig = false;
    private int seconden = 0;
    private int secondenTwee = 0;

    public StopWatch(Spelbord spelbord) { 
        this.spelbord = spelbord;
    }
    
    public void pacmanOnverslaanbaarTimer(Pacman pacman){

        if (pacmanTimerIsBezig) {
            spelbord.stopMuziek();
            spelbord.startMuziek(SUPERBOLLETJE_GELUID, true);
            tijdPacman.stop();
            seconden = 0;
            pacmanListener = new PacmanBeweegListener();
            tijdPacman  = new Timer(0, pacmanListener);
            tijdPacman.setDelay(1000);
            pacmanListener.setPacman(pacman);
            tijdPacman.start();

        } else {
            spelbord.stopMuziek();
            spelbord.startMuziek(SUPERBOLLETJE_GELUID, true);
            pacmanTimerIsBezig = true;
            pacman.setOnverslaanbaar(true);
            pacmanListener = new PacmanBeweegListener();
            tijdPacman = new Timer(0, pacmanListener);
            tijdPacman.setDelay(1000);
            pacmanListener.setPacman(pacman);
            tijdPacman.start();
        }
    }
    
    public void lopenInky(Spookje spookje, int snelheid)
    {
        stomSpookjeListener = new stomSpookjeBeweegListener();
        tijdInky = new Timer(0, stomSpookjeListener);
        tijdInky.setDelay(snelheid);
        stomSpookjeListener.setSpookje(spookje);
        tijdInky.start();
    }
    
    public void lopenBlinky(Spookje spookje, int snelheid)
    {
        stomSpookjeListener = new stomSpookjeBeweegListener();
        tijdBlinky = new Timer(0, stomSpookjeListener);
        tijdBlinky.setDelay(snelheid);
        stomSpookjeListener.setSpookje(spookje);
        tijdBlinky.start();
    }
    
    public void lopenPinky(Spookje spookje, int snelheid)
    {
        spookjeListener = new SpookjeBeweegListener();
        spookjeListener.setSpookje(spookje);
        tijdPinky = new javax.swing.Timer(snelheid, spookjeListener);
        tijdPinky.start();
    }
    
    public void lopenClyde(Spookje spookje, int snelheid)
    {
        spookjeListener = new SpookjeBeweegListener();
        spookjeListener.setSpookje(spookje);
        tijdClyde = new javax.swing.Timer(snelheid, spookjeListener);
        tijdClyde.start();
    }
    
    public void stopLopenSpookjes(){
        // Inky
        if (tijdInky != null) {
            tijdInky.stop();
        }

        // Blinky
        if (tijdBlinky != null) {
            tijdBlinky.stop();
        }

        // Pinky
        if (tijdPinky != null) {
            tijdPinky.stop();
        }

        // Clyde
        if(tijdClyde != null ){
            tijdClyde.stop();
        }
    }
    
    public void stopTimer() {
        if (pacmanTimerIsBezig) {
            tijdPacman.stop();
            pacmanTimerIsBezig = false;
            seconden = 0;
        } 
    }
    
    public void pauzeerTimer(){
      if (pacmanTimerIsBezig) {
            tijdPacman.stop();
            pacmanTimerIsBezig = false;
        }
    }
    class PacmanBeweegListener implements ActionListener {
        private Pacman pacman;
        public void setPacman(Pacman pacman) {
            this.pacman = pacman;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            seconden++;
                System.out.println(seconden);

                if (seconden == 10) {
                    spelbord.stopMuziek();
                    spelbord.startMuziek(BACKGROUND_GELUID, true);
                    pacmanTimerIsBezig = false;
                  
                    pacman.setOnverslaanbaar(false);
                    seconden = 0;
                    tijdPacman.stop();
                    System.out.println("Klaar");
                }
        }
    }
    class stomSpookjeBeweegListener implements ActionListener {

        private WillekeurigSpookje inky;
        private WillekeurigSpookje blinky;
        
        public void setSpookje(Spookje spookje) {
            if(spookje.naam.equals("clyde"))
                this.inky = (WillekeurigSpookje) spookje;
            else
                this.blinky = (WillekeurigSpookje) spookje;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(inky != null) {
                inky.bewegen();
            }
            if(blinky != null){
                blinky.bewegen();
            }
        }
    }
    class SpookjeBeweegListener implements ActionListener {

        private AchtervolgendSpookje clyde;
        private AchtervolgendSpookje pinky;
        
        public void setSpookje(Spookje spookje) {
            if(spookje.naam.equals("clyde"))
                this.clyde = (AchtervolgendSpookje) spookje;
            else
                this.pinky = (AchtervolgendSpookje) spookje;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(clyde != null) {
                clyde.veranderStrategie("volg");
                clyde.bewegen();
            }
            if(pinky != null){
                pinky.veranderStrategie("volg");
                pinky.bewegen();
            }
        }
    }

}
