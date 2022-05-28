package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class navigation {
    public static void navigate(AnchorPane anchorPane, String location) throws IOException {
        anchorPane.getChildren().clear();
        Parent parent = FXMLLoader.load(navigation.class.getResource("../view/" + location + "-form.fxml"));
        anchorPane.getChildren().add(parent);
    }
}
