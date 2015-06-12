/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.GUI;

import infz.d.project.SpelElementen.Kers;
import infz.d.project.SpelElementen.Spookje;
import infz.d.project.SpelElementen.Pacman;
import infz.d.project.SpelElementen.SpelElement;
import infz.d.project.SpelElementen.Superbolletje;
import infz.d.project.SpelElementen.Muur;
import infz.d.project.SpelElementen.AchtervolgendSpookje;
import infz.d.project.SpelElementen.WillekeurigSpookje;
import infz.d.project.SpelElementen.Bolletje;

import java.awt.Graphics;

import java.util.ArrayList;

/**
 *
 * @author Method
 */
public class Vakje {

    /**
     * Creates new form Vakje
     */
    private int                     xPositie;
    private int                     yPositie;
    private String                  element   = "pad";
    private ArrayList<Vakje>        buurVakje = new ArrayList<>();
    private ArrayList<SpelElement>  spelElementList = new ArrayList<>();
    private final Spelbord          spelbord;
    private SpelElement             spelElement;
    private Muur                    muur;
    public WillekeurigSpookje       willekeurigSpookje;
    private AchtervolgendSpookje    achtervolgendSpookje;
    private WillekeurigSpookje      inky;
    private WillekeurigSpookje      blinky;
    private AchtervolgendSpookje    pinky;
    private AchtervolgendSpookje    clyde;
    private Pacman                  pacman;
    
