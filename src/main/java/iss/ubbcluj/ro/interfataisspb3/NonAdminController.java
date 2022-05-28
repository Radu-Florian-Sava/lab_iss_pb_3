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
import persistenta.RetetaRepo;
import persistenta.SessionFactorySingleton;
import service.ActorService;
import service.MedicamentService;
import service.RetetaService;

import java.io.IOException;

public class NonAdminController {

    @FXML
    TextField medicNameTextField, farmacistNameTextField;
    @FXML
    PasswordField medicPasswordTextField, farmacistPasswordTextField;
    private ActorService actorService;

    @FXML
    void initialize() {
        actorService = new ActorService(new ActorRepo(SessionFactorySingleton.getSessionFactory()));
    }

    @FXML
    void loginMedic() {
        if (actorService.loginMedic(medicNameTextField.getText(), medicPasswordTextField.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fereastraSectie.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Autentificat ca si " + medicNameTextField.getText());
            try {
                SectieController sectieController = new SectieController();
                sectieController.setRetetaService(new RetetaService(new RetetaRepo(SessionFactorySingleton.getSessionFactory())));
                sectieController.setMedicamentService(new MedicamentService(new MedicamentRepo(SessionFactorySingleton.getSessionFactory())));
                sectieController.setUsername(medicNameTextField.getText());
                fxmlLoader.setController(sectieController);
                stage.setTitle("Autentificat ca si " + medicNameTextField.getText());
                stage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException ignored) {
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
                FarmacistController farmacistController = new FarmacistController();
                farmacistController.setRetetaService(new RetetaService(new RetetaRepo(SessionFactorySingleton.getSessionFactory())));
                fxmlLoader.setController(farmacistController);
                stage.setTitle("Autentificat ca si " + farmacistNameTextField.getText());
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
