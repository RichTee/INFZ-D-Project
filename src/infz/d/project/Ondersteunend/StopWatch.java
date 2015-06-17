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
import java.util.Timer;
import java.util.TimerTask;
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
    private boolean             pacmanTimerIsBezig = false;
    private int seconden = 0;
    private int secondenTwee = 0;

    public StopWatch(Spelbord spelbord) { 
        this.spelbord = spelbord;
    }
    
    public void pacmanOnverslaanbaarTimer(Pacman pacman){
        int delay = 1000;
        
        TimerTask task = new TimerTask() {
            public void run() {

                seconden++;
                System.out.println(seconden);

                if (seconden == 10) {
                    spelbord.stopMuziek();
                    spelbord.startMuziek(BACKGROUND_GELUID, true);
                    pacmanTimerIsBezig = false;
                  
                    pacman.setOnverslaanbaar(false);
                    seconden = 0;
                    tijdPacman.cancel();
                    tijdPacman.purge();
                    System.out.println("Klaar");
                }
            }
        };

        if (pacmanTimerIsBezig) {
            spelbord.stopMuziek();
            spelbord.startMuziek(SUPERBOLLETJE_GELUID, true);
            tijdPacman.cancel();
            tijdPacman.purge();
            seconden = 0;
            tijdPacman = new Timer();
            tijdPacman.scheduleAtFixedRate(task, 0, delay);

        } else {
            spelbord.stopMuziek();
            spelbord.startMuziek(SUPERBOLLETJE_GELUID, true);
            pacmanTimerIsBezig = true;
            pacman.setOnverslaanbaar(true);
            tijdPacman = new Timer();
            tijdPacman.scheduleAtFixedRate(task, 0, delay);
        }
    }
    
    public void lopenInky(Spookje spookje, int snelheid)
    {
        TimerTask task = new TimerTask() {
            public void run() {
                spookje.bewegen();
            }
        };
        tijdInky = new Timer();
        tijdInky.scheduleAtFixedRate(task, 0, snelheid);
    }
    
    public void lopenBlinky(Spookje spookje, int snelheid)
    {
        TimerTask task = new TimerTask() {
            public void run() {
                spookje.bewegen();
            }
        };
        tijdBlinky = new Timer();
        tijdBlinky.scheduleAtFixedRate(task, 0, snelheid);
    }
    
    public void lopenPinky(Spookje spookje, int snelheid)
    {
        AchtervolgendSpookje pinky = (AchtervolgendSpookje) spookje;
        TimerTask task = new TimerTask() {
            public void run() {
//                if(secondenTwee < 10) {
//                    secondenTwee++;
                    pinky.veranderStrategie("volg");
//                } else {
//                    pinky.veranderStrategie("random");
//                }
//                
                spookje.bewegen();
            }
        };
        tijdPinky = new Timer();
        tijdPinky.scheduleAtFixedRate(task, 0, snelheid);
    }
    
    public void lopenClyde(Spookje spookje, int snelheid)
    {
        AchtervolgendSpookje clyde = (AchtervolgendSpookje) spookje;
        TimerTask task = new TimerTask() {
            public void run() {
//                if(secondenTwee >= 10) {
//                    if(secondenTwee >= 20) {
//                        secondenTwee = 0;
//                    } else {
//                        secondenTwee++;
//                    }
                    clyde.veranderStrategie("random");
//                } else {
//                    clyde.veranderStrategie("random");
//                }
                spookje.bewegen();
            }
        };
        tijdClyde = new Timer();
        tijdClyde.scheduleAtFixedRate(task, 0, snelheid);   
    }
    
    public void stopLopenSpookjes(){
        // Inky
        if (tijdInky != null) {
            tijdInky.cancel();
            tijdInky.purge();
            tijdInky = null;
        }

        // Blinky
        if (tijdBlinky != null) {
            tijdBlinky.cancel();
            tijdBlinky.purge();
            tijdBlinky = null;
        }

        // Pinky
        if (tijdPinky != null) {
            tijdPinky.cancel();
            tijdPinky.purge();
            tijdPinky = null;
        }

        // Clyde
        if(tijdClyde != null ){
            tijdClyde.cancel();
            tijdClyde.purge();
            tijdClyde = null;
        }
    }
    
    public void stopTimer() {
        if (pacmanTimerIsBezig) {
            tijdPacman.cancel();
            tijdPacman.purge();
            pacmanTimerIsBezig = false;
            seconden = 0;
        } 
    }
    
    public void pauzeerTimer(){
      if (pacmanTimerIsBezig) {
            tijdPacman.cancel();
            tijdPacman.purge();
            pacmanTimerIsBezig = false;
        }
    }
}
