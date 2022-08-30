package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.services.ComplaintListFileDataSource;
import ku.cs.services.DataSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ComplaintPostPageController {
    @FXML private TextField topicField;
    @FXML private TextField categoryField;

    public void addComplaint(){
        //System.out.println("1");
        /*File file = new File("src/main/resources/ku/cs/data/complaint_list.csv");
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.newLine();
            buffer.write(topicField.getText()+","+categoryField.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }*/
        DataSource<ComplaintList> dataSource = new ComplaintListFileDataSource();
        ComplaintList complaintList = dataSource.readData();
        complaintList.addComplaint(new Complaint(topicField.getText(),categoryField.getText()));
        dataSource.writeData(complaintList);
    }

}
