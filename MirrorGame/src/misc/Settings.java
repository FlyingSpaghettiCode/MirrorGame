import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Remembers the game settings.
 * Ignore lines with "#" as they are comments.
 * @author Adriano
 *
 */
public class Settings {
	private final String PATH = "/misc/Settings.txt";
	private List<String> docText() throws IOException{
		return Files.readAllLines(Paths.get(PATH));
	}
}
