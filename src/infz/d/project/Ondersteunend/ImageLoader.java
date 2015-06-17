/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Ondersteunend;

import infz.d.project.Enums.Afbeelding;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Method
 */
public class ImageLoader {
    //          Key,    Value
    private final Map<Afbeelding.Statisch, Image> mapStatischAfbeelding = new HashMap<>();
    private final Map<Afbeelding.Pacman, Image> mapPacmanAfbeelding = new HashMap<>();
    private final Map<Afbeelding.Spookje, Image> mapSpookjeAfbeelding = new HashMap<>();
    private File afbeelding;

    public ImageLoader() {
        laadStatischeSpelElementAfbeelding();
        laadBewegendeSpelElementAfbeelding();
    }

    private void laadStatischeSpelElementAfbeelding() {
        // Alle statische spelElementen
        mapStatischAfbeelding.put(Afbeelding.Statisch.MUUR, converteerFileNaarImage(afbeelding = new File("src/afbeelding/muur.png")));
        mapStatischAfbeelding.put(Afbeelding.Statisch.BOLLETJE, converteerFileNaarImage(afbeelding = new File("src/afbeelding/bolletje.png")));
        mapStatischAfbeelding.put(Afbeelding.Statisch.SUPERBOLLETJE, converteerFileNaarImage(afbeelding = new File("src/afbeelding/superbolletje.png")));
        mapStatischAfbeelding.put(Afbeelding.Statisch.KERS, converteerFileNaarImage(afbeelding = new File("src/afbeelding/kers.png")));
    }

    private void laadBewegendeSpelElementAfbeelding() {
        //Pacman
        mapPacmanAfbeelding.put(Afbeelding.Pacman.PACMANNOORD, converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanNoord.png")));
        mapPacmanAfbeelding.put(Afbeelding.Pacman.PACMANOOST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanOost.png")));
        mapPacmanAfbeelding.put(Afbeelding.Pacman.PACMANZUID, converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanZuid.png")));
        mapPacmanAfbeelding.put(Afbeelding.Pacman.PACMANWEST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanWest.png")));

        //Spookjes - Alle
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.BANG, converteerFileNaarImage(afbeelding = new File("src/afbeelding/SpookjeBang.png")));
        
        //Spookjes - Willekeurig
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.INKYNOORD, converteerFileNaarImage(afbeelding = new File("src/afbeelding/inkyNOORD.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.INKYOOST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/inkyOOST.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.INKYZUID, converteerFileNaarImage(afbeelding = new File("src/afbeelding/inkyZUID.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.INKYWEST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/inkyWEST.png")));

        mapSpookjeAfbeelding.put(Afbeelding.Spookje.BLINKYNOORD, converteerFileNaarImage(afbeelding = new File("src/afbeelding/blinkyNOORD.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.BLINKYOOST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/blinkyOOST.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.BLINKYZUID, converteerFileNaarImage(afbeelding = new File("src/afbeelding/blinkyZUID.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.BLINKYWEST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/blinkyWEST.png")));

        // Spookjes - Achtervolgend
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.PINKYNOORD, converteerFileNaarImage(afbeelding = new File("src/afbeelding/pinkyNOORD.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.PINKYOOST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/pinkyOOST.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.PINKYZUID, converteerFileNaarImage(afbeelding = new File("src/afbeelding/pinkyZUID.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.PINKYWEST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/pinkyWEST.png")));

        mapSpookjeAfbeelding.put(Afbeelding.Spookje.CLYDENOORD, converteerFileNaarImage(afbeelding = new File("src/afbeelding/clydeNOORD.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.CLYDEOOST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/clydeOOST.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.CLYDEZUID, converteerFileNaarImage(afbeelding = new File("src/afbeelding/clydeZUID.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.CLYDEWEST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/clydeWEST.png")));
    }

    private Image converteerFileNaarImage(File afbeelding) {
        try {
            BufferedImage bit = ImageIO.read(afbeelding);
            afbeelding = null;
            return bit;
        } catch (IOException e) {
            System.out.println("ImageLoader - converteerFileNaarImage: afbeelding pad niet gevonden, return null");
            return null;
        }
    }

    public Image selectStatischeSpelElementAfbeelding(Afbeelding.Statisch afbeeldingNaam) {
        switch (afbeeldingNaam) {
            case MUUR:
                return mapStatischAfbeelding.get(Afbeelding.Statisch.MUUR); // Kan null zijn
            case BOLLETJE:
                return mapStatischAfbeelding.get(Afbeelding.Statisch.BOLLETJE); // Kan null zijn
            case SUPERBOLLETJE:
                return mapStatischAfbeelding.get(Afbeelding.Statisch.SUPERBOLLETJE); // Kan null zijn
            case KERS:
                return mapStatischAfbeelding.get(Afbeelding.Statisch.KERS); // Kan null zijn
            default:
                System.out.println("ImageLoader - selectStatischeSpelElementAfbeelding: Geen afbeelding voor: " + afbeeldingNaam);
                return null;
        }
    }

    public Image selectPacmanAfbeelding(Afbeelding.Pacman afbeeldingNaam) {
        switch (afbeeldingNaam) {
            case PACMANNOORD:
                return mapPacmanAfbeelding.get(Afbeelding.Pacman.PACMANNOORD); // Kan null zijn
            case PACMANOOST:
                return mapPacmanAfbeelding.get(Afbeelding.Pacman.PACMANOOST); // Kan null zijn
            case PACMANZUID:
                return mapPacmanAfbeelding.get(Afbeelding.Pacman.PACMANZUID); // Kan null zijn
            case PACMANWEST:
                return mapPacmanAfbeelding.get(Afbeelding.Pacman.PACMANWEST); // Kan null zijn
            default:
                System.out.println("ImageLoader - selectPacmanAfbeelding: Geen afbeelding voor: " + afbeeldingNaam);
                return null;
        }
    }

    public Image selectSpookjeAfbeelding(Afbeelding.Spookje afbeeldingNaam) {
        switch (afbeeldingNaam) {
            case BANG:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.BANG); // Kan null zijn
            case INKYNOORD:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.INKYNOORD); // Kan null zijn
            case INKYOOST:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.INKYOOST); // Kan null zijn
            case INKYZUID:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.INKYZUID); // Kan null zijn
            case INKYWEST:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.INKYWEST); // Kan null zijn
            case BLINKYNOORD:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.BLINKYNOORD); // Kan null zijn
            case BLINKYOOST:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.BLINKYOOST); // Kan null zijn
            case BLINKYZUID:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.BLINKYZUID); // Kan null zijn
            case BLINKYWEST:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.BLINKYWEST); // Kan null zijn    
            case PINKYNOORD:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.PINKYNOORD); // Kan null zijn
            case PINKYOOST:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.PINKYOOST); // Kan null zijn
            case PINKYZUID:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.PINKYZUID); // Kan null zijn
            case PINKYWEST:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.PINKYWEST); // Kan null zijn     
            case CLYDENOORD:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.CLYDENOORD); // Kan null zijn
            case CLYDEOOST:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.CLYDEOOST); // Kan null zijn
            case CLYDEZUID:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.CLYDEZUID); // Kan null zijn
            case CLYDEWEST:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.CLYDEWEST); // Kan null zijn    
            default:
                System.out.println("ImageLoader - selectSpookjeAfbeelding: Geen afbeelding voor: " + afbeeldingNaam);
                return null;
        }
    }
}
