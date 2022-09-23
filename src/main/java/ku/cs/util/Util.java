package ku.cs.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import ku.cs.models.Button;
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

        Path from = Paths.get(selectedFile.toURI());
//        Path to = Paths.get("D:\\lab 211\\project211-araikordai\\src\\main\\resources\\ku\\cs\\image\\" + selectedFile.getName());
//        CopyOption[] options = new CopyOption[]{
//                StandardCopyOption.REPLACE_EXISTING,
//                StandardCopyOption.COPY_ATTRIBUTES};
//        Files.copy(from.toFile().toPath(), to.toFile().toPath(),options);
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

    public static HBox createButton(String buttonName,String element){
        HBox button = new HBox(7);
        button.setPrefSize(300,60);
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setPadding(new Insets(0,20,0,0));

        Label name = new Label(buttonName);
        name.setFont(FontLoader.font("ths",48));
        name.setTextFill(Color.web("#0076a3"));
        button.getChildren().add(name);

        if(element.contains(".")){
            Circle circle = new Circle(25);
            circle.setFill(new ImagePattern(new Image("file:data/image/profile/"+element)));
            button.getChildren().add(circle);
        }
        else{
            Label symbol = new Label(element);
            symbol.setFont(FontLoader.font("fa_wf",48));
            symbol.setTextFill(Color.web("#0076a3"));
            button.getChildren().add(symbol);
        }

        return button;
    }
}
