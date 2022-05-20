package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.UserDTO;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    public AnchorPane context;
    public JFXPasswordField pwptxt;
    public JFXTextField usertxt;
    public Label createbtn;
    public JFXButton signinbtn;
    public JFXComboBox stasuscmb;
    public JFXButton signupbtn;

    public void initialize(){
        ObservableList observableList=FXCollections.observableArrayList();
        observableList.add("Administrator");
        observableList.add("Cashier");
        stasuscmb.setItems(observableList);
    }

    public static ArrayList<UserDTO> user=new ArrayList<>();

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        if (usertxt.getText().isEmpty() && pwptxt.getText().isEmpty()){

        }else {

            util.Utilities.loadui(context, "dashboard-form");
        }

    }

    public void createAccountOnAction(MouseEvent mouseEvent) throws IOException {
        createVisible();
    }

    public void signupOnAction(ActionEvent actionEvent) {
        if (usertxt.getText().isEmpty() && pwptxt.getText().isEmpty() && stasuscmb.getValue().equals("")){
            System.out.println("create");
        }else {
            user.add(new UserDTO(usertxt.getText(),pwptxt.getText(), (String) stasuscmb.getValue()));
            loginVisible();
        }

    }

    public void createVisible() {
        signinbtn.setVisible(false);
        createbtn.setVisible(false);
        stasuscmb.setVisible(true);
        signupbtn.setVisible(true);
    }

    public void loginVisible() {
        signinbtn.setVisible(true);
        createbtn.setVisible(true);
        stasuscmb.setVisible(false);
        signupbtn.setVisible(false);
    }

}
