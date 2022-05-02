package iss.ubbcluj.ro.interfataisspb3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistenta.ActorRepo;
import service.ActorService;

import java.io.IOException;

public class NonAdminController {

    @FXML
    TextField medicNameTextField, farmacistNameTextField;
    @FXML
    PasswordField medicPasswordTextField, farmacistPasswordTextField;
    private ActorService actorService;

    @FXML
    void initialize() {
        actorService = new ActorService(new ActorRepo());
    }

    @FXML
    void loginMedic() {
        if (actorService.loginMedic(medicNameTextField.getText(), medicPasswordTextField.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fereastraSectie.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Autentificat ca si " + medicNameTextField.getText());
            try {
                stage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setContentText("Credentiale incorecte");
                alert.show();
            }
            stage.show();
            medicNameTextField.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eroare");
            alert.setContentText("Credentiale incorecte");
            alert.show();
        }
    }

    @FXML
    void loginFarmacist() {
        if (actorService.loginFarmacist(farmacistNameTextField.getText(), farmacistPasswordTextField.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fereastraFarmacist.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Autentificat ca si " + farmacistNameTextField.getText());
            try {
                stage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setContentText("Credentiale incorecte");
                alert.show();
            }
            stage.show();
            farmacistPasswordTextField.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eroare");
            alert.setContentText("Credentiale incorecte");
            alert.show();
        }
    }
}
