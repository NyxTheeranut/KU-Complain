module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml;
    exports ku.cs.controllers.button;
    opens ku.cs.controllers.button to javafx.fxml;
//    exports ku.cs.services;
//    opens ku.cs.services to javafx.fxml;
    exports ku.cs.models;
    opens ku.cs.models to javafx.fxml;
    exports ku.cs.models.accounts;
    opens ku.cs.models.accounts to javafx.fxml;
    exports ku.cs.models.complaints;
    opens ku.cs.models.complaints to javafx.fxml;
    exports ku.cs.models.category;
    opens ku.cs.models.category to javafx.fxml;
    exports ku.cs.services.datasource.accounts;
    opens ku.cs.services.datasource.accounts to javafx.fxml;
    exports ku.cs.services.datasource.complaints;
    opens ku.cs.services.datasource.complaints to javafx.fxml;
    exports ku.cs.services.datasource.categorytlists;
    opens ku.cs.services.datasource.categorytlists to javafx.fxml;
    exports ku.cs.services.datasource;
    opens ku.cs.services.datasource to javafx.fxml;
    exports ku.cs.util;
    opens ku.cs.util to javafx.fxml;
}