package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.*;
import ku.cs.util.Data;
import ku.cs.util.FontLoader;
import ku.cs.util.ObjectStorage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public class AccountListController {
    @FXML
    private FlowPane accountArea;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private Button reverseButton;

    private ArrayList<Account> accounts;
    private ArrayList<Account> filteredAccountList;
    public void initialize() {
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        accounts = dataSource.readData().getAllAccount();
        filteredAccountList = accounts;

        setupAccountArea(accounts);
        setupRoleComboBox();

    }

    Font ths1 = FontLoader.font("ths", 30);
    Font ths2 = FontLoader.font("ths", 20);


    private void setupAccountArea(ArrayList<Account> accounts) {
        accountArea.getChildren().clear();

        for (Account i:accounts) {
            //setup hBox
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(0, 0, 0, 20));
            hBox.setPrefSize(980, 100);

            //setup profilePic
            ImageView profilePic= new ImageView(i.getImage());
            profilePic.setFitWidth(90);
            profilePic.setFitHeight(90);

            //setup vBox
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(0, 0, 0, 20));
            vBox.setPrefSize(880, 100);

            //setup hBox1
            HBox hBox1 = new HBox();
            hBox1.setPrefSize(960, 38);

            //setup usernameLabel
            Label usernameLabel = new Label();
            usernameLabel.setText(i.getUsername());
            usernameLabel.setFont(ths1);
            usernameLabel.setPrefWidth(715);

            //setup roleLabel
            Label roleLabel = new Label();
            roleLabel.setText("Role : " + i.getRole());
            roleLabel.setFont(ths1);

            hBox1.getChildren().add(usernameLabel);
            hBox1.getChildren().add(roleLabel);

            //setup hBox2
            HBox hBox2 = new HBox();
            hBox2.setPrefSize(960, 30);
            hBox2.setSpacing(20);

            //setup nameLabel
            Label nameLabel = new Label();
            nameLabel.setText(i.getName() + " " + i.getSurname());
            nameLabel.setFont(ths2);

            //setup idLabel
            Label idLabel = new Label();
            idLabel.setText("id : " + i.getId().toString());
            idLabel.setFont(ths2);

            hBox2.getChildren().add(nameLabel);
            hBox2.getChildren().add(idLabel);

            //setup hBox3
            HBox hBox3 = new HBox();
            hBox3.setPrefSize(960, 30);

            //setup lastLoginLabel
            Label lastLoginLabel = new Label();
            lastLoginLabel.setPrefWidth(715);
            Long yearsBetween = ChronoUnit.YEARS.between(i.getLastLogin(), LocalDateTime.now());
            Long monthsBetween = ChronoUnit.MONTHS.between(i.getLastLogin(), LocalDateTime.now());
            Long weeksBetween = ChronoUnit.WEEKS.between(i.getLastLogin(), LocalDateTime.now());
            Long daysBetween = ChronoUnit.DAYS.between(i.getLastLogin(), LocalDateTime.now());
            Long hoursBetween = ChronoUnit.HOURS.between(i.getLastLogin(), LocalDateTime.now());
            Long minutesBetween = ChronoUnit.MINUTES.between(i.getLastLogin(), LocalDateTime.now());
            if      (yearsBetween  >= 1) lastLoginLabel.setText("Last login : " + yearsBetween + " ปี");
            else if (monthsBetween >= 1) lastLoginLabel.setText("Last login : " + monthsBetween + " เดือน");
            else if (weeksBetween  >= 1) lastLoginLabel.setText("Last login : " + weeksBetween + " สัปดาห์");
            else if (daysBetween   >= 1) lastLoginLabel.setText("Last login : " + daysBetween + " วัน");
            else if (hoursBetween  >= 1) lastLoginLabel.setText("Last login : " + hoursBetween + " ชั่วโมง");
            else                         lastLoginLabel.setText("Last login : " + minutesBetween + " นาที");
            lastLoginLabel.setFont(ths2);

            //setup isBanLabel
            Label isBanLabel = new Label();
            isBanLabel.setText("Status : " + ((i.getIsBanned())?"banned" : "authorize"));
            isBanLabel.setFont(ths2);

            hBox3.getChildren().add(lastLoginLabel);
            hBox3.getChildren().add(isBanLabel);

            vBox.getChildren().add(hBox1);
            vBox.getChildren().add(hBox2);
            vBox.getChildren().add(hBox3);

            hBox.getChildren().add(profilePic);
            hBox.getChildren().add(vBox);

            accountArea.getChildren().add(hBox);

        }
    }
    private void setupRoleComboBox() {
        roleComboBox.setItems(FXCollections.observableArrayList(
                "user",
                "moderator",
                "admin"
        ));
    }
    public void handleSearchButton(){
        filterAccount();
    }
    public void handleReverseButton(){
        if ((reverseButton.getText().equals("\uF0D8"))) reverseButton.setText("\uF0D7");
        else reverseButton.setText("\uF0D8");
        Collections.reverse(filteredAccountList);
        setupAccountArea(filteredAccountList);
    }
    private void filterAccount() {
        String username = searchTextField.getText();
        String role = roleComboBox.getValue();

        filteredAccountList = Data.filter(username, accounts, new AccountUsernameFilter());
        if (role != null)
            filteredAccountList = Data.filter(role, filteredAccountList, new AccountRoleFilter());

        setupAccountArea(filteredAccountList);
    }

    public void handleResetButton(){
        ((ObjectStorage) com.github.saacsos.FXRouter.getData()).getHomeController().loadPage("account_list.fxml");
    }
}
