/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Geluiden;
import infz.d.project.Enums.Geluid;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

 /*
 * @author Sebastiaan
 */
public class AudioPlayer {

    Clip clip;
    String muziekPad = "";
    
    public void speelGeluid(Geluid geluid, boolean loop) {
        AudioInputStream inputStream;
        
        try {
            switch(geluid)
            {
                case START_GELUID:
                    muziekPad = "src/Geluid/pacman_beginning.wav";
                    break;   
                case BACKGROUND_GELUID:
                    muziekPad = "src/Geluid/siren-sound.wav";
                    break;
                case SUPERBOLLETJE_GELUID:
                    muziekPad = "geluid/invinciblejb.wav";
                    break;
            }
                   
            clip = null;
            File file = new File(muziekPad);
            clip = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream(file);

            clip.open(inputStream);
            if (loop) {
                clip.loop(-1);
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void stopGeluid() {
        clip.stop();      
    }
}
