package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class EditProfileController {

    @FXML private TextField reNameTextField;
    @FXML private ImageView profilePicture;

    @FXML public void handleProfilePicture(ActionEvent actionEvent) throws IOException {
        selectPicture();
    }

    @FXML public void initialize(){
        /* String url = getClass().getResource("" + imagePath).toExternalForm();
        profilePicture.setImage(new Image(url));*/
    }
    public void selectPicture() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(selectedFile.toURI().toString());
        profilePicture.setImage(image);
        Path from = Paths.get(selectedFile.toURI());
        Path to = Paths.get("D:\\lab 211\\project211-araikordai\\src\\main\\resources\\ku\\cs\\image\\" + selectedFile.getName());
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES};
        Files.copy(from.toFile().toPath(), to.toFile().toPath(),options);

    }

}
