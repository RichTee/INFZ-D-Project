/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Method
 */
public class Spelbord extends javax.swing.JPanel {
    private int xPos, yPos; // Positie
    private int cellBreedte, cellHoogte; // Cell dimensies
    private final static int CELL = 50;
    private Vakje[][] vakje;
    private ArrayList<String> vakjesInhoud = new ArrayList<String>();
    private Border lineBorder = BorderFactory.createLineBorder(Color.black);
    
    /**
     * Creates new form Spelbord
     */
    public Spelbord() {
        initComponents();
        genereerSpelbordPanelGegevens();
    }
    
    // Panel gegevens
    private void genereerSpelbordPanelGegevens() {
        getSpelbordTxtFile();
        genereerGuiMap();
        //genereerVakjes();
        setPreferredSize(new Dimension(CELL * cellBreedte, CELL * cellHoogte));
        // this.setBackground(Color.BLACK);
        this.setBorder(lineBorder);
    }
    
    // Snelle start voor Spel om de veld te activeren met het spel.
    public void start() {
        genereerSpelbordPanelGegevens();
        this.requestFocusInWindow();
    }
    
    // Bekijk of een Poppetje naar een cell mag gaan
    public boolean getVakjeEnNavigeerbaar(int richtingX, int richtingY, Vakje vakje) {
        Graphics g;
        // Out of bounds check
        if(vakje.xPositie + richtingX == -1 
            || vakje.yPositie + richtingY == -1
            || richtingX + vakje.xPositie == this.cellBreedte + 1
            || richtingY + vakje.yPositie == this.cellHoogte + 1)
            return false;
        
        // Muur check
        if(this.vakje[vakje.xPositie + richtingX][vakje.yPositie + richtingY].isMuur())
            return false;
                
        // Set nieuw spelElement voor Vakjes
        this.vakje[vakje.xPositie + richtingX][vakje.yPositie + richtingY].setElement(vakje.getElement());
        vakje.setElement("pad");
        
        repaint();
        
        return true;
    }
    
    // Get posities van entiteiten die een spel vormen uit de txt file.
    private void getSpelbordTxtFile() {
        try {
            Scanner read = new Scanner(new File("txt/maze.txt"));
            int lineCount = 0;
            while (read.hasNextLine()) {
                String line = read.nextLine();
                vakjesInhoud.add(line);
                System.out.println(line);
                if (line.contains("5")) {
                    yPos = lineCount;
                    xPos = line.indexOf('5');
                }
                lineCount++;
            }
            read.close();

            cellHoogte = vakjesInhoud.size();
            cellBreedte = vakjesInhoud.get(0).length();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Genereer de map voor de GUI nadat de TXT is gelezen
    private void genereerGuiMap() {
        vakje = new Vakje[cellHoogte][cellBreedte];

        for (int row = 0; row < cellHoogte; row++) {
            for (int column = 0; column < cellBreedte; column++) {
                switch (String.valueOf(charAt(row, column))) {
                    case "0":
                        vakje[row][column] = new Vakje(row, column, "pad");
                        break;
                    case "1":
                        // muur
                        vakje[row][column] = new Vakje(row, column, "muur");
                        break;
                    case "2":
                        // Bolletje
                        break;
                    case "3":
                        // Superbolletje
                        break;
                    case "4":
                        // Spookje
                        vakje[row][column] = new Vakje(row, column, "spookje");
                        break;
                    case "5":
                        // Pacman
                        vakje[row][column] = new Vakje(row, column, "pacman");
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    // Override paintComponent om spelElementen te laten zien in de map.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, cellBreedte * CELL, cellHoogte * CELL);
        for(int i = 0; i < cellHoogte; i++){
            for(int j = 0; j < cellBreedte; j++){
                vakje[i][j].draw(g);
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
