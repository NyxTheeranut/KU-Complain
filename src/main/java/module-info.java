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
    exports ku.cs.services;
    opens ku.cs.services to javafx.fxml;
    exports ku.cs.models;
    opens ku.cs.models to javafx.fxml;
    exports ku.cs.models.accounts;
    opens ku.cs.models.accounts to javafx.fxml;
    exports ku.cs.models.complaints;
    opens ku.cs.models.complaints to javafx.fxml;
    exports ku.cs.models.category;
    opens ku.cs.models.category to javafx.fxml;
    exports ku.cs.services.accounts;
    opens ku.cs.services.accounts to javafx.fxml;
    exports ku.cs.services.complaints;
    opens ku.cs.services.complaints to javafx.fxml;
    exports ku.cs.services.categorytlists;
    opens ku.cs.services.categorytlists to javafx.fxml;
}