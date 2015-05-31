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
    Spelbord bord;

    public int seconden = 0;

    public StopWatch() {
   
    }
    
    
    
    public void maakTimer(){
    Timer tijd = new Timer();

        TimerTask task = new TimerTask() {

            public void run() {

                System.out.println("yeahh");
                seconden++;
                System.out.println(seconden);

                if (seconden == 10) {
                    tijd.cancel();
                    tijd.purge();

                }
            }

        };
        tijd.scheduleAtFixedRate(task, 0, 1000);

    }
        
    

}
