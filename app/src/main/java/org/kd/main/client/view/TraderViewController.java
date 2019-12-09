package org.kd.main.client.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kd.main.client.presenter.PresenterHandler;
import org.kd.main.client.view.lib.PropertiesReader;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.CorporateAccount;
import org.kd.main.common.entities.Transfer;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.stream.Collectors;

class TraderViewController {

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

    @FXML
    protected void handleBookTradeAction(ActionEvent event) {
        handler.bookTransfer();
    }

    @FXML
    protected void handleShowCptyAction(ActionEvent event) {

        var errorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        if (isNoneElementSelected(bankIdChoiceBox, showBankButton, errorMsg)) return;

        var id = readBankId();
        var bank = handler.readBank(id);
        this.bankNameField.setText(bank.getName());
        this.shortBankNameField.setText(bank.getShortname());

    }

    @FXML
    protected void handleDeleteBankAction(ActionEvent actionEvent) {
        var errorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        if (isNoneElementSelected(bankIdChoiceBox, showBankButton, errorMsg)) return;

        var id = readBankId();
        handler.deleteBank(id);
    }

    @FXML
    protected void handleDeleteCustomerAction(ActionEvent actionEvent) {
        var errorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        if (isNoneElementSelected(customerIdChoiceBox, showCustomerButton, errorMsg)) return;

        var id = readCustomerId();
        handler.deleteCustomer(id);
    }

    @FXML
    protected void handleShowCustomerAction(ActionEvent event) {

        String errorMsg = new PropertiesReader().readKey("error.message.customer.not.selected");
        if (isNoneElementSelected(customerIdChoiceBox, showCustomerButton, errorMsg)) return;

        var id = readCustomerId();
        var customer = handler.readCustomer(id);
        if (customer != null) {
            this.customerNameField.setText(customer.getName());
            this.customerShortNameField.setText(customer.getShortname());
            this.customerCashField.setText(String.valueOf(customer.getBalance()));
        }
    }
    @FXML
    protected void showDetailsPanel(ActionEvent e){

    }

    @FXML
    protected void handleSaveBankAction(ActionEvent event) {
        String errorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        if (isNoneElementSelected(bankIdChoiceBox, showCustomerButton, errorMsg)) return;

        handler.updateBank(new Bank(readBankId(), readBankName(), readBankShortName()));
    }

    @FXML
    protected void handleSaveCustomerAction(ActionEvent event) {
        String errorMsgCustomer = new PropertiesReader().readKey("error.message.customer.not.selected");
        String errorMsgBank = new PropertiesReader().readKey("error.message.bank.not.selected");
        if (isNoneElementSelected(customerIdChoiceBox, showCustomerButton, errorMsgCustomer)
                | isNoneElementSelected(bankIdChoiceBox, showCustomerButton, errorMsgBank)) return;

        handler.updateCustomer(new CorporateAccount(readCustomerShortName(), readCustomerName(), readCustomerUnits(), readBankId()));
    }

    @FXML
    protected void handleCreateBankAction(ActionEvent event) {
        String errorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        if (isNoneElementSelected(bankIdChoiceBox, showCustomerButton, errorMsg)) return;

        handler.createBank(readBankName(), readBankShortName());
    }

    @FXML
    protected void handleCreateCustomerAction(ActionEvent event) {
        String errorMsg = new PropertiesReader().readKey("error.message.customer.not.selected");
        if (isNoneElementSelected(customerIdChoiceBox, showCustomerButton, errorMsg)) return;

        handler.createCustomer(readCustomerShortName(), readCustomerName(), readCustomerUnits(), readBankId());
    }

    @FXML
    protected void stopServerActionHandler() {
        handler.stopServer();
    }

    public void loadBanks() {

        var list = FXCollections.observableArrayList(
                handler.readBanks()
                        .stream()
                        .map(Bank::getId)
                        .map(Object::toString)
                        .collect(Collectors.toList()));

        bankIdChoiceBox.setItems(list);
        bankId4TradeChoiceBox.setItems(list);
    }

    public void loadCustomers() {

        var list = FXCollections.observableArrayList(
                handler.readCustomers()
                        .stream()
                        .map(Account::getId)
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

    private long readCustomerId() {
        return OptionalLong
                .of(Long.parseLong(customerIdChoiceBox.getValue()))
                .orElse(0);
    }

    private String readCustomerShortName() {
        return this.customerShortNameField.getText();
    }

    private String readCustomerName() {
        return this.customerNameField.getText();
    }

    private double readCustomerUnits() {
        return OptionalDouble
                .of(Double.valueOf(this.customerCashField.getText()))
                .orElse(0);
    }

    private Long readBankId() {
        var bankId = bankIdChoiceBox.getValue();
        return Long.parseLong(bankId);
    }

    private String readBankShortName() {
        return this.shortBankNameField.getText();
    }

    private String readBankName() {
        return this.bankNameField.getText();
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
        var formErrorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, formErrorMsg,
                errorMessage);
        return true;
    }

    public static void setHandler(PresenterHandler handler) {
        TraderViewController.handler = handler;
    }

}
