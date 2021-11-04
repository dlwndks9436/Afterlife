import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGM implements Runnable {

    File file;
    AudioInputStream audioStream = null;
    Clip clip = null;
    String location;

    BGM(String location) {
        this.location = location;
        if (this.location.equals("시작"))
            file = new File("Old-RuneScape-Soundtrack-Deadlands.wav");
        else if (this.location.equals("마을"))
            file = new File("Old-RuneScape-Soundtrack-Wilderness3.wav");
        else if (this.location.equals("나태한 자들의 평원"))
            file = new File("Old-RuneScape-Soundtrack-Spooky2.wav");
        else if (this.location.equals("속삭임이 가득한 숲"))
            file = new File("Old-RuneScape-Soundtrack-Talking-Forest.wav");
        else if (this.location.equals("드높은 협곡"))
            file = new File("Old-RuneScape-Soundtrack-Inadequacy.wav");
        else
            file = new File("Old-RuneScape-Soundtrack-Wilderness.wav");
    }

    public void exit() {
//        clip.stop();
//        clip.flush();
    }

    public void run() {
//        if (file != null) {
//            try {
//                audioStream = AudioSystem.getAudioInputStream(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (UnsupportedAudioFileException e) {
//                e.printStackTrace();
//            }
//            try {
//                clip = AudioSystem.getClip();
//                clip.open(audioStream);
//            } catch (LineUnavailableException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            clip.loop(clip.LOOP_CONTINUOUSLY);
//            clip.start();
//        }
    }
}

