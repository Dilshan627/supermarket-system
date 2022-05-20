package controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane context;

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        util.Utilities.loadui(context,"dashboard-form");
    }
}
