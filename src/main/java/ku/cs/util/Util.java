package ku.cs.util;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import ku.cs.models.accounts.Account;
import ku.cs.models.complaints.Complaint;
import ku.cs.services.searcher.Searcher;

import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public final class Util {
    //Object Storage
    static AbstractMap<String, Object> map = new HashMap<>();
    public static Account account;
    public static Complaint complaint;
    private Util(){};
    public static void add(String objectKey, Object object) {
        map.put(objectKey, object);
    }
    public static Object find(String objectKey){
        Object object = map.get(objectKey);
        if (object == null) {
            System.err.println(
                    "Load object <" + objectKey + "> failed.\n" +
                            "please check object key"
            );
            return null;
        }
        return object;
    }
    public static <T> T search(String id, ArrayList<T> objects, Searcher<T> searcher){
        for(T i : objects){
            //System.out.println(((Account)i).getName());
            if (searcher.found(i, id)){
                return i;
            }
        }
        return null;
    }
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

        String name = directory.list().length + "";

        Path to = Paths.get((""+directory.toURI())
                .replaceAll("file:/", "") +File.separator+ name + ".jpg");

        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES};
        try {
            Files.copy(from.toFile().toPath(), to.toFile().toPath(),options);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error saving image");
        }
        return name+".jpg";
    }

}
