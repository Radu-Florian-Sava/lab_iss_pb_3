package iss.ubbcluj.ro.interfataisspb3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import model.Reteta;
import model.RetetaId;
import service.RetetaService;

public class FarmacistController {

    ListCell<Reteta> lastSelected = null;

    @FXML
    TextArea detaliiTextArea;

    @FXML
    ListView<Reteta> listaReteteNeonorate;
    private RetetaService retetaService;
    private ObservableList<Reteta> listaObserverRetete;


    @FXML
    void initialize() {
        listaObserverRetete = FXCollections.observableList(retetaService.getAllNeonorate());
        listaReteteNeonorate.setItems(listaObserverRetete);
        listaReteteNeonorate.setCellFactory(new Callback<ListView<Reteta>, ListCell<Reteta>>() {
                                                @Override
                                                public ListCell<Reteta> call(ListView<Reteta> list) {
                                                    return new FarmacistController.ColorCell();
                                                }
                                            }
        );
    }

    void setRetetaService(RetetaService retetaService) {
        this.retetaService = retetaService;
    }

    @FXML
    void onoreazaComanda() {
        if (lastSelected != null) {
            RetetaId retetaId = lastSelected.getItem().getId();
            retetaService.finishCommand(retetaId);
            listaObserverRetete.clear();
            listaObserverRetete.addAll(retetaService.getAllNeonorate());
            lastSelected = null;
            detaliiTextArea.setText("");
        }
    }

    class ColorCell extends ListCell<Reteta> {
        @Override
        public void updateItem(Reteta item, boolean empty) {
            if (item == getItem()) return;
            super.updateItem(item, empty);
            selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected && !isEmpty() && item != null) {
                    detaliiTextArea.setText(item.getId().getNumeMedicament() + " x " + item.getCantitate().toString());
                    lastSelected = this;
                }
            });
            if (item != null) {
                setText(item.toString());
            } else {
                super.setText(null);
                super.setGraphic(null);
            }
        }


    }

}
