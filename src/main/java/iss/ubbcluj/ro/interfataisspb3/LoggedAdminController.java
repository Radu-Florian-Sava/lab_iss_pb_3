package iss.ubbcluj.ro.interfataisspb3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistenta.ActorRepo;
import service.ActorService;

import java.io.IOException;

public class LoggedAdminController {
    @FXML
    TextField addUsername, addPassword, addCheckPassword;
    private ActorService actorService;

    @FXML
    void initialize() {
    }

    void setActorService(ActorService actorService){
        this.actorService = actorService;
    }

    @FXML
    void addSectie() {
        String username = addUsername.getText();
        String password = addPassword.getText();
        String checkPassword = addCheckPassword.getText();

        if (username != "" && password != "" && checkPassword != "") {
            boolean adaugat = actorService.addMedic(username, password, checkPassword);
            if(!adaugat){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setContentText("Utilizatorul nu a putut fi adaugat!");
                alert.show();
            }
        }
    }

    @FXML
    void addFarmacie() {
        String username = addUsername.getText();
        String password = addPassword.getText();
        String checkPassword = addCheckPassword.getText();

        if (username != "" && password != "" && checkPassword != "") {
            boolean adaugat = actorService.addFarmacist(username, password, checkPassword);
            if(!adaugat){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setContentText("Utilizatorul nu a putut fi adaugat!");
                alert.show();
            }
        }
    }
}