    public Vakje(int xPositie, int yPositie, String element, Spelbord spelBord) {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.element = element;
        this.spelbord = spelBord;

        vulSpelElementList();
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
                    break;
                case "inky":
                    this.inky = (WillekeurigSpookje) spelElement;
                    break;
                case "blinky":
                    this.blinky = (WillekeurigSpookje) spelElement;
                    break;
                case "pinky":
                    this.pinky = (AchtervolgendSpookje) spelElement;
                    break;
                case "clyde":
                    this.clyde = (AchtervolgendSpookje) spelElement;
                    break;
                case "bolletje":
                case "superbolletje":
                    break;
                case "kers":
                    this.element = element;
                    this.spelElement = spelElement;
                    System.out.println("Element: " + element);
                    System.out.println("spelElement: " + spelElement);
                    break;
            }
            // Andere SpelElementen later.
        }
    }
    
    public Pacman getPacman() {
        return pacman;
    }

    public SpelElement getSpelElement() {
        return spelElement;
    }
    
    public Spookje getSpookje(String spookje) {
        switch(spookje){
            case "inky":
                return inky;
            case "blinky":
                return blinky;
            case "pinky":
                return pinky;
            case "clyde":
                return clyde;
            default:
                return null;
        }
    }
    
    public void setSpookje(Spookje spookje) {
        switch(spookje.naam /*getNaam*/) {
            case "inky":
                this.inky = (WillekeurigSpookje) spookje;
                break;
            case "blinky":
                this.blinky = (WillekeurigSpookje) spookje;
                break;
            case "pinky":
                this.pinky = (AchtervolgendSpookje) spookje;
                break;
            case "clyde":
                this.clyde = (AchtervolgendSpookje) spookje;
                break;
            default:
                break;
        }
    }
    
    public void voegtBuurToe(Vakje vakje) {
        buurVakje.add(vakje);
    }

    private int puntenOptellenVanBuurVakje(Vakje vakje) {
        int punten = 0;
        
        Spookje inky = vakje.getSpookje("inky");
        Spookje blinky = vakje.getSpookje("blinky");
        Spookje pinky = vakje.getSpookje("pinky");
        Spookje clyde = vakje.getSpookje("clyde");
        
        if(inky != null)
            punten = punten + inky.getPunten();
        if(blinky != null)
            punten = punten + blinky.getPunten();
        if(pinky != null)
            punten = punten + pinky.getPunten();
        if(clyde != null)
            punten = punten + clyde.getPunten();
        
        switch(vakje.getElement()){
            case "bolletje":
            case "superbolletje":
            case "kers":
                if(vakje.getSpelElement() != null) {
                    punten = punten + vakje.getSpelElement().getPunten();
                }
                break;
            default:
                break;
        }
        
        return punten;
    }
    
    private void teleporteerBewegend(){
        if(this.getXPositie() != 10)
            return;
        
        // Alleen pacman
        if(this.getYPositie() == 1){
            spelbord.getSpecifiekVakje(10, 17).setElement("pacman", pacman);
            this.element = "pad";
            pacman.setVakje(spelbord.getSpecifiekVakje(10, 17));
            spelbord.tekenOpnieuw();
        }
        
        if(this.getYPositie() == 18){
            spelbord.getSpecifiekVakje(10, 2).setElement("pacman", pacman);
            this.element = "pad";
            pacman.setVakje(spelbord.getSpecifiekVakje(10, 2));
            spelbord.tekenOpnieuw();
        }
    }
    
    public void pacmanRichting(String richting) {
        /*
        * NOORD = 0
        * OOST  = 1
        * ZUID  = 2
        * WEST  = 3
        */
        switch(richting) {
            case "NOORD":
                checkPacmanCollisie(buurVakje.get(0));
                break;
            case "OOST":
                checkPacmanCollisie(buurVakje.get(1));
                break;
            case "ZUID":
                checkPacmanCollisie(buurVakje.get(2));
                break;
            case "WEST":
                checkPacmanCollisie(buurVakje.get(3));
                break;
        }
    }
    
    public void spookjeRichting(String richting, Spookje spookje) {
        /*
        * NOORD = 0
        * OOST  = 1
        * ZUID  = 2
        * WEST  = 3
        */
        switch(richting) {
            case "NOORD":
                checkSpookjeCollisie(buurVakje.get(0), spookje);
                break;
            case "OOST":
                checkSpookjeCollisie(buurVakje.get(1), spookje);
                break;
            case "ZUID":
                checkSpookjeCollisie(buurVakje.get(2), spookje);
                break;
            case "WEST":
                checkSpookjeCollisie(buurVakje.get(3), spookje);
                break;
        }
    }

    private void checkPacmanCollisie(Vakje vakje){
        int punten = 0;
        boolean verlorenSwitchConditie = false;
        WillekeurigSpookje inky = (WillekeurigSpookje) vakje.getSpookje("inky");
        WillekeurigSpookje blinky = (WillekeurigSpookje) vakje.getSpookje("blinky");
        AchtervolgendSpookje pinky = (AchtervolgendSpookje) vakje.getSpookje("pinky");
        AchtervolgendSpookje clyde = (AchtervolgendSpookje) vakje.getSpookje("clyde");
        
        // ToDo: Prioriteit: Spookje > Bolletje > Superbolletje > Kers
        if (inky != null || blinky != null || pinky != null || clyde != null) {
            
            if (pacman.getKracht() && !vakje.getElement().equals("pad")) {
                punten = puntenOptellenVanBuurVakje(vakje);
                
                if (inky != null) {
                    vakje.inky = null;
                }
                if (blinky != null) {
                    vakje.blinky = null;
                }
                if (pinky != null) {
                    vakje.pinky = null;
                }
                if (clyde != null) {
                    vakje.clyde = null;
                }

                vakje.setElement(this.getElement(), pacman);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw();
                spelbord.setSpelInformatie(punten, 0, "");

            } else if (pacman.getKracht()){
                punten = puntenOptellenVanBuurVakje(vakje);
                if(inky != null)
                    vakje.inky = null;
                if(blinky != null)
                    vakje.blinky = null;
                if(pinky != null)
                    vakje.pinky = null;
                if(clyde != null)
                    vakje.clyde = null;
                
                vakje.setElement(this.getElement(), pacman);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw();
                spelbord.setSpelInformatie(punten, 0, "");
            } else if (!pacman.getKracht()) {
                System.out.println("VERLOREN");
                verlorenSwitchConditie = true;
                spelbord.setSpelInformatie(0, -1, "");
                spelbord.resetPoppetje();
                spelbord.tekenOpnieuw();
            }
        }
        if(!verlorenSwitchConditie){
            switch(vakje.getElement()){
                case "muur":
                    break;
                case "pad":
                    vakje.setElement(this.getElement(), null);
                    this.setElement("pad", null);
                    pacman.setVakje(vakje);
                    spelbord.tekenOpnieuw();
                    //tekenOpnieuw();
                    //vakje.tekenOpnieuw();
                    break;
                case "bolletje":
                    punten = puntenOptellenVanBuurVakje(vakje);
                    spelbord.maakKersInLegeVakje();
                    vakje.setElement(this.getElement(), pacman);
                    this.setElement("pad", null);
                    pacman.setVakje(vakje); // ToDo: Spookje error
                    spelbord.tekenOpnieuw();
                    spelbord.setSpelInformatie(punten, 0, "bolletje");
 
                    //tekenOpnieuw();
                    //vakje.tekenOpnieuw();
                    break;
                case "superbolletje":
                    punten = puntenOptellenVanBuurVakje(vakje);
                    vakje.setElement(this.getElement(), pacman);
                    this.setElement("pad", null);
                    pacman.setVakje(vakje);
                    spelbord.geefPacmanSuperkracht(pacman);
                    spelbord.tekenOpnieuw();
                    spelbord.setSpelInformatie(punten, 0, "superbolletje");
                    //tekenOpnieuw();
                    //vakje.tekenOpnieuw();
                    break;
                case "kers":
                    punten = puntenOptellenVanBuurVakje(vakje);
                    vakje.setElement(this.getElement(), null);
                    this.setElement("pad", null);
                    pacman.setVakje(vakje);
                    spelbord.tekenOpnieuw();
                    spelbord.setSpelInformatie(punten, 0, "kers");
                    //tekenOpnieuw();
                    //vakje.tekenOpnieuw();
                    break;
                default:
                    break;
            }
        }
        teleporteerBewegend();
    }

    private void checkSpookjeCollisie(Vakje vakje, Spookje spookje){ 
        switch(vakje.getElement()){
            case "muur":
                break;
            case "pad":
                spookje.setVakje(vakje);
                vakje.setSpookje(spookje);
                if(spookje.naam.equals("inky"))
                    this.inky = null;
                if(spookje.naam.equals("blinky"))
                    this.blinky = null;
                spelbord.tekenOpnieuw();
                break;
            case "bolletje":
                spookje.setVakje(vakje);
                vakje.setSpookje(spookje);
                if(spookje.naam.equals("inky"))
                    this.inky = null;
                if(spookje.naam.equals("blinky"))
                    this.blinky = null;
                spelbord.tekenOpnieuw();
                break;
            case "superbolletje":
                spookje.setVakje(vakje);
                vakje.setSpookje(spookje);
                if(spookje.naam.equals("inky"))
                    this.inky = null;
                if(spookje.naam.equals("blinky"))
                    this.blinky = null;
                spelbord.tekenOpnieuw();
                break;
            case "kers":
                spookje.setVakje(vakje);
                vakje.setSpookje(spookje);
                if(spookje.naam.equals("inky"))
                    this.inky = null;
                if(spookje.naam.equals("blinky"))
                    this.blinky = null;
                spelbord.tekenOpnieuw();
                break;
            case "pacman":
                if (pacman.getKracht()) {
                    spookje.setVakje(vakje);
                    vakje.setSpookje(spookje);
                    if(spookje.naam.equals("inky"))
                        this.inky = null;
                    if(spookje.naam.equals("blinky"))
                        this.blinky = null;
                    spelbord.tekenOpnieuw();
                    
                } else if (!pacman.getKracht()) {
                    System.out.println("VERLOREN");
                    spelbord.setSpelInformatie(0, -1, "");
                    spelbord.resetPoppetje();
                    spelbord.tekenOpnieuw();
                    
                }
                break;
            default:
                break;
        }
    }
    
    // Logischer in Spelbord voor minder Memory intake en makkelijkere toegang.
    private void vulSpelElementList() {
        switch(element){
            case "muur":
                muur = new Muur(this);
                spelElement = muur;
                break;
            case "bolletje":
                spelElement = new Bolletje(this);
                break;
            case "superbolletje":
                spelElement = new Superbolletje(this);
                break;
            case "inky":
                inky = new WillekeurigSpookje(this, "inky");
                break;
            case "blinky":
                blinky = new WillekeurigSpookje(this, "blinky");
                break;
            case "pinky":
                pinky = new AchtervolgendSpookje(this, "pinky");
                break;
            case "clyde":
                clyde = new AchtervolgendSpookje(this, "clyde");
                break;
            case "pacman":
                pacman = new Pacman(this);
                break;
            default:
                // Pad, niks toevoegen. Indien vakje leeg is, dan moet die niks bevatten
                break;
        }
       // sortSpelElementList();
    }

    // Draw Logic | Vakje moet zich kleuren op basis van inhoud.
    // Met als prioriteit muur > pacman > spookje > bolletje > superbolletje > kers
    public void draw(Graphics g) {
        switch(element){
            case "muur":
                muur.draw(g);
                break;
            case "bolletje":
            case "superbolletje":
            case "kers":
                spelElement.draw(g);
                break;
            default:
                break;
        }

        if (pacman != null) {
            pacman.draw(g);
        } else if (inky != null) {
            inky.draw(g);
        } else if (blinky != null) {
            blinky.draw(g);
        } else if (pinky != null) {
            pinky.draw(g);
        } else if (clyde != null) {
            clyde.draw(g);
        }
    }             
}
