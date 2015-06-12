/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Ondersteunend;

import infz.d.project.SpelElementen.Pacman;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import infz.d.project.Enums.Richting;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Method
 */
public class KeyboardListener implements KeyListener {
    public Pacman               pacman;
    private Timer               timer;
    
    public KeyboardListener(Pacman pacman) {
        this.pacman = pacman;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) { }

    @Override
    public void keyReleased(KeyEvent ke) {
        int delay = 500;
        // Reset alleen timer wanneer we de nieuwe richting op kunnen (CHECKEN IN PACMAN->VAKJE)
        // ActionListener gebruiken + bool
        if(timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
        
        TimerTask task = new TimerTask() {
            public void run() {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        pacman.bewegen(Richting.NOORD);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacman.bewegen(Richting.OOST);
                        break;
                    case KeyEvent.VK_DOWN:
                        pacman.bewegen(Richting.ZUID);
                        break;
                    case KeyEvent.VK_LEFT:
                        pacman.bewegen(Richting.WEST);
                        break;
                }
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, delay);
    }

    @Override
    public void keyPressed(KeyEvent ke) { }
}
