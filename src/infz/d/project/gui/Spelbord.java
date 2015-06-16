/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import infz.d.project.Geluiden.AudioPlayer;
import infz.d.project.Enums.Geluid;
import static infz.d.project.Enums.Geluid.*; // ?
import infz.d.project.Enums.Richting;
import infz.d.project.Ondersteunend.*;
import infz.d.project.SpelElementen.Kers;
import infz.d.project.SpelElementen.Pacman;
import infz.d.project.SpelElementen.AchtervolgendSpookje;
import infz.d.project.SpelElementen.Spookje;
import infz.d.project.SpelElementen.WillekeurigSpookje;

/**
 *
 * @author Method
 */
public class Spelbord extends javax.swing.JPanel {
    private final LevelLoader       levelLoader;
    private SpelInformatie          spelInformatie;
    private final AudioPlayer       player; 
    private Pacman                  pacman;
    private Kers                    kers;
    private Spel                    spel;
    private WillekeurigSpookje      inky;
    private WillekeurigSpookje      blinky;
    private AchtervolgendSpookje    pinky;
    private AchtervolgendSpookje    clyde;
    private final StopWatch         stopwatch;
    private final ImageLoader       imageLoader = new ImageLoader();
    private int                     xPos, yPos; // Positie
    private int                     arrayBreedte, arrayHoogte; // Cell dimensies
    private final static int        CELL = 35;
    private Vakje[][]               vakje;
    private ArrayList<String>       vakjesInhoud = new ArrayList<>();
    private final Border            lineBorder = BorderFactory.createLineBorder(Color.black);
    public int                      level = 0;
    private int                     snelheid = 500;
    private KeyListener listener;
    
    /**
     * Creates new form Spelbord
     */
    public Spelbord() {
        initComponents();
        levelIncrement(1);
        this.levelLoader = new LevelLoader();
        genereerSpelbordPanelGegevens();
        this.setBackground(Color.BLACK);
        player = new AudioPlayer();
        stopwatch = new StopWatch(this);
    }
    
    public void startMuziek(Geluid geluid, boolean loop)
    {
        player.speelGeluid(geluid, loop);
    }
    
    public void stopMuziek(){
        player.stopGeluid();
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
        this.startMuziek(BACKGROUND_GELUID, true);
        genereerSpelbordPanelGegevens();
        panelListener();
    }
    
    public void herstart() {
        spel.setTekstPauze();
        stopwatch.stopLopenSpookjes();
        reset();
        this.startMuziek(BACKGROUND_GELUID, true);
        genereerSpelbordPanelGegevens();
        panelListener();
    }
 
    public void nextLevel() {
        // Set moeilijkheid
        if(level <= 3){
            snelheid = snelheid * (level / 10);
        }
        
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
        
        repaint();
        
        genereerSpelbordPanelGegevens();
        panelListener();
    }
    
    public void pauzeer() {
        // Moet nog timer stoppen.
        if (!spelInformatie.getPauze()) {
            stopMuziek();
            stopwatch.pauzeerTimer();
            pacman.magLopen = false;
            stopwatch.stopLopenSpookjes();
            
            this.setRequestFocusEnabled(false);
            spelInformatie.setPauze(true);
        } else {
            this.startMuziek(BACKGROUND_GELUID, true);
            
            if(pacman.getKracht()){
                stopwatch.pacmanOnverslaanbaarTimer(pacman);
            }
            
            spel.setTekstPauze();
            this.requestFocusInWindow();
            spelInformatie.setPauze(false);
            pacman.magLopen = true;
            stopwatch.lopenInky(inky, snelheid);
            stopwatch.lopenBlinky(blinky, snelheid);
        }
    }
    
    private void panelListener() {
        this.removeKeyListener(listener);
        this.listener = new KeyboardListener(pacman);
        this.addKeyListener(listener);
        this.requestFocusInWindow();
        
        stopwatch.lopenInky(inky, snelheid);
        stopwatch.lopenBlinky(blinky, snelheid);
        //stopwatch.lopenSpookjes(pinky);
        //stopwatch.lopenSpookjes(clyde);
    }
    
    public void reset() {
        stopwatch.stopTimer();
        
        this.stopMuziek();
        
        inky = null;
        blinky = null;
        pinky = null;
        clyde = null;
        
        spelInformatie.setTotaalAantalBolletjes(0);
        spelInformatie.reset();

        this.vakjesInhoud.clear();
        
        for( int i = 0; i < vakje.length; i++ )
            vakje[i] = null;
        
        //repaint();
    }

    // Moet anders kunnen...
    public void setSpel(Spel spel) {
        this.spel = spel;
        spelInformatie = new SpelInformatie(spel);
    }

