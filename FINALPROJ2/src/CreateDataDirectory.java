import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDataDirectory {
    public static void main(String[] args) {
        String projectDirectory = System.getProperty("user.dir");
        String dataDirectory = projectDirectory + File.separator + "data";

        try {
            Path path = Paths.get(dataDirectory);
            Files.createDirectories(path);
            System.out.println("Directory created: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}