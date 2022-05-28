package iss.ubbcluj.ro.interfataisspb3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Medicament;
import model.Reteta;
import model.RetetaId;
import service.MedicamentService;
import service.RetetaService;

public class SectieController {
    String username;

    @FXML
    TextArea detaliiComanda, detaliiMedicament;

    @FXML
    ListView<Reteta> listaComenziNeonorate, listaComenziOnorate;

    @FXML
    ComboBox<Medicament> comboBoxMedicamente;

    @FXML
    Spinner<Integer> spinnerCantitate;

    private RetetaService retetaService;
    private MedicamentService medicamentService;
    private ObservableList<Reteta> listaObserverReteteNeonorate, listaObserverReteteOnorate;
    private ObservableList<Medicament> listaObserverMedicamente;

    @FXML
    void initialize() {
        listaObserverReteteNeonorate = FXCollections.observableList(retetaService.getAllNeonorateOf(username));
        listaObserverReteteOnorate = FXCollections.observableList(retetaService.getAllOnorateOf(username));
        listaObserverMedicamente = FXCollections.observableList(medicamentService.getAllVisible());
        listaComenziNeonorate.setItems(listaObserverReteteNeonorate);
        listaComenziOnorate.setItems(listaObserverReteteOnorate);
        comboBoxMedicamente.setItems(listaObserverMedicamente);

        listaComenziOnorate.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detaliiComanda.setText(newValue.getId().getNumeMedicament() + " x " + newValue.getCantitate().toString());
        });

        listaComenziNeonorate.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detaliiComanda.setText(newValue.getId().getNumeMedicament() + " x " + newValue.getCantitate().toString());
        });

        spinnerCantitate.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10));
    }

    void setRetetaService(RetetaService retetaService) {
        this.retetaService = retetaService;
    }

    void setMedicamentService(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    void setUsername(String username) {
        this.username = username;
    }

    @FXML
    void loadDescription() {
        if (comboBoxMedicamente.getValue() != null) {
            detaliiMedicament.setText(comboBoxMedicamente.getValue().getDescriere());
        }
    }

    @FXML
    void cancel(){
        RetetaId retetaId = new RetetaId();
        retetaId.setData(null);
        retetaId.setNumeSectie(username);
        retetaService.cancelCommand(retetaId);
        listaObserverReteteNeonorate.setAll(retetaService.getAllNeonorateOf(username));
    }

    @FXML
    void addMedicament(){
        String numeMedicamnet = comboBoxMedicamente.getValue().getDenumire();
        Integer cantitate= spinnerCantitate.getValue();

        if(numeMedicamnet !=null && cantitate!=null) {
            RetetaId retetaId = new RetetaId();
            retetaId.setData(null);
            retetaId.setNumeSectie(username);
            retetaId.setNumeMedicament(numeMedicamnet);
            Reteta reteta = new Reteta();
            reteta.setId(retetaId);
            reteta.setCantitate(cantitate);
            reteta.setOnorata(false);
            retetaService.addRetetaWithLastTimestamp(reteta);
            listaObserverReteteNeonorate.setAll(retetaService.getAllNeonorateOf(username));
        }
    }

    @FXML
    void addElement(){
        retetaService.eraseTimestamp();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setContentText("Comanda a fost adaugata cu succes!");
        alert.show();
    }
}
