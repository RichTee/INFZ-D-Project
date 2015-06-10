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
    String path = "";
   
    
    public void playMusic(Geluid geluid, boolean loop) {
        AudioInputStream inputStream;
        
        try {
            
            switch(geluid)
            {
            
                case START_GELUID:
                    path = "src/Geluid/pacman_beginning.wav";
                    break;
                
                case BACKGROUND_GELUID:
                    path = "src/Geluid/siren_sound.wav";
                    break;
                
                case SUPERBOLLETJE_GELUID:
                    path = "geluid/pacman_intermission.wav";
                    break;
   
            }
                   
                   
            
            clip = null;
            File file = new File(path);
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

    public void stopMusic() {
        clip.stop();      
    }
}
