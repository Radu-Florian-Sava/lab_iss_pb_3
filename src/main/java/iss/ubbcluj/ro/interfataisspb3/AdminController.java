package iss.ubbcluj.ro.interfataisspb3;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistenta.ActorRepo;
import persistenta.MedicamentRepo;
import persistenta.SessionFactorySingleton;
import service.ActorService;
import service.MedicamentService;

import java.io.IOException;

public class AdminController {

    @FXML
    TextField nameTextField;
    @FXML
    PasswordField passwordTextField;

    private ActorService actorService;

    @FXML
    void initialize() {
        actorService = new ActorService(new ActorRepo(SessionFactorySingleton.getSessionFactory()));
    }

    @FXML
    void login() {
        if (actorService.loginAdmin(nameTextField.getText(), passwordTextField.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fereastraAdministrator.fxml"));
            LoggedAdminController loggedAdminController = new LoggedAdminController();
            loggedAdminController.setActorService(actorService);
            loggedAdminController.setMedicamentService(new MedicamentService(new MedicamentRepo(SessionFactorySingleton.getSessionFactory())));
            fxmlLoader.setController(loggedAdminController);
            Stage stage = new Stage();
            stage.setTitle("Autentificat ca si " + nameTextField.getText());
            try {
                stage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setContentText("Credentiale incorecte");
                alert.show();
            }
            stage.show();
            nameTextField.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eroare");
            alert.setContentText("Credentiale incorecte");
            alert.show();
        }
    }

}