package GameComponents;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/Assets/Sounds/an_vat_pham.wav");// nhạc nền menu
        soundURL[1] = getClass().getResource("/Assets/Sounds/chumchet.wav");// nhạc tank bị bắn chết
        soundURL[2] = getClass().getResource("/Assets/Sounds/kick.wav"); // nhạc kick
        soundURL[3] = getClass().getResource("/Assets/Sounds/nhacNenMenu.wav"); // nhan an items
        soundURL[4] = getClass().getResource("/Assets/Sounds/tieng tank ban.wav");
        soundURL[5] = getClass().getResource("/Assets/Sounds/tiengsung.wav");
    }

    public void setFile(int i){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            System.out.println("Loi");
        }

    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
