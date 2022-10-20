package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.Pair;
import ku.cs.models.accounts.*;
import ku.cs.services.comparator.*;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.services.filter.AccountStatusFilter;
import ku.cs.services.filter.AccountUsernameContainFilter;
import ku.cs.util.Data;
import ku.cs.util.FontLoader;
import ku.cs.util.ImageManager;
import ku.cs.util.ObjectStorage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BlacklistController {
    @FXML
    private FlowPane accountArea;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<Pair<Comparator, String>> sortComboBox;
    @FXML
    private Button reverseButton;

    private ArrayList<Account> accounts;
    private ArrayList<Account> filteredAccountList;
    public void initialize() {
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        accounts = Data.filter("true", dataSource.readData().getAllAccount(), new AccountStatusFilter());
        filteredAccountList = accounts;

        setupAccountArea(accounts);
        setupSortComboBox();

    }

    Font ths1 = FontLoader.font("ths", 30);
    Font ths2 = FontLoader.font("ths", 20);


    private void setupAccountArea(ArrayList<Account> accounts) {
        accountArea.getChildren().clear();

        for (Account i:accounts) {
            User user;
            try {
                user = (User) i;
            }catch (ClassCastException e) {
                continue;
            }
            //setup hBox
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(0, 0, 0, 20));
            hBox.setPrefWidth(980);

            //setup unban button
            Button unbanButton = new Button();
            unbanButton.setOnAction(event -> toggleBan(user, unbanButton));
            unbanButton.setText("Unban");
            unbanButton.setFont(ths2);

            //setup profilePic
            ImageView profilePic= new ImageView(ImageManager.loadImage("data/image/account/" + user.getImagePath()));
            profilePic.setFitWidth(100);
            profilePic.setFitHeight(100);

            //setup vBox
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(0, 0, 0, 20));
            vBox.setPrefWidth(880);

            //setup hBox1
            HBox hBox1 = new HBox();
            hBox1.setPrefSize(960, 38);
            hBox1.setSpacing(10);

            //setup usernameLabel
            Label usernameLabel = new Label();
            usernameLabel.setText(user.getRole() + " " + user.getUsername());
            usernameLabel.setFont(ths1);

            hBox1.getChildren().add(usernameLabel);
            hBox1.getChildren().add(unbanButton);

            //setup hBox2
            HBox hBox2 = new HBox();
            hBox2.setPrefSize(960, 38);
            hBox2.setSpacing(20);

            //setup nameLabel
            Label nameLabel = new Label();
            nameLabel.setText(user.getName() + " " + user.getSurname());
            nameLabel.setFont(ths2);
            //nameLabel.setPrefWidth(200);

            //setup idLabel
            Label idLabel = new Label();
            idLabel.setText("id : " + user.getId().toString());
            idLabel.setFont(ths2);
            //idLabel.setPrefWidth(460);
            if (!nameLabel.getText().equals(" ")) hBox2.getChildren().add(nameLabel);
            hBox2.getChildren().add(idLabel);

            //setup hBox3
            HBox hBox3 = new HBox();
            hBox3.setPrefWidth(960);
            Label unbanRequestLabel = new Label();

            unbanRequestLabel.setText("Unban request : " + user.getUnbanRequest());
            unbanRequestLabel.setWrapText(true);
            unbanRequestLabel.setMaxWidth(960);
            unbanRequestLabel.setFont(ths2);
            if (!user.getUnbanRequest().equals("")) hBox3.getChildren().add(unbanRequestLabel);


            //setup hBox4
            HBox hBox4 = new HBox();
            hBox4.setPrefSize(960, 30);

            //setup lastLoginLabel
            Label lastLoginLabel = new Label();
            lastLoginLabel.setPrefWidth(715);
            Long yearsBetween = ChronoUnit.YEARS.between(user.getLastLogin(), LocalDateTime.now());
            Long monthsBetween = ChronoUnit.MONTHS.between(user.getLastLogin(), LocalDateTime.now());
            Long weeksBetween = ChronoUnit.WEEKS.between(user.getLastLogin(), LocalDateTime.now());
            Long daysBetween = ChronoUnit.DAYS.between(user.getLastLogin(), LocalDateTime.now());
            Long hoursBetween = ChronoUnit.HOURS.between(user.getLastLogin(), LocalDateTime.now());
            Long minutesBetween = ChronoUnit.MINUTES.between(user.getLastLogin(), LocalDateTime.now());
            if      (yearsBetween  >= 1) lastLoginLabel.setText("Last login : " + yearsBetween + " ปี");
            else if (monthsBetween >= 1) lastLoginLabel.setText("Last login : " + monthsBetween + " เดือน");
            else if (weeksBetween  >= 1) lastLoginLabel.setText("Last login : " + weeksBetween + " สัปดาห์");
            else if (daysBetween   >= 1) lastLoginLabel.setText("Last login : " + daysBetween + " วัน");
            else if (hoursBetween  >= 1) lastLoginLabel.setText("Last login : " + hoursBetween + " ชั่วโมง");
            else                         lastLoginLabel.setText("Last login : " + minutesBetween + " นาที");
            lastLoginLabel.setText(lastLoginLabel.getText() + "  Login attempt : " + user.getLoginAttempt());
            lastLoginLabel.setFont(ths2);

            //setup isBanLabel
            Label isBanLabel = new Label();
            isBanLabel.setText("Status : " + ((user.isBanned())?"banned" : "authorize"));
            isBanLabel.setFont(ths2);

            hBox4.getChildren().add(lastLoginLabel);
            hBox4.getChildren().add(isBanLabel);

            vBox.getChildren().add(hBox1);
            vBox.getChildren().add(hBox2);
            vBox.getChildren().add(hBox3);
            vBox.getChildren().add(hBox4);

            hBox.getChildren().add(profilePic);
            hBox.getChildren().add(vBox);

            accountArea.getChildren().add(hBox);

        }
    }
    private void toggleBan(User user, Button unbanButton) {

        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();

        User user1 = (User)Data.search(user.getId().toString(), accountList.getAllAccount(), new AccountIdFilter());

        if (user1.isBanned()) ((Admin)((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount()).ban(user1);
        else ((Admin)((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount()).unban(user1);

        unbanButton.setText((user1.isBanned())? "Unban" : "Ban");

        dataSource.writeData(accountList);
    }
    private void setupSortComboBox() {
        Callback<ListView<Pair<Comparator, String>>, ListCell<Pair<Comparator, String>>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Pair<Comparator, String> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getValue());
            }
        };

        sortComboBox.setCellFactory(factory);
        sortComboBox.setButtonCell(factory.call(null));

        ArrayList<Pair<Comparator, String>> comparatorList = new ArrayList<>();
        comparatorList.add(new Pair(new CompareAccountByUsername(), "Username"));
        comparatorList.add(new Pair(new CompareAccountByLoginAttempt(), "Login attempt"));

        sortComboBox.setItems(FXCollections.observableArrayList(comparatorList));
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
        Comparator comparator = sortComboBox.getValue().getKey();

        filteredAccountList = Data.filter(username, accounts, new AccountUsernameContainFilter());
        if (comparator != null)
            Collections.sort(filteredAccountList, comparator);

        setupAccountArea(filteredAccountList);
    }

    public void handleResetButton(){
        ((ObjectStorage) com.github.saacsos.FXRouter.getData()).getHomeController().loadPage("blacklist.fxml");
    }
}
