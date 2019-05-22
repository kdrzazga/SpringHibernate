package org.kd.main.client.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.Set;
import java.util.stream.Collectors;

import org.kd.main.client.presenter.PresenterHandler;
import org.kd.main.client.view.lib.PropertiesReader;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Customer;
import org.kd.main.common.entities.Transfer;

public class TraderViewController {

    private static PresenterHandler handler;

    @FXML
    private Tab devTab;

    @FXML
    private TableView<Transfer> tradeTable;

    @FXML
    private ChoiceBox<String> partyIdChoiceBox;

    @FXML
    private ChoiceBox<String> fundIdChoiceBox;

    @FXML
    private TextField cptyNameField;

    @FXML
    private TextField shortCptyNameField;

    @FXML
    private ChoiceBox<String> partyId4TradeChoiceBox;

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
                handler.loadBanks()
                        .stream()
                        .map(Bank::getId)
                        .map(Object::toString)
                        .collect(Collectors.toList()));

        partyIdChoiceBox.setItems(list);
        partyId4TradeChoiceBox.setItems(list);
    }

    public void loadFunds() {

        var list = FXCollections.observableArrayList(
                handler.loadCustomers()
                        .stream()
                        .map(Customer::getId)
                        .map(Object::toString)
                        .collect(Collectors.toList()));

        fundIdChoiceBox.setItems(list);
    }


    public void loadTrades() {
        var trades = FXCollections
                .observableArrayList(
                        handler.loadTransfers());

        var idColumn = new TableColumn<Transfer, String>("Id");
        var quantityColumn = new TableColumn<Transfer, String>("Units");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("units"));

        tradeTable.setItems(trades);
        tradeTable.getColumns().addAll(idColumn, quantityColumn);
    }

    @FXML
    protected void handleShowCptyAction(ActionEvent event) {

        var errorMsg = new PropertiesReader().readKey("error.message.party.not.selected");
        if (isNoneElementSelected(partyIdChoiceBox, showCptyButton, errorMsg)) return;

        var id = readPartyId();
        var party = handler.loadBank(id);

        if (party != null) {
            this.cptyNameField.setText(party.getName());
            this.shortCptyNameField.setText(party.getShortname());
        }
    }

    private int readPartyId() {
        return Integer.parseInt(partyIdChoiceBox.getValue());
    }

    @FXML
    protected void handleShowFundAction(ActionEvent event) {

        String errorMsg = new PropertiesReader().readKey("error.message.fund.not.selected");
        if (isNoneElementSelected(fundIdChoiceBox, showFundButton, errorMsg)) return;

        var id = readFundId();
        var fund = handler.loadFund(id);
        if (fund != null) {
            this.fundNameField.setText(fund.getName());
            this.fundShortNameField.setText(fund.getShortname());
            this.fundUnitsField.setText(String.valueOf(fund.getUnits()));
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
        String errorMsg = new PropertiesReader().readKey("error.message.party.not.selected");
        if (isNoneElementSelected(partyIdChoiceBox, showFundButton, errorMsg)) return;

        handler.saveBank(new Bank(readPartyId(), readPartyName(), readPartyShortName()));
    }

    @FXML
    protected void handleSaveFundAction(ActionEvent event) {
        String errorMsg = new PropertiesReader().readKey("error.message.fund.not.selected");
        if (isNoneElementSelected(fundIdChoiceBox, showFundButton, errorMsg)) return;

        handler.saveFund(new Customer(readFundShortName(), readFundName(),  readFundUnits(), readPartyId()));
    }

    private Set<Customer> readFundsAvailableForParty() {

        return Set.of(new Customer("", "", 0, 1001));//TODO: this will be implemented during migration to Hibernate 5
    }

    private String readPartyShortName() {
        return this.shortCptyNameField.getText();
    }

    private String readPartyName() {
        return this.cptyNameField.getText();
    }

    @FXML
    protected void handleBookTradeAction(ActionEvent event) {
        System.out.println("Book Transfer!");
    }

    private boolean isItemSelected(ChoiceBox<String> choiceBox) {

        return Optional.ofNullable(choiceBox)
                .map(ChoiceBox::getValue)
                .map(String::isEmpty)
                .isPresent();
    }

    private boolean isNoneElementSelected(ChoiceBox<String> choiceBox, Button showButton, String errorMessage) {
        if (isItemSelected(choiceBox)) return false;

        var owner = showButton.getScene().getWindow();
        var formErrorMsg = new PropertiesReader().readKey("error.message.party.not.selected");
        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, formErrorMsg,
                errorMessage);
        return true;
    }

    public static void setHandler(PresenterHandler handler) {
        TraderViewController.handler = handler;
    }
}
