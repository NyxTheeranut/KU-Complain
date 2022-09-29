package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.accounts.User;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.regex.Pattern;

public class EditProfileController {

    @FXML private TextField renameTextField;
    @FXML private ImageView profilePicture;

    @FXML public void handleProfilePicture(ActionEvent actionEvent) throws IOException {
        selectPicture();
    }
    @FXML public void handleProfileName(ActionEvent actionEvent) throws IOException {
        rename();
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
        Path to = Paths.get("src\\main\\resources\\ku\\cs\\image\\" + selectedFile.getName());
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES};
        Files.copy(from.toFile().toPath(), to.toFile().toPath(),options);

    }
    public void rename() throws IOException {
        String name =renameTextField.getText();
        final Path yourPath = Paths.get("src\\main\\resources\\ku\\cs\\data\\account_list.csv");
        byte[] buff = Files.readAllBytes(yourPath);
        String Name = new String(buff, Charset.defaultCharset());
        Name = Name.replace(Pattern.quote("dujrawee"),name);
        Files.write(yourPath, Name.getBytes());
    }
}
