package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import ku.cs.models.complaints.Complaint;
import ku.cs.objectcollector.DataBank;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ComplaintPageController {
    Complaint complaint = DataBank.complaint;

    @FXML
    private Text topic;
    @FXML
    private Text category;
    @FXML
    private ListView data;


    public void initialize() {
        topic.setText(complaint.getTopic());
        category.setText(complaint.getCategory());

        data.getItems().add(complaint.getDetail());
    }

    public void handleBackButton() {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("Error loading home page");
        }
    }
}
