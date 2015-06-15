/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Ondersteunend;

import static infz.d.project.Enums.Geluid.*;
import infz.d.project.GUI.Spel;
import infz.d.project.GUI.Spelbord;
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

    public StopWatch(Spelbord spelbord) { 
        this.spelbord = spelbord;
    }
    
    
    public void pacmanOnverslaanbaarTimer(Pacman pacman){
        int delay = 500;
        
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

        TimerTask task = new TimerTask() {
            public void run() {
                spookje.bewegen();
            }
        };
        tijdPinky = new Timer();
        tijdPinky.scheduleAtFixedRate(task, 0, snelheid);
        
    }
    
    public void lopenClyde(Spookje spookje, int snelheid)
    {

        TimerTask task = new TimerTask() {
            public void run() {
                spookje.bewegen();
            }
        };
        tijdClyde = new Timer();
        tijdClyde.scheduleAtFixedRate(task, 0, snelheid);
        
    }
    
    public void stopLopenSpookjes(){
        // Inky
        tijdInky.cancel();
        tijdInky.purge();
        
        // Blinky
        tijdBlinky.cancel();
        tijdBlinky.purge();
        
        // Pinky
        //tijdPinky.cancel();
        //tijdPinky.purge();
        
        // Clyde
        //tijdClyde.cancel();
        //tijdClyde.purge();
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
