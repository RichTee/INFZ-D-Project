/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import infz.d.project.GUI.Vakje;
import infz.d.project.SpelElementen.Pacman;
import infz.d.project.SpelElementen.SpelElement;
import infz.d.project.SpelElementen.Spookje;
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

    public VakjeTest() {
    }

//Testgevallen testmaat1:
    /**
     * Geval1
     */
    @Test
    public void testPuntenOptellenVanBuurVakje() {
        int punten = 0;
        int expResult = 800;
        Vakje vakje = new Vakje(2, 3, "inky", null);
        Vakje instance = vakje;
        int result = instance.puntenOptellenVanBuurVakje(vakje);
        punten = punten + result;
        vakje = null;
        vakje = new Vakje(2, 3, "blinky", null);
        int resultN = instance.puntenOptellenVanBuurVakje(vakje);
        punten = punten + resultN;
        vakje = null;
        vakje = new Vakje(2, 3, "pinky", null);
        int resultN2 = instance.puntenOptellenVanBuurVakje(vakje);
        punten = punten + resultN2;
        vakje = null;
        vakje = new Vakje(2, 3, "clyde", null);
        int resultN3 = instance.puntenOptellenVanBuurVakje(vakje);
        punten = punten + resultN3;
        
        assertEquals(expResult, punten);
    }

    /**
     * Geval2
//     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        Vakje vakje = new Vakje(2, 3, "pad", null);
//        Vakje instance = vakje;
//        int expResult = 0;
//        int result = instance.puntenOptellenVanBuurVakje(vakje);
//        assertEquals(expResult, result);
//
//    }
//
    /**
     * Geval3
//     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        Vakje vakje = new Vakje(2, 3, "bolletje", null);
//        Vakje instance = vakje;
//        int expResult = 10;
//        int result = instance.puntenOptellenVanBuurVakje(vakje);
//        assertEquals(expResult, result);
//
//    }

    //Testgevallen testmaat2:
    /**
//     * Geval1
////     */
//  @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        int punten = 0;
//        int expResult = 800;
//        Vakje vakje = new Vakje(2, 3, "inky", null);
//        Vakje instance = vakje;
//        int result = instance.puntenOptellenVanBuurVakje(vakje);
//        punten = punten + result;
//        vakje = null;
//        vakje = new Vakje(2, 3, "blinky", null);
//        int resultN = instance.puntenOptellenVanBuurVakje(vakje);
//        punten = punten + resultN;
//        vakje = null;
//        vakje = new Vakje(2, 3, "pinky", null);
//        int resultN2 = instance.puntenOptellenVanBuurVakje(vakje);
//        punten = punten + resultN2;
//        vakje = null;
//        vakje = new Vakje(2, 3, "clyde", null);
//        int resultN3 = instance.puntenOptellenVanBuurVakje(vakje);
//        punten = punten + resultN3;
//        
//        assertEquals(expResult, punten);
//    }

//    /**
//     * Geval2
////     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        Vakje vakje = new Vakje(2, 3, "pad", null);
//        Vakje instance = vakje;
//        int expResult = 0;
//        int result = instance.puntenOptellenVanBuurVakje(vakje);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Geval3
//     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//        Vakje vakje = new Vakje(2, 3, "blinky", null);
//        Vakje instance = vakje;
//        int expResult = 200;
//        int result = instance.puntenOptellenVanBuurVakje(vakje);
//        assertEquals(expResult, result);
//
//    }

//    /**
//     * Geval4
//     */
//    @Test
//    public void testPuntenOptellenVanBuurVakje() {
//       int punten = 0;
//        int expResult = 410;
//        Vakje vakje = new Vakje(2, 3, "inky", null);
//        Vakje instance = vakje;
//        int result = instance.puntenOptellenVanBuurVakje(vakje);
//        punten = punten + result;
//        vakje = null;
//        vakje = new Vakje(2, 3, "pinky", null);
//        int resultN = instance.puntenOptellenVanBuurVakje(vakje);
//        punten = punten + resultN;
//        vakje = null;
//        vakje = new Vakje(2, 3, "bolletje", null);
//        int resultN2 = instance.puntenOptellenVanBuurVakje(vakje);
//        punten = punten + resultN2;
//        assertEquals(expResult, punten);
//
//    }
}
