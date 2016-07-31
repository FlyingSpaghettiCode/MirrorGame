package sounds;

import java.nio.file.Paths;

import javafx.scene.media.AudioClip;

/**
 * 
 * @author Adriano
 *
 */
public class SoundPlayer {

	//math must start src/ instead of of /
	private String PATH; //path to media
	
	public SoundPlayer(String PATH) {
		this.PATH = PATH;
	}
	public void playSound(){
		new AudioClip(Paths.get(PATH).toUri().toString()).play();
	}
	public String getPath() {return PATH;}
	public void setPath(String pATH) {PATH = pATH;}
}
