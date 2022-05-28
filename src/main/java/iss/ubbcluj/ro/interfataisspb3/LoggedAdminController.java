package iss.ubbcluj.ro.interfataisspb3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.GenericActor;
import model.Medicament;
import service.ActorService;
import service.MedicamentService;

public class LoggedAdminController {
    @FXML
    TextField addUsername, addPassword, addCheckPassword, addDenumire, addCantitate, toggleDenumire;

    @FXML
    TextArea addDescriere;

    @FXML
    ListView<Medicament> listaMedicamente;

    @FXML
    ListView<GenericActor> listaNonAdmin;

    private ActorService actorService;
    private MedicamentService medicamentService;
    private ObservableList<Medicament> listaObserverMedicamente;
    private ObservableList<GenericActor> listaObserverNonAdmin;


    @FXML
    void initialize() {
        listaObserverMedicamente = FXCollections.observableList(medicamentService.getAll());
        listaObserverNonAdmin = FXCollections.observableList(actorService.getAllNonAdmin());
        listaMedicamente.setItems(listaObserverMedicamente);
        listaNonAdmin.setItems(listaObserverNonAdmin);

        listaMedicamente.setCellFactory(new Callback<ListView<Medicament>, ListCell<Medicament>>() {
                                            @Override
                                            public ListCell<Medicament> call(ListView<Medicament> list) {
                                                return new ColorCell();
                                            }
                                        }
        );

    }

    void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    void setMedicamentService(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @FXML
    void addSectie() {
        String username = addUsername.getText();
        String password = addPassword.getText();
        String checkPassword = addCheckPassword.getText();

        if (username != "" && password != "" && checkPassword != "") {
            boolean adaugat = actorService.addMedic(username, password, checkPassword);
            if (!adaugat) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setContentText("Utilizatorul nu a putut fi adaugat!");
                alert.show();
            } else {
                listaObserverNonAdmin.add(actorService.getOne(username));
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
            if (!adaugat) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setContentText("Utilizatorul nu a putut fi adaugat!");
                alert.show();
            } else {
                listaObserverNonAdmin.add(actorService.getOne(username));
            }

        }
    }

    @FXML
    void addMedicament() {
        String denumire = addDenumire.getText();
        String descriere = addDescriere.getText();
        int cantitate = Integer.parseInt(addCantitate.getText());

        if (denumire != "" && descriere != "" && cantitate > 0) {
            medicamentService.addMedicament(denumire, descriere, cantitate);
            listaObserverMedicamente.add(medicamentService.getOne(denumire));
        }
    }

    @FXML
    void toggleMedicament() {
        String denumire = toggleDenumire.getText();

        if (denumire != "") {
            medicamentService.toggleMedicament(denumire);
            listaObserverMedicamente.setAll(medicamentService.getAll());
        }
    }

    static class ColorCell extends ListCell<Medicament> {
        @Override
        public void updateItem(Medicament item, boolean empty) {
            super.updateItem(item, empty);
            selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected && !isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Descriere");
                    alert.setContentText(item.getDescriere());
                    alert.show();
                }
            });
            if (item != null) {
                setText(item.toString());
                if (item.isUtilizabil()) {
                    this.setStyle("-fx-background-color: GREEN;");
                } else {
                    this.setStyle("-fx-background-color: RED;");
                }
            }
        }
    }
}
