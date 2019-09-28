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
    private TableView<Transfer> transferTable;

    @FXML
    private ChoiceBox<String> bankIdChoiceBox;

    @FXML
    private ChoiceBox<String> customerIdChoiceBox;

    @FXML
    private TextField bankNameField;

    @FXML
    private TextField shortBankNameField;

    @FXML
    private ChoiceBox<String> bankId4TradeChoiceBox;

    @FXML
    private TextField customersField;

    @FXML
    private Button showBankButton;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerShortNameField;

    @FXML
    private TextField customerCashField;

    @FXML
    private Button showCustomerButton;

    @FXML
    public void initialize() {
        devTab.setDisable(new PropertiesReader().readKey("dev-mode").equals("false"));
    }

    public void loadParties() {

        var list = FXCollections.observableArrayList(
                handler.readBanks()
                        .stream()
                        .map(Bank::getId)
                        .map(Object::toString)
                        .collect(Collectors.toList()));

        bankIdChoiceBox.setItems(list);
        bankId4TradeChoiceBox.setItems(list);
    }

    public void loadFunds() {

        var list = FXCollections.observableArrayList(
                handler.readCustomers()
                        .stream()
                        .map(Customer::getId)
                        .map(Object::toString)
                        .collect(Collectors.toList()));

        customerIdChoiceBox.setItems(list);
    }

    public void loadTrades() {
        var trades = FXCollections
                .observableArrayList(
                        handler.readTransfers());

        var idColumn = new TableColumn<Transfer, String>("Id");
        var quantityColumn = new TableColumn<Transfer, String>("Units");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("units"));

        transferTable.setItems(trades);
        transferTable.getColumns().addAll(idColumn, quantityColumn);
    }

    @FXML
    protected void handleShowCptyAction(ActionEvent event) {

        var errorMsg = new PropertiesReader().readKey("error.message.party.not.selected");
        if (isNoneElementSelected(bankIdChoiceBox, showBankButton, errorMsg)) return;

        var id = readPartyId();
        var party = handler.readBank(id);

        if (party != null) {
            this.bankNameField.setText(party.getName());
            this.shortBankNameField.setText(party.getShortname());
        }
    }

    private long readPartyId() {
        return Long.parseLong(bankIdChoiceBox.getValue());
    }

    @FXML
    protected void handleShowFundAction(ActionEvent event) {

        String errorMsg = new PropertiesReader().readKey("error.message.fund.not.selected");
        if (isNoneElementSelected(customerIdChoiceBox, showCustomerButton, errorMsg)) return;

        var id = readFundId();
        var customer = handler.readCustomer(id);
        if (customer != null) {
            this.customerNameField.setText(customer.getName());
            this.customerShortNameField.setText(customer.getShortname());
            this.customerCashField.setText(String.valueOf(customer.getUnits()));
        }
    }

    private long readFundId() {
        return OptionalLong
                .of(Long.parseLong(customerIdChoiceBox.getValue()))
                .orElse(0);
    }

    private String readFundShortName() {
        return this.customerShortNameField.getText();
    }

    private String readFundName() {
        return this.customerNameField.getText();
    }

    private double readFundUnits() {
        return OptionalDouble
                .of(Double.valueOf(this.customerCashField.getText()))
                .orElse(0);
    }

    @FXML
    protected void handleSavePartyAction(ActionEvent event) {
        String errorMsg = new PropertiesReader().readKey("error.message.party.not.selected");
        if (isNoneElementSelected(bankIdChoiceBox, showCustomerButton, errorMsg)) return;

        handler.saveBank(new Bank(readPartyId(), readPartyName(), readPartyShortName()));
    }

    @FXML
    protected void handleSaveFundAction(ActionEvent event) {
        String errorMsg = new PropertiesReader().readKey("error.message.fund.not.selected");
        if (isNoneElementSelected(customerIdChoiceBox, showCustomerButton, errorMsg)) return;

        handler.saveCustomer(new Customer(readFundShortName(), readFundName(),  readFundUnits(), readPartyId()));
    }

    private Set<Customer> readFundsAvailableForParty() {

        return Set.of(new Customer("", "",  0.0, 1001L));//TODO: this will be implemented during migration to Hibernate 5
    }

    private String readPartyShortName() {
        return this.shortBankNameField.getText();
    }

    private String readPartyName() {
        return this.bankNameField.getText();
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
