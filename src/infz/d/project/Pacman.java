/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Method
 */

    // Pacman Extend entiteit(abstract)
    public class Pacman extends Entiteit {
        Image pacman;
        int xPos;
        int yPos;
        public Pacman() {
            xPos = 100;
            yPos = 100;
        }
        public void draw(Graphics g){
            try {
                g.drawImage(ImageIO.read(new File("afbeelding/Pacman_1.png")), xPos, yPos, 50, 50, null);
            } catch (IOException ex) {
                Logger.getLogger(PacmanWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void move(Direction direction){       
        switch (direction){
            case NORTH:
                yPos-=10;
                break;
            case SOUTH:
                yPos+=10;
                break;
            case WEST:
                xPos-=10;
                break;
            case EAST:
                xPos+=10;
                break;
        }
    }
    }
