import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Adriano
 * This stores the progress of the player, by simply providing status for each level.
 * We might it expand it to remember the level state, but for now it will just start from the beginning of the level;
 * levels are such that restarted from the beginning does not destroy much progress, as most of the intralevel
 * progress is done by thinking about the solution.
 * Lines with # are comments just for clarity.
 */
public class Progress {
	private final String PATH = "/misc/Progress.txt";
	private List<String> docText() throws IOException{
		return Files.readAllLines(Paths.get(PATH));
	}
}
