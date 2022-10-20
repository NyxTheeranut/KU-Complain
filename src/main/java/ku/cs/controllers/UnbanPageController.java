package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.User;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.util.Data;
import ku.cs.util.ObjectStorage;

public class UnbanPageController {
    @FXML TextArea detailTextArea;
    @FXML Button backButton;
    @FXML Label errorLabel;
    private Account account;

    public void initialize() {
        account = ((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount();
    }

    public void handleSubmitButton(){
        String detail = detailTextArea.getText();

        if (detail.isEmpty()) {
            errorLabel.setText("กรุณาใส่รายละเอียดการขอปลดแบน");
            return;
        }

        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();

        ((User)Data.search(account.getId().toString(), accountList.getAllAccount(), new AccountIdFilter())).setUnbanRequest(detail);

        errorLabel.setText("ส่งคำขอสำเร็จ");

        dataSource.writeData(accountList);
    }

    public void handleBackButton(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
