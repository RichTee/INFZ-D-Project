/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Method
 */
public class Spelbord extends javax.swing.JPanel {
    private LevelLoader             levelLoader;
    private SpelInformatie          spelInformatie;
    private Pacman                  pacman;
    private Kers                    kers;
    private Spel                    spel;
    private WillekeurigSpookje      inky;
    private WillekeurigSpookje      blinky;
    private AchtervolgendSpookje    pinky;
    private AchtervolgendSpookje    clyde;
    private StopWatch               stopwatch;
    private int                     xPos, yPos; // Positie
    private int                     arrayBreedte, arrayHoogte; // Cell dimensies
    private final static int        CELL = 50;
    private Vakje[][]               vakje;
    private ArrayList<String>       vakjesInhoud = new ArrayList<String>();
    private Border                  lineBorder = BorderFactory.createLineBorder(Color.black);
    public int                      level = 0;
    private KeyListener listener;
    
    /**
     * Creates new form Spelbord
     */
    public Spelbord() {
        initComponents();
        levelIncrement(1);
        this.levelLoader = new LevelLoader();
        genereerSpelbordPanelGegevens();
        this.stopwatch = new StopWatch(this);
        this.setBackground(Color.BLACK);
    }
    
    public void levelIncrement(int level){
        this.level = this.level + level;
    }
    
    public void setLevel(int level){
        this.level = level;
    }

    // Panel gegevens
    private void genereerSpelbordPanelGegevens() {
        getSpelbordTxtFile();
        genereerGuiMap();
        isBuur();
        
        this.setPreferredSize(new Dimension(CELL * arrayBreedte, CELL * arrayHoogte));
        this.setBorder(lineBorder);
    }

    // Snelle start voor Spel om de veld te activeren met het spel.
    public void start() {
        reset();
        
        genereerSpelbordPanelGegevens();
        panelListener();
        
        stopwatch.lopenSpookjes(inky);
        //stopwatch.lopenSpookjes(blinky);
        //stopwatch.lopenSpookjes(pinky);
        //stopwatch.lopenSpookjes(clyde);
    }
    
    public void herstart() {
        reset();
        genereerSpelbordPanelGegevens();
        panelListener();
    }
 
    public void nextLevel() {
        stopwatch.stopTimer();
  
        inky = null;
        blinky = null;
        pinky = null;
        clyde = null;
        
        spelInformatie.setTotaalAantalBolletjes(0);
        spelInformatie.nextLevelReset();

        this.vakjesInhoud.clear();
        
        for( int i = 0; i < vakje.length; i++ )
            vakje[i] = null;
        
        vakje = null;
        
        repaint();
        
        genereerSpelbordPanelGegevens();
        panelListener();
    }
    
    public void pauzeer() {
        // Moet nog timer stoppen.
        if (!spelInformatie.getPauze()) {
            this.setRequestFocusEnabled(false);
            spelInformatie.setPauze(true);
        } else {
            spel.setTekstPauze();
            this.requestFocusInWindow();
            spelInformatie.setPauze(false);
        }
    }
    
    private void panelListener() {
        this.removeKeyListener(listener);
        this.listener = new KeyboardListener(pacman);
        this.addKeyListener(listener);
        this.requestFocusInWindow();
    }
    
    public void reset() {
        stopwatch.stopTimer();
  
        inky = null;
        blinky = null;
        pinky = null;
        clyde = null;
        
        spelInformatie.setTotaalAantalBolletjes(0);
        spelInformatie.reset();

        this.vakjesInhoud.clear();
        
        for( int i = 0; i < vakje.length; i++ )
            vakje[i] = null;
        
        vakje = null;
        
        repaint();
    }

    // Moet anders kunnen...
    public void setSpel(Spel spel) {
        this.spel = spel;
        spelInformatie = new SpelInformatie(spel);
    }

    // Get posities van entiteiten die een spel vormen uit de txt file.
    private void getSpelbordTxtFile() {
            vakjesInhoud = levelLoader.laadLevel(level);
            arrayHoogte = vakjesInhoud.size();
            arrayBreedte = vakjesInhoud.get(0).length();
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
                        if(inky == null){
                            System.out.println("Inky spelbord");
                            vakje[row][column] = new Vakje(row, column, "inky", this);
                            inky = (WillekeurigSpookje) vakje[row][column].getSpookje("inky");
                            System.out.println("inky: " + vakje[row][column].getSpookje("inky").toString());
                        } else if (blinky == null ) {
                            System.out.println("blinky");
                            vakje[row][column] = new Vakje(row, column, "blinky", this);
                            blinky = (WillekeurigSpookje) vakje[row][column].getSpookje("blinky");
                        } else if (pinky == null ) {
                            System.out.println("pinky");
                            vakje[row][column] = new Vakje(row, column, "pinky", this);
                            pinky = (AchtervolgendSpookje) vakje[row][column].getSpookje("pinky");
                        } else if (clyde == null ) {
                            System.out.println("clyde");
                            vakje[row][column] = new Vakje(row, column, "clyde", this);
                            clyde = (AchtervolgendSpookje) vakje[row][column].getSpookje("clyde");
                        }
                        break;
                    case "5":
                        // Pacman
                        vakje[row][column] = new Vakje(row, column, "pacman", this);
                        pacman = (Pacman) vakje[row][column].getPacman();
                        xPos = column;
                        yPos = row;
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
            //System.out.println(xPos + "  " + yPos);
            vakje[xPos-1][yPos].getElement();
            vakje[xPos][yPos].voegtBuurToe(vakje[xPos-1][yPos]);
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
            spelInformatie.setScore(score, bolletje);
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
            
            vakje[XPositie][YPositie].setElement("kers", kers = new Kers(vakje[XPositie][YPositie]));
            kers = vakje[XPositie][YPositie].getKers();
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
    public void geefPacmanSuperkracht(Pacman pacman){
        stopwatch.pacmanOnverslaanbaarTimer(pacman);
    }
    
    // Alle bewegende entiteiten moeten op hun eigen plek verschijnen.
    public void resetPoppetje() {
        for (int i = 0; i < arrayHoogte; i++) {
            for (int j = 0; j < arrayBreedte; j++) {
                if (vakje[i][j].getElement().equals("pacman")) {
                    pacman = (Pacman) vakje[i][j].getPacman();
                    vakje[i][j].setElement("pad", null);
                } else if (vakje[i][j].getElement().equals("spookje")){
                    // Methode voor spookje, Blinky, Pinky, Inky, Clyde.
                    // blinky = vakje[i][j].spookje;
                }
            }
        }
        pacman.setVakje(vakje[yPos][xPos]);
        vakje[yPos][xPos].setElement("pacman", pacman);
    }

    public void tekenOpnieuw(){
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
