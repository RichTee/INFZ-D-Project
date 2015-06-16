/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Enums;

/**
 *
 * @author Method
 */
public class Afbeelding {

    public enum Statisch {
        PAD,
        MUUR,
        BOLLETJE,
        SUPERBOLLETJE,
        KERS
    }

    public enum Pacman {
        PACMANNOORD,
        PACMANOOST,
        PACMANZUID,
        PACMANWEST,
    }

    public enum Spookje {
        // Inky
        INKYNOORD,
        INKYOOST,
        INKYZUID,
        INKYWEST,
        
        // Blinky
        BLINKYNOORD,
        BLINKYOOST,
        BLINKYZUID,
        BLINKYWEST,
        
        // Pinky
        PINKYNOORD,
        PINKYOOST,
        PINKYZUID,
        PINKYWEST,
        
        // Clyde
        CLYDENOORD,
        CLYDEOOST,
        CLYDEZUID,
        CLYDEWEST,
    }
}
