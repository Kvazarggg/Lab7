package sample.test_fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button getAPIButton;

    @FXML
    void initialize() {
        getAPIButton.setSkin(new MyButtonSkin(getAPIButton));
        getAPIButton.setOnAction(event -> {
           getAPIButton.getScene().getWindow().hide();

           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/sample/test_fx/TableWithData.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Lab 7 - Bilyi Max");
            stage.getIcons().add(new Image("https://cdn3.iconfinder.com/data/icons/pixomania/128/anchor-256.png"));
            stage.showAndWait();

        });
    }

}