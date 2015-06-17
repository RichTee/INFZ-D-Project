/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import infz.d.project.GUI.Vakje;
import infz.d.project.Ondersteunend.ImageLoader;
import infz.d.project.SpelElementen.AchtervolgendSpookje;
import infz.d.project.SpelElementen.Pacman;
import infz.d.project.SpelElementen.SpelElement;
import infz.d.project.SpelElementen.Spookje;
import infz.d.project.SpelElementen.WillekeurigSpookje;
import java.awt.Graphics;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastiaan
 */
public class VakjeTest {

    ImageLoader imageLoader;
    Pacman pacman;
    Vakje vakje;

    public VakjeTest() {
    }

    private void maakObjectenLeeg() {
        imageLoader = null;
        pacman = null;
        vakje = null;
    }

//Testgevallen testmaat1:
    /**
     * Geval1 //
     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        int punten = 0;
//        int expResult = 800;
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "inky", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int result = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + result;
//        maakObjectenLeeg();
//
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "blinky", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int resultN = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + resultN;
//        maakObjectenLeeg();
//
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "pinky", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int resultN2 = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + resultN2;
//        maakObjectenLeeg();
//
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "clyde", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int resultN3 = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + resultN3;
//     maakObjectenLeeg();
//        
//        assertEquals(expResult, punten);
//    }
//    /**
//     * Geval2
//     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        int expResult = 0;
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "pad", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int result = vakje.puntenOptellenVanVakje(vakje);
//        maakObjectenLeeg();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Geval3 //
//     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        int expResult = 10;
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "bolletje", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//
//        int result = vakje.puntenOptellenVanVakje(vakje);
//        maakObjectenLeeg();
//        assertEquals(expResult, result);
//
//    }
    //Testgevallen testmaat2:
    /**
     * // * Geval1 ////
     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        int punten = 0;
//        int expResult = 800;
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "inky", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int result = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + result;
//        maakObjectenLeeg();
//
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "blinky", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int resultN = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + resultN;
//        maakObjectenLeeg();
//
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "pinky", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int resultN2 = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + resultN2;
//        maakObjectenLeeg();
//
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "clyde", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int resultN3 = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + resultN3;
//          maakObjectenLeeg();
//
//        assertEquals(expResult, punten);
//    }
//    /**
//     * Geval2
////     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        int expResult = 0;
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "pad", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int result = vakje.puntenOptellenVanVakje(vakje);
//          maakObjectenLeeg();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Geval3
//     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        int expResult = 200;
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "blinky", null, imageLoader);
//        Vakje instance = vakje;
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int result = instance.puntenOptellenVanVakje(vakje);
//        maakObjectenLeeg();
//        assertEquals(expResult, result);
//    }
//    /**
//     * Geval4
//     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        int punten = 0;
//        int expResult = 410;
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "inky", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int result = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + result;
//        maakObjectenLeeg();
//
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "pinky", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int resultN = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + resultN;
//        maakObjectenLeeg();
//
//        imageLoader = new ImageLoader();
//        vakje = new Vakje(2, 3, "bolletje", null, imageLoader);
//        pacman = new Pacman(vakje);
//        vakje.setPacman(pacman);
//        pacman.setOnverslaanbaar(true);
//        int resultN2 = vakje.puntenOptellenVanVakje(vakje);
//        punten = punten + resultN2;
//
//        maakObjectenLeeg();
//        assertEquals(expResult, punten);
//
//    }
}