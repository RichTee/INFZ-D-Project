/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Method
 */
public class KeyboardListener implements KeyListener {

    public Pacman pacman;

    public KeyboardListener(Pacman pacman) {
        this.pacman = pacman;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) { }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                pacman.bewegen(Pacman.Richting.NOORD);
                break;
            case KeyEvent.VK_RIGHT:
                pacman.bewegen(Pacman.Richting.OOST);
                break;
            case KeyEvent.VK_DOWN:
                pacman.bewegen(Pacman.Richting.ZUID);
                break;
            case KeyEvent.VK_LEFT:
                pacman.bewegen(Pacman.Richting.WEST);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) { }
}
