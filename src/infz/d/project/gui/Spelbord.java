/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Method
 */
public class Spelbord extends javax.swing.JPanel {
    private SpelInformatie      spelInformatie;
    private Pacman              pacman;
    private Kers                kers;
    private Spel                spel;
    private Timer               tijd;
    private boolean             timerIsBezig = false;
    private boolean             repaintIsBezig = false;
    private int                 counter = 0;
    private int                 xPos, yPos; // Positie
    private int                 arrayBreedte, arrayHoogte; // Cell dimensies
    private int                 seconden = 0;
    private final static int    CELL = 50;
    private Vakje[][]           vakje;
    private ArrayList<String>   vakjesInhoud = new ArrayList<String>();
    private Border              lineBorder = BorderFactory.createLineBorder(Color.black);
    
    /**
     * Creates new form Spelbord
     */
    public Spelbord() {
        initComponents();
        
        genereerSpelbordPanelGegevens();
        
        KeyListener listener = new KeyboardListener();
        this.addKeyListener(listener);
        this.setFocusable(true);
    }

    // Panel gegevens
    private void genereerSpelbordPanelGegevens() {
        getSpelbordTxtFile();
        genereerGuiMap();
        isBuur();
        setPreferredSize(new Dimension(CELL * arrayBreedte, CELL * arrayHoogte));
        this.setBorder(lineBorder);
    }

    // Snelle start voor Spel om de veld te activeren met het spel.
    public void start() {
        reset();
        
        genereerSpelbordPanelGegevens();
        this.requestFocusInWindow();
    }
    
    public void herstart() {
        reset();
        
        genereerSpelbordPanelGegevens();
        this.requestFocusInWindow();
    }
 
    public void reset() {
        stopTimer();
  
        spelInformatie.setTotaalAantalBolletjes(0);
        spelInformatie.reset();

        this.vakjesInhoud.clear();
        
        for( int i = 0; i < vakje.length; i++ )
            vakje[i] = null;
        
        repaint();
    }
    
    private void stopTimer() {
        if (timerIsBezig) {
            tijd.cancel();
            tijd.purge();
            timerIsBezig = false;
            seconden = 0;
        }
    }

    // Moet anders kunnen...
    public void setSpel(Spel spel) {
        this.spel = spel;
        spelInformatie = new SpelInformatie(spel);
    }