    public Vakje getSpecifiekVakje(int row, int column) {
       return vakje[row][column]; 
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
                        vakje[row][column] = new Vakje(row, column, "pad", this, imageLoader);
                        break;
                    case "1":
                        // muur
                        vakje[row][column] = new Vakje(row, column, "muur", this, imageLoader);
                        break;
                    case "2":
                        // Bolletje
                        vakje[row][column] = new Vakje(row, column, "bolletje", this, imageLoader);
                        spelInformatie.setTotaalAantalBolletjesIncrement(1);
                        break;
                    case "3":
                        // Superbolletje
                        vakje[row][column] = new Vakje(row, column, "superbolletje", this, imageLoader);
                        break;
                    case "4":
                        // Spookje
                        if(inky == null){
                            vakje[row][column] = new Vakje(row, column, "pad", this, imageLoader);
                            vakje[row][column].setNieuwSpookje("inky");
                            inky = (WillekeurigSpookje) vakje[row][column].getSpookje("inky");
                        } else if (blinky == null ) {
                            vakje[row][column] = new Vakje(row, column, "pad", this, imageLoader);
                            vakje[row][column].setNieuwSpookje("blinky");
                            blinky = (WillekeurigSpookje) vakje[row][column].getSpookje("blinky");
                        } else if (pinky == null ) {
                            vakje[row][column] = new Vakje(row, column, "pad", this, imageLoader);
                            vakje[row][column].setNieuwSpookje("pinky");
                            pinky = (AchtervolgendSpookje) vakje[row][column].getSpookje("pinky");
                        } else if (clyde == null ) {
                            vakje[row][column] = new Vakje(row, column, "pad", this, imageLoader);
                            vakje[row][column].setNieuwSpookje("clyde");
                            clyde = (AchtervolgendSpookje) vakje[row][column].getSpookje("clyde");
                        }
                        break;
                    case "5":
                        // Pacman
                        vakje[row][column] = new Vakje(row, column, "pacman", this, imageLoader);
                        pacman = (Pacman) vakje[row][column].getPacman();
                        xPos = column;
                        yPos = row;
                        //pacman = new Pacman(vakje[row][column]);
                        break;
                    case "9":
                        vakje[row][column] = new Vakje(row, column, "bolletje", this, imageLoader);
                        vakje[row][column].setKanTeleporteren(true);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    // Elk vakje moet zijn buren weten
    public void isBuur() {
        for (int row = 0; row < arrayHoogte; row++) {
            for (int column = 0; column < arrayBreedte; column++) {
                if(!vakje[row][column].getElement().equals("muur")) // We checken nooit of een muur een buur heeft!
                    isBuurOutOfBounds(row, column);
            }
        }
    }

    // Niet elk vakje heeft een even aantal buren die in een array zit, met try catch vangen we excepties.
    private void isBuurOutOfBounds(int xPos, int yPos) {
        try {
            // Noord
            //System.out.println(xPos + "  " + yPos);
            if(!vakje[xPos-1][yPos].getElement().equals("muur")) {
                vakje[xPos-1][yPos].getElement();
                vakje[xPos][yPos].voegtBuurToe(Richting.NOORD, vakje[xPos-1][yPos]);
            }
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("xPos-1 : " + e );
        }
        try {
            // Oost
            if(!vakje[xPos][yPos+1].getElement().equals("muur")){
                vakje[xPos][yPos+1].getElement();
                vakje[xPos][yPos].voegtBuurToe(Richting.OOST, vakje[xPos][yPos+1]);
            }
            //System.out.println(xPos + "  " + yPos);
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("xPos+1 : " + e );
        }
        try {
            // Zuid
            if(!vakje[xPos+1][yPos].getElement().equals("muur")){
                vakje[xPos+1][yPos].getElement();
                vakje[xPos][yPos].voegtBuurToe(Richting.ZUID, vakje[xPos+1][yPos]);
            }
            //System.out.println(xPos + "  " + yPos);
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("yPos-1 : " + e );
        }
        try {
            // West
            if(!vakje[xPos][yPos-1].getElement().equals("muur")){
                vakje[xPos][yPos-1].getElement();
                vakje[xPos][yPos].voegtBuurToe(Richting.WEST, vakje[xPos][yPos-1]);
            }
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
    
    public void maakKersInLegeVakje() {
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
    public void geefPacmanSuperkracht(Pacman pacman){
        stopMuziek();
        stopwatch.pacmanOnverslaanbaarTimer(pacman);
    }
    
    // Alle bewegende entiteiten moeten op hun eigen plek verschijnen.
    public void resetPacman() {
        for (int i = 0; i < arrayHoogte; i++) {
            for (int j = 0; j < arrayBreedte; j++) {
                if (vakje[i][j].getPacman() != null) {
                    pacman = (Pacman) vakje[i][j].getPacman();
                    vakje[i][j].setElement("pad", null);
                } 
            }
        }
        pacman.setVakje(vakje[yPos][xPos]);
        vakje[yPos][xPos].setElement("pacman", pacman);
    }

    public void resetSpookje(Spookje spookje) {
        spookje.setVakje(spookje.startPositie);
    }
    
    // Repaint 
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
