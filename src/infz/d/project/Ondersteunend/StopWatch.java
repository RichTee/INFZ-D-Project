/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Ondersteunend;

import infz.d.project.GUI.Spelbord;
import infz.d.project.SpelElementen.Spookje;
import infz.d.project.SpelElementen.Pacman;
import infz.d.project.GUI.Spel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Sebastiaan
 */
public class StopWatch {
    private Spel                spel;
    private Spelbord            spelbord;
    private Timer               tijdPacman;
    private Timer               tijdSpookjes;
    private boolean             pacmanTimerIsBezig = false;
    
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
                    spelbord.stopMuziek();
                    spelbord.startMuziek("C:\\Users\\Sebastiaan\\Documents\\GitHub\\INFZ-D-Project\\geluid\\siren_sound.wav", true);
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
            spelbord.startMuziek("C:\\Users\\Sebastiaan\\Documents\\GitHub\\INFZ-D-Project\\geluid\\pacman_intermission.wav", true);
            tijdPacman.cancel();
            tijdPacman.purge();
            seconden = 0;
            tijdPacman = new Timer();
            tijdPacman.scheduleAtFixedRate(task, 0, delay);

        } else {
            spelbord.stopMuziek();
            spelbord.startMuziek("C:\\Users\\Sebastiaan\\Documents\\GitHub\\INFZ-D-Project\\geluid\\pacman_intermission.wav", true);
            pacmanTimerIsBezig = true;
            pacman.setOnverslaanbaar(true);
            tijdPacman = new Timer();
            tijdPacman.scheduleAtFixedRate(task, 0, delay);
        }
    }
    
    public void lopenSpookjes(Spookje spookje)
    {
        int delay = 500;
        
        TimerTask task = new TimerTask() {
            public void run() {
                spookje.bewegen();
                System.out.println("Spookje locatie X: " + spookje.getVakje().getXPositie() + " Y: " + spookje.getVakje().getYPositie());
            }
        };

        tijdSpookjes = new Timer();
        tijdSpookjes.scheduleAtFixedRate(task, 0, delay);
    }
    
    public void stopLopenSpookjes(){
        tijdSpookjes.cancel();
        tijdSpookjes.purge();
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
