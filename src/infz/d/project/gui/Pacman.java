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
public class Pacman extends Poppetje implements KeyListener {

    final int STAP = 1;
    final int STIL = 0;
    public enum Richting {

        NOORD,
        OOST,
        ZUID,
        WEST,
    };

    private void bewegen(Richting richting) {
        switch(richting){
            case NOORD:
                vakje.pacmanRichting(- STAP, STIL);
                break;
            case OOST:
                vakje.pacmanRichting(STIL, + STAP);
                break;
            case ZUID:
                vakje.pacmanRichting(-STAP, STIL);
                break;
            case WEST:
                vakje.pacmanRichting(STIL, -STAP);
                break;
            default:
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("Test: key typed");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("Test: key released");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_R:
                break;
            case KeyEvent.VK_UP:
                // Methode / Nieuw vakje
                bewegen(Richting.NOORD);
                break;
            case KeyEvent.VK_RIGHT:
                bewegen(Richting.OOST);
                break;
            case KeyEvent.VK_DOWN:
                bewegen(Richting.ZUID);
                break;
            case KeyEvent.VK_LEFT:
                bewegen(Richting.WEST);
                break;
        }
    }
}

