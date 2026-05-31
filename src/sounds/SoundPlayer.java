package sounds;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.Random;

/**
 * Purpose: The reponsibility of SoundPlayer is ...
 *
 * SoundPlayer is-a ...
 * SoundPlayer is ...
 */

public class SoundPlayer
{
	private Random random = new Random();
    private Clip clip;

    private String[] soundFiles = {
            "/sounds/Bad.wav",
            "/sounds/Beat It.wav",
            "/sounds/Billie Jean.wav",
            "/sounds/Black or White.wav",
            "/sounds/Butterflies.wav",
            "/sounds/Chicago.wav",
            "/sounds/Dirty Diana.wav",
            "/sounds/Don't Stop 'Til You Get Enough.wav",
            "/sounds/Give in to Me.wav",
            "/sounds/Human Nature.wav",
            "/sounds/Liberian Girl.wav",
            "/sounds/Off the Wall.wav",
            "/sounds/PYT.wav",
            "/sounds/Rock with You.wav",
            "/sounds/Smooth Criminal.wav",
            "/sounds/Thriller.wav",
            "/sounds/You Rock My World.wav",
            "/sounds/Heaven Can Wait.wav"
    };
    
    private boolean isStopped = false;
    private int currentIndex = random.nextInt(soundFiles.length);

    
    
    
    /** 
     * Purpose: To play a random sound from the playlist and then continue to play
     * 
     */
    public void playLoopingPlaylist()
    {
        playNext();
    }

    private void playNext()
    {
        try
        {
            if (clip != null)
            {
                clip.close();
            }

            URL url = getClass().getResource(soundFiles[currentIndex]);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.addLineListener(event ->
            {
                if (event.getType() == LineEvent.Type.STOP && !isStopped && !clip.isRunning())
                {
                    clip.close();

                    currentIndex++;

                    if (currentIndex >= soundFiles.length)
                    {
                        currentIndex = 0;
                    }

                    playNext();
                }
            });

            clip.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Purpose: To stop the music and close the clip/playlist
     */
    
    public void stop()
    {
        isStopped = true;

        if (clip != null)
        {
            clip.stop();
            clip.close();
        }
    }
}

