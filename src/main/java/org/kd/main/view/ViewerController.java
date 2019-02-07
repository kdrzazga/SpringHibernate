package org.kd.main.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.Set;
import java.util.stream.Collectors;

import org.kd.entities.Fund;
import org.kd.entities.Party;
import org.kd.main.model.DataModelManager;

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
                        .map(Object::toString)
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
                        .map(Object::toString)
                        .collect(Collectors.toList()));

        fundIdChoiceBox.setItems(list);
    }

    @FXML
    protected void handleShowCptyAction(ActionEvent event) {

        var errorMsg = new PropertiesReader().readKey("error.message.party.not.selected");
        if (isNoneElementSelected(cptyIdChoiceBox, showCptyButton, errorMsg)) return;

        var id = readPartyId();
        var party = DataModelManager.getPartyDao().get(id);
        if (party != null) {
            this.cptyNameField.setText(party.getName());
            this.shortCptyNameField.setText(party.getShortName());
        }
    }

    private long readPartyId() {
        return Long.parseLong(cptyIdChoiceBox.getValue());
    }

    @FXML
    protected void handleShowFundAction(ActionEvent event) {

        String errorMsg = new PropertiesReader().readKey("error.message.fund.not.selected");
        if (isNoneElementSelected(fundIdChoiceBox, showFundButton, errorMsg)) return;

        var id = readFundId();
        var fund = DataModelManager.getFundDao().get(id);
        if (fund != null) {
            this.fundNameField.setText(fund.getName());
            this.fundShortNameField.setText(fund.getShortName());
            this.fundUnitsField.setText(fund.getUnits().toString());
        }
    }

    private long readFundId() {
        return OptionalLong
                .of(Long.parseLong(fundIdChoiceBox.getValue()))
                .orElse(0);
    }

    private String readFundShortName() {
        return this.fundShortNameField.getText();
    }

    private String readFundName() {
        return this.fundNameField.getText();
    }

    private double readFundUnits() {
        return OptionalDouble
                .of(Double.valueOf(this.fundUnitsField.getText()))
                .orElse(0);
    }

    @FXML
    protected void handleSavePartyAction(ActionEvent event) {
        DataModelManager
                .getPartyDao()
                .save(new Party(readPartyId(), readPartyName(), readPartyShortName(), readFunds()));
    }

    @FXML
    protected void handleSaveFundAction(ActionEvent event) {
        DataModelManager
                .getFundDao()
                .save(new Fund(readFundId(), readFundName(), readFundShortName(), readFundUnits()));
    }

    private Set<Fund> readFunds() {
        return Set.of(new Fund());//TODO: implement this
    }

    private String readPartyShortName() {
        return this.shortCptyNameField.getText();
    }

    private String readPartyName() {
        return this.cptyNameField.getText();
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
        var formErrorMsg = new PropertiesReader().readKey("error.message.party.not.selected");
        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, formErrorMsg,
                errorMessage);
        return true;

    }
}
