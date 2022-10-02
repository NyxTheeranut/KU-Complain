package ku.cs.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import ku.cs.models.accounts.Account;
import ku.cs.models.complaints.Complaint;
import ku.cs.services.filter.ComplaintCategoryFilter;
import ku.cs.services.filter.ComplaintIdFilter;
import ku.cs.services.filter.ComplaintTopicFilter;
import ku.cs.services.filter.Filterer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public final class Util {
    //Object Storage
    static AbstractMap<String, Object> map = new HashMap<>();
    public static Account account;
    public static Complaint complaint;

    public final static ArrayList<Filterer> complaintFilter = new ArrayList<>(Arrays.asList(
            new ComplaintCategoryFilter(),
            new ComplaintIdFilter(),
            new ComplaintTopicFilter()
    ));

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
    public static <T> T search(String id, ArrayList<T> objects, Filterer<T> filterer){
        for(T i : objects){
            //System.out.println(((Account)i).getName());
            if (filterer.found(i, id)){
                return i;
            }
        }
        return null;
    }
    public static <T> ArrayList<T> filter(String filter, ArrayList<T> objects, Filterer<T> filterer) {
        ArrayList<T> result = new ArrayList<>();
        for(T i : objects){
            //System.out.println(((Account)i).getName());
            if (filterer.found(i, filter)){
                result.add(i);
            }
        }
        return result;
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

    public static HBox createButton(String buttonName,String element){
        HBox button = new HBox(15);
        button.setPrefSize(300,60);
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setPadding(new Insets(0,10,0,0));

        Label name = new Label(buttonName);
        name.setFont(FontLoader.font("ths",48));
        name.setTextFill(Color.web("#9d9fa1"));
        button.getChildren().add(name);

        if(element.contains(".")){
            Circle circle = new Circle(25);
            circle.setFill(new ImagePattern(new Image("file:data/image/profile/"+element)));
            button.getChildren().add(circle);
        }
        else{
            Label symbol = new Label(element);
            symbol.setFont(FontLoader.font("fa_wf",48));
            symbol.setTextFill(Color.web("#9d9fa1"));
            button.getChildren().add(symbol);
        }

        return button;
    }
}
