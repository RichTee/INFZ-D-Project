/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Method
 */
public class Vakje extends javax.swing.JPanel {

    /**
     * Creates new form Vakje
     */
    private int                     xPositie;
    private int                     yPositie;
    private String                  element;
    private ArrayList<Vakje>        buurVakje = new ArrayList<>();
    private ArrayList<SpelElement>  spelElementList = new ArrayList<>();
    private final Spelbord          spelbord;
    private Muur                    muur;
    private Spookje                 spookje;
    private Pacman                  pacman;
    private Bolletje                bolletje;
    private Superbolletje           superbolletje;
    public  Kers                    kers;
    
    public Vakje(int xPositie, int yPositie, String element, Spelbord spelBord) {
        initComponents();
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.element = element;
        this.spelbord = spelBord;
        setPreferredSize(new Dimension(50, 50));

        vulSpelElementList();
    }

    public void voegtBuurToe(Vakje vakje) {
        buurVakje.add(vakje);
    }

    public void pacmanRichting(int xpos, int ypos) {
        xpos = xpos + xPositie;
        ypos = ypos + yPositie;
        
        for (Vakje vakje : buurVakje) {
            if (vakje.getXPositie() == xpos) {
                if (vakje.getYPositie() == ypos) {
                    if (!vakje.isMuur()) {
                        checkElement(vakje);
                    }
                }
            }
        }
    }

    private void checkElement(Vakje vakje){
        // We moeten onderscheid maken tussen spookje en andere spelElementen.
        // Omdat spookje geen pad mag neer zetten, en 2 elementen in een vak kunnen zitten.
        // ArrayList van max 5(4 spoken en 1 ander spelElement)
        switch(vakje.getElement()){
            case "pad":
                vakje.setElement(this.getElement(), null);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw(this.getXPositie(), this.getYPositie());
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            case "bolletje":
                spelbord.setSpelInformatie(vakje.getSpelElement().getPunten(), 0, "bolletje");
                spelbord.maakKers();
                vakje.setElement(this.getElement(), pacman);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw(this.getXPositie(), this.getYPositie());
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            case "superbolletje":
                spelbord.geefPacmanSuperkracht();
                vakje.setElement(this.getElement(), pacman);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw(this.getXPositie(), this.getYPositie());
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            case "kers":
                spelbord.setSpelInformatie(vakje.getSpelElement().getPunten(), 0, "kers");
                vakje.setElement(this.getElement(), null);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw(this.getXPositie(), this.getYPositie());
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            case "spookje":
                if (pacman.getKracht()) {
                    spelbord.setSpelInformatie(vakje.getSpelElement().getPunten(), 0, "");
                    vakje.setElement(this.getElement(), pacman);
                    this.setElement("pad", null);
                    pacman.setVakje(vakje);
                    spelbord.tekenOpnieuw(this.getXPositie(), this.getYPositie());

                } else if (!pacman.getKracht()) {
                    System.out.println("VERLOREN");
                    spelbord.setSpelInformatie(0, -1, "");
                    spelbord.resetPoppetje();
                    spelbord.tekenOpnieuw(this.getXPositie(), this.getYPositie());

                }
                //spelbord.tekenOpnieuw(this.getXPositie(), this.getYPositie());
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            default:
                System.out.println("Default switch");
                break;
        }
    }
    
    // return of vakje een muur
    public boolean isMuur() {
        if (element.equals("muur")) {
            return true;
        } else {
            return false;
        }
    }

    // Positie van vakje(X)
    public int getXPositie() {
        return xPositie;
    }

    // Positie van vakje(Y)
    public int getYPositie() {
        return yPositie;
    }

    // Return SpelElement (muur of poppetje..)
    public String getElement() {
        return element;
    }

    public void setElement(String element, SpelElement spelElement) {
        if (spelElement == null) {
            this.element = element;
        } else {
            switch(element){
                case "pacman":
                    this.element = element;
                    this.pacman = (Pacman) spelElement;
                    spelElementList.add(spelElement);
                    break;
                case "spookje":
                case "bolletje":
                case "superbolletje":
                case "kers":
                    this.element = element;
                    spelElementList.add(spelElement);
                    break;
            }
            // Andere SpelElementen later.
        }
    }
    
    
    public SpelElement getSpelElement(){
        switch(element){
            case "pacman":
                return pacman;
            case "spookje":
                return spookje;
            case "muur":
                return muur;
            case "bolletje":
                return bolletje;
            case "superbolletje":
                return superbolletje;
            case "kers":
                return kers;
            default:
                return null;
        }
    }
    
    // Logischer in Spelbord voor minder Memory intake en makkelijkere toegang.
    private void vulSpelElementList() {
        muur = new Muur(this);
        spookje = new Spookje(this);
        pacman = new Pacman(this);
        bolletje = new Bolletje(this);
        superbolletje = new Superbolletje(this);
        kers = new Kers(this);
        
        spelElementList.add(muur);          // 1
        spelElementList.add(bolletje);      // 2
        spelElementList.add(superbolletje); // 3
        spelElementList.add(spookje);       // 4
        spelElementList.add(pacman);        // 5
        spelElementList.add(kers);          // 6
    }

    // Draw Logic | Vakje moet zich kleuren op basis van inhoud.
    public void draw(Graphics g) {
        switch (element) {
            case "pad":
                g.setColor(Color.decode("#000000"));
                g.fillRect(yPositie * 50/*yPositie*/, xPositie * 50/*xPositie*/, 50, 50);
                break;
            case "muur":
                if (spelElementList.contains(muur)) {
                    muur.draw(g);
                }
                break;
            case "bolletje":
                if (spelElementList.contains(bolletje)){
                    bolletje.draw(g);
                }
                break;
            case "superbolletje":
                if (spelElementList.contains(superbolletje)){
                    superbolletje.draw(g);
                }
                break;
            case "spookje":
                if (spelElementList.contains(spookje)) {
                    spookje.draw(g);
                }
                break;
            case "pacman":
                if (spelElementList.contains(pacman)) {
                    pacman.draw(g);
                }
                break;
            case "kers":
                if (spelElementList.contains(kers)){
                    kers.draw(g);
                }
            default:
                break;
        }
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
