package org.kd.main.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.kd.entities.Fund;
import org.kd.entities.Party;
import org.kd.main.Trader;
import org.kd.main.model.DataModelManager;

import java.util.Optional;
import java.util.stream.Collectors;

public class ViewerController {

    @FXML
    private Tab devTab;

    @FXML
    private TableView<String> tradeTable;

    @FXML
    private ChoiceBox<String> cptyIdChoiceBox;

    @FXML
    private ChoiceBox<String> fundIdChoiceBox;

    @FXML
    private TextField cptyNameField;

    @FXML
    private TextField shortCptyNameField;

    @FXML
    private ChoiceBox<String> cptyId4TradeChoiceBox;

    @FXML
    private TextField fundsField;

    @FXML
    private Button showCptyButton;

    @FXML
    private TextField fundNameField;

    @FXML
    private TextField fundShortNameField;

    @FXML
    private TextField fundUnitsField;

    @FXML
    private Button showFundButton;

    @FXML
    public void initialize() {
        devTab.setDisable(new PropertiesReader().readKey("dev-mode").equals("false"));
    }

    public void loadParties() {

        var list = FXCollections.observableArrayList(
                DataModelManager
                        .getPartyDao()
                        .getAvailableCptiesIds()
                        .stream()
                        .map(Party::getId)
                        .map(id -> id.toString())
                        .collect(Collectors.toList()));

        cptyIdChoiceBox.setItems(list);
        cptyId4TradeChoiceBox.setItems(list);
    }

    public void loadFunds() {

        var list = FXCollections.observableArrayList(
                DataModelManager
                        .getFundDao()
                        .getAvailableFunds()
                        .stream()
                        .map(Fund::getId)
                        .map(id -> id.toString())
                        .collect(Collectors.toList()));

        fundIdChoiceBox.setItems(list);
    }

    @FXML
    protected void handleShowCptyAction(ActionEvent event) {

        var errorMsg = new PropertiesReader().readKey("error.messsage.cpty.not.selected");
        if (isNoneElementSelected(cptyIdChoiceBox, showCptyButton, errorMsg)) return;

        var id = Long.parseLong(cptyIdChoiceBox.getValue());
        var cpty = DataModelManager.getPartyDao().get(id);
        if (cpty != null) {
            this.cptyNameField.setText(cpty.getName());
            this.shortCptyNameField.setText(cpty.getShortName());
        }
    }

    @FXML
    protected void handleShowFundAction(ActionEvent event) {

        String errorMsg = new PropertiesReader().readKey("error.messsage.fund.not.selected");
        if (isNoneElementSelected(fundIdChoiceBox, showFundButton, errorMsg)) return;

        var id = Long.parseLong(fundIdChoiceBox.getValue());
        var fund = DataModelManager.getFundDao().get(id);
        if (fund != null) {
            this.fundNameField.setText(fund.getName());
            this.fundShortNameField.setText(fund.getShortName());
            this.fundUnitsField.setText(fund.getUnits().toString());
        }
    }

    @FXML
    protected void handleBookTradeAction(ActionEvent event) {
        System.out.println("Book Trade!");
    }

    private boolean isItemSelected(ChoiceBox<String> choiceBox) {

        return Optional.ofNullable(choiceBox)
                .map(ChoiceBox::getValue)
                .map(String::isEmpty)
                .isPresent();
    }

    private boolean isNoneElementSelected(ChoiceBox<String> fundIdChoiceBox, Button showFundButton, String errorMessage) {
        if (isItemSelected(fundIdChoiceBox)) return false;

        var owner = showFundButton.getScene().getWindow();
        var formErrorMsg = new PropertiesReader().readKey("error.messsage.cpty.not.selected");
        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, formErrorMsg,
                errorMessage);
        return true;

    }
}
