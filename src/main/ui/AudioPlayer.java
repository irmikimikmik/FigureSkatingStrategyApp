package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

// An utility class for playing back audio files using Java Sound API.
// @author www.codejava.net
public class AudioPlayer implements LineListener {

    private boolean playCompleted;
    private boolean isStopped;
    private boolean isPaused;
    private Clip audioClip;

    // EFFECTS: loads the audioFile by following the file path, throws UnsupportedAudioFileException if the type of the
    //          audio file is not .wav, IOException if file can't be found and LineUnavailableException if line from
    //          the audio file cannot be found.
    public void load(String audioFilePath)
            throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        File audioFile = new File(audioFilePath);

        AudioInputStream audioStream = AudioSystem
                .getAudioInputStream(audioFile);

        AudioFormat format = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(Clip.class, format);

        audioClip = (Clip) AudioSystem.getLine(info);

        audioClip.addLineListener(this);

        audioClip.open(audioStream);
    }

    // EFFECTS: plays the loaded audio
    public void play() {

        audioClip.start();

        playCompleted = false;
        isStopped = false;
        isPaused = false;

        while (!playCompleted) {
            // wait for the playback completes
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                if (isStopped) {
                    audioClip.stop();
                    break;
                }
                if (isPaused) {
                    audioClip.stop();
                } else {
                    System.out.println("!!!!");
                    audioClip.start();
                }
            }
        }

        audioClip.close();

    }

    // EFFECTS: stops the audio
    public void stop() {
        isStopped = true;
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
            if (isStopped || !isPaused) {
                playCompleted = true;
            }
        }
    }
}