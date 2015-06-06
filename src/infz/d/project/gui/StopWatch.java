/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Sebastiaan
 */
public class StopWatch {
    private Spel                spel;
    private Spelbord            spelbord;
    private Timer               tijd;
    private Timer               tijdSpookjes;
    
    private boolean             timerIsBezig = false;
    
    private int seconden = 0;

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
                    timerIsBezig = false;
                    pacman.setOnverslaanbaar(false);
                    seconden = 0;
                    tijd.cancel();
                    tijd.purge();
                    System.out.println("Klaar");
                }
            }
        };

        if (timerIsBezig) {
            tijd.cancel();
            tijd.purge();
            seconden = 0;
            tijd = new Timer();
            tijd.scheduleAtFixedRate(task, 0, delay);

        } else {
            timerIsBezig = true;
            pacman.setOnverslaanbaar(true);
            tijd = new Timer();
            tijd.scheduleAtFixedRate(task, 0, delay);
        }
    }
        
    
    public void stopTimer() {
        if (timerIsBezig) {
            tijd.cancel();
            tijd.purge();
            timerIsBezig = false;
            seconden = 0;
        }
    }
    
    public void lopenSpookjes(Spookje spookje)
    {
    int delay = 1000;
        
        TimerTask task = new TimerTask() {
            public void run() {
               
                System.out.println("spookje");

                
            }
        };

     tijdSpookjes = new Timer();
     tijdSpookjes.scheduleAtFixedRate(task, 0, delay);
    }
}
