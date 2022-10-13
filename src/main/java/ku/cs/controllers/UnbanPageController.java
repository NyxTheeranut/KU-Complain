package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.util.Data;
import ku.cs.util.ObjectStorage;

public class UnbanPageController {
    @FXML TextArea detailTextArea;
    @FXML Button backButton;
    private Account account;

    public void initialize() {
        account = ((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount();
    }

    public void handleSubmitButton(ActionEvent actionEvent){
        String detail = detailTextArea.getText();

        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();

        Data.search(account.getId().toString(), accountList.getAllAccount(), new AccountIdFilter()).setUnbanRequest(detail);

        dataSource.writeData(accountList);
    }

    public void handleBackButton(ActionEvent actionEvent){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