    // Get posities van entiteiten die een spel vormen uit de txt file.
    private void getSpelbordTxtFile() {
        try {
            Scanner read = new Scanner(new File("txt/maze.txt"));
            int lineCount = 0;
            while (read.hasNextLine()) {
                String line = read.nextLine();
                vakjesInhoud.add(line);
                if (line.contains("5")) {
                    yPos = lineCount;
                    xPos = line.indexOf('5');
                }
                lineCount++;
            }
            read.close();

            arrayHoogte = vakjesInhoud.size();
            arrayBreedte = vakjesInhoud.get(0).length();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Genereer de map voor de GUI nadat de TXT is gelezen
    private void genereerGuiMap() {
        spelInformatie = new SpelInformatie(spel);
        vakje = new Vakje[arrayHoogte][arrayBreedte];
        
        for (int row = 0; row < arrayHoogte; row++) {
            for (int column = 0; column < arrayBreedte; column++) {
                switch (String.valueOf(charAt(row, column))) {
                    case "0":
                        vakje[row][column] = new Vakje(row, column, "pad", this);
                        break;
                    case "1":
                        // muur
                        vakje[row][column] = new Vakje(row, column, "muur", this);
                        break;
                    case "2":
                        // Bolletje
                        vakje[row][column] = new Vakje(row, column, "bolletje", this);
                        spelInformatie.setTotaalAantalBolletjesIncrement(1);
                        break;
                    case "3":
                        // Superbolletje
                        vakje[row][column] = new Vakje(row, column, "superbolletje", this);
                        break;
                    case "4":
                        // Spookje
                        vakje[row][column] = new Vakje(row, column, "spookje", this);
                        break;
                    case "5":
                        // Pacman
                        vakje[row][column] = new Vakje(row, column, "pacman", this);
                        //pacman = new Pacman(vakje[row][column]);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    // Elk vakje moet zijn buren weten
    public void isBuur() {
        for (int i = 0; i < arrayHoogte; i++) {
            for (int j = 0; j < arrayBreedte; j++) {
               isBuurOutOfBounds(i, j);
            }
        }
    }

    // Niet elk vakje heeft een even aantal buren die in een array zit, met try catch vangen we excepties.
    private void isBuurOutOfBounds(int xPos, int yPos) {
        try {
            // Noord
            vakje[xPos-1][yPos].getElement();
            vakje[xPos][yPos].voegtBuurToe(vakje[xPos-1][yPos]);
            //System.out.println(xPos + "  " + yPos);
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("xPos-1 : " + e );
        }
        try {
            // Oost
            vakje[xPos][yPos+1].getElement();
            vakje[xPos][yPos].voegtBuurToe(vakje[xPos][yPos+1]);
            //System.out.println(xPos + "  " + yPos);
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("xPos+1 : " + e );
        }
        try {
            // Zuid
            vakje[xPos+1][yPos].getElement();
            vakje[xPos][yPos].voegtBuurToe(vakje[xPos+1][yPos]);
            //System.out.println(xPos + "  " + yPos);
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("yPos-1 : " + e );
        }
        try {
            // West
            vakje[xPos][yPos-1].getElement();
            vakje[xPos][yPos].voegtBuurToe(vakje[xPos][yPos-1]);
            //System.out.println(xPos + "  " + yPos);
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("yPos+1 : " + e );
        }
    }
    
    public void setSpelInformatie(int score, int levens, String bolletje) {
        if(!bolletje.equals(""))
            spelInformatie.setScore(score);
        else if(levens == 0)
            spelInformatie.setScoreZonderBolletje(score);
        else
            spelInformatie.setLevens(levens);
        
        
    }
    
    public void maakKers() {
        ArrayList<Vakje> legeVakjes = new ArrayList<Vakje>();

        if (spelInformatie.checkKers()) {
            for (int i = 0; i < arrayHoogte; i++) {
                for (int j = 0; j < arrayBreedte; j++) {
                    if (vakje[i][j].getElement().equals("pad")) {
                        legeVakjes.add(vakje[i][j]);

                    }
                }
            }
 
            int lengteList = legeVakjes.size();
            int randomGetal = getRandomIndex(lengteList);
            int XPositie = legeVakjes.get(randomGetal).getXPositie();
            int YPositie = legeVakjes.get(randomGetal).getYPositie();
            
            vakje[XPositie][YPositie].setElement("kers", kers);
            kers = (Kers) vakje[XPositie][YPositie].getSpelElement();
            kers.setVakje(vakje[XPositie][YPositie]);
            
            legeVakjes.clear();

        }
    }


    private int getRandomIndex(int lengte)
    {
        Random rand = new Random();
        int randomGetal = rand.nextInt(lengte);
    
        return randomGetal;
    }
    
    // in pacman zelf zetten
    public void geefPacmanSuperkracht(){
        pacman.setKracht(true);
        maakSuperbolletjeTimer();
    }

    private void maakSuperbolletjeTimer() {
        int delay = 1000;
        TimerTask task = new TimerTask() {
            
            public void run() {
                System.out.println("yeahh");
                seconden++;
                System.out.println(seconden);

                if (seconden == 10) {
                    timerIsBezig = false;
                    zetSuperKrachtUit();
                    seconden = 0;
                    tijd.cancel();
                    tijd.purge();
                    System.out.println("bezig");
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
            tijd = new Timer();
            tijd.scheduleAtFixedRate(task, 0, delay);
        }
    }

    
    private void zetSuperKrachtUit(){
        pacman.setKracht(false);
    }
    
    // Alle bewegende entiteiten moeten op hun eigen plek verschijnen.
    public void resetPoppetje() {
        for (int i = 0; i < arrayHoogte; i++) {
            for (int j = 0; j < arrayBreedte; j++) {
                if (vakje[i][j].getElement().equals("pacman")) {
                    pacman = (Pacman) vakje[i][j].getSpelElement();
                    vakje[i][j].setElement("pad", null);
                    System.out.println("gevonden");
                } else if (vakje[i][j].getElement().equals("spookje")){
                    // Methode voor spookje, Blinky, Pinky, Inky, Clyde.
                    // blinky = vakje[i][j].spookje;
                }
            }
        }
        pacman.setVakje(vakje[yPos][xPos]);
        vakje[yPos][xPos].setElement("pacman", pacman);
    }
  
    private void getPacmanHuidigePositie() {
        for (int i = 0; i < arrayHoogte; i++) {
            for (int j = 0; j < arrayBreedte; j++) {
                if(vakje[i][j].getElement().equals("pacman")){
                    pacman = (Pacman) vakje[i][j].getSpelElement();
                }
            }
        }
    }
    public void tekenOpnieuw(int xPositie, int yPositie){
        repaint();
    }

    // Override paintComponent om spelElementen te laten zien in de map.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
            for (Vakje[] row : vakje) {
                for (Vakje element : row) {
                    //vakje[i][j].draw(g);
                    element.draw(g);
                }
            }
    }

    // Makkelijk info halen uit een rij en kolom van vakjesInhoud.
    private char charAt(int row, int column) {
        return vakjesInhoud.get(row).charAt(column);
    }

    class KeyboardListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) { }

        @Override
        public void keyReleased(KeyEvent ke) {
            getPacmanHuidigePositie();
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_R:
                    break;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
