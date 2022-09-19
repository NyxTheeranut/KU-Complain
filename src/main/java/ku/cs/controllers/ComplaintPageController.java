package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Pair;
import ku.cs.models.complaints.Complaint;
import ku.cs.services.Utility;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.util.ArrayList;

public class ComplaintPageController {
    Complaint complaint = Utility.complaint;

    @FXML
    private Text topic;
    @FXML
    private Text category;
    @FXML
    private ListView data;


    public void initialize() {
        topic.setText(complaint.getTopic());
        category.setText(complaint.getCategory().getName());

        ArrayList<Pair<String, String>> field = complaint.getCategory().getFields();
        ArrayList<String> fields = complaint.getFields();
        System.out.println(field.size());

        for (int i=0; i<fields.size(); i++) {
            data.getItems().add(field.get(i).getKey() + field.get(i).getValue() + fields.get(i));
        }
    }

    public void handleBackButton() {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("Error loading home page");
        }
    }
}
