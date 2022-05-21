package view.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class Utilities {
    public static void loadui(AnchorPane anchorPane, String location) throws IOException {
        anchorPane.getChildren().clear();
        Parent parent= FXMLLoader.load(Utilities.class.getResource("../view/"+location+".fxml"));
        anchorPane.getChildren().add(parent);
    }
}
