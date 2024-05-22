package com.example.doancuoiky2.Controller;

import com.example.doancuoiky2.DAO.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControllerSignUp {


    @FXML
    private PasswordField passwordText;

    @FXML
    private Button regisBtn;

    @FXML
    private TextField usernameText;

    private Stage stage;
    private Scene scene;
    private Parent root;


public void switchLogin(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("/com/example/doancuoiky2/FirstLogin.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
private PreparedStatement prepare;
private Connection connection;
private ResultSet result;

    public void signUp() {
        String sql = "SELECT * FROM thongtinkh WHERE username = ? and matkhau = ?";
        connection = DAO.connection;

        try{
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, usernameText.getText());
            prepare.setString(2, passwordText.getText());

            result = prepare.executeQuery();

            Alert alert;

            if(usernameText.getText().isEmpty() || passwordText.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                if(result.next()){
                    // TAKE THE USERNAME OF USER
                    //getData.username = usernameText.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                    // HIDE YOUR LOGIN FORM
                    regisBtn.getScene().getWindow().hide();
                    // LINK YOUR DASHBOARD FORM

                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancuoiky2/FirstLogin.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);



                    stage.setScene(scene);
                    stage.show();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }



}

