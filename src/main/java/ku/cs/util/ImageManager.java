package ku.cs.util;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

public final class ImageManager {
    public static Image selectImage() throws IOException {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files",
                "*.png",
                "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(selectedFile.toURI().toString());

        //Path from = Paths.get(selectedFile.toURI());
        return image;

    }
    public static String saveImage(Image image, String directoryName) {
        Path from = Paths.get(image.getUrl()
                .replaceAll("file:/", "")
                .replaceAll("\"", File.separator)
                .replaceAll("%20", " "));
        File directory = new File("data" +File.separator+ "image" +File.separator+ directoryName);
        System.out.println(from);
        String name = directory.list().length + "";

        Path to = Paths.get((""+directory.toURI())
                .replaceAll("file:/", "")
                .replaceAll("\"", File.separator)
                .replaceAll("%20", " ")+File.separator+ name + ".jpg");

        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES};
        try {
            Files.copy(from.toFile().toPath(), to.toFile().toPath(),options);
        } catch (IOException e) {
            System.err.println(e);
            System.out.println("Error saving image");
        }
        return name+".jpg";
    }
    public static Image loadImage(String url) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(url.replaceAll("//", File.separator));
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open image");
            System.err.println(e);
        }
        Image image = new Image(fileInputStream);
        return image;
    }
}
