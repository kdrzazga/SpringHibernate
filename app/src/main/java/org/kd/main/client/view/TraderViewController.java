package org.kd.main.client.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

import static org.kd.main.client.view.AlertHelper.showErrorAlert;
import static org.kd.main.client.view.AlertHelper.showInfoAlert;

public class TraderViewController {

    private static PresenterHandler handler;

    @FXML
    private Tab devTab;

    @FXML
    private TableView<Transfer> transferTable;

    @FXML
    private ChoiceBox<String> bankIdChoiceBox;

    @FXML
    private ChoiceBox<String> accountIdChoiceBoxAccount;

    @FXML
    private TextField bankNameField;

    @FXML
    private TextField shortBankNameField;

    @FXML
    private ChoiceBox<String> bankIdChoiceBoxTransfer;

    @FXML
    private TextField accountsField;

    @FXML
    private Button showBankButton;

    @FXML
    private TextField accountNameField;

    @FXML
    private TextField accountShortNameField;

    @FXML
    private TextField accountBalanceField;

    @FXML
    private Button showAccountButton;

    @FXML
    private TextField messageTextBox;

    @FXML
    public void initialize() {
        devTab.setDisable(new PropertiesReader().readKey("dev-mode").equals("false"));
    }

    @FXML
    protected void handleBookTradeAction(ActionEvent event) {
        var bookConfirmMsg = new PropertiesReader().readKey("error.message.transfer.booked");
        var bookingErrorMgs = new PropertiesReader().readKey("error.message.transfer.not.booked");

        if (handler.bookTransfer()) {
            showInfoAlert(bookConfirmMsg);
        } else {
            showErrorAlert(bookingErrorMgs);
        }
        loadTransfers();
    }

    @FXML
    protected void handleShowBankAction(ActionEvent event) {

        var errorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        if (isNoneElementSelected(bankIdChoiceBox, errorMsg)) return;

        var id = readBankId();
        var bank = handler.readBank(id);
        if (bank.isPresent()) {
            this.bankNameField.setText(bank.get().getName());
            this.shortBankNameField.setText(bank.get().getShortname());
        }
    }

    @FXML
    protected void handleDeleteBankAction(ActionEvent actionEvent) {
        var selectionErrorMsg = new PropertiesReader().readKey("message.error.bank.not.selected");
        var deleteErrorMsg = new PropertiesReader().readKey("message.error.bank.not.deleted");
        var deleteConfirmMsg = new PropertiesReader().readKey("message.confirm.bank.deleted");
        if (isNoneElementSelected(bankIdChoiceBox, selectionErrorMsg)) return;

        var id = readBankId();
        if (handler.deleteBank(id))
            showInfoAlert(deleteConfirmMsg);
        else
            showErrorAlert(deleteErrorMsg);
    }

    @FXML
    protected void handleDeleteAccountAction(ActionEvent actionEvent) {
        var selectionErrorMsg = new PropertiesReader().readKey("message.error.account.not.selected");
        var deleteErrorMsg = new PropertiesReader().readKey("message.error.account.not.deleted");
        var deleteConfirmMsg = new PropertiesReader().readKey("message.confirm.account.deleted");
        if (isNoneElementSelected(accountIdChoiceBoxAccount, selectionErrorMsg)) return;

        var id = readAccountId();
        if (handler.deleteAccount(id))
            showInfoAlert(deleteConfirmMsg);
        else
            showErrorAlert(deleteErrorMsg);
    }

    @FXML
    protected void handleShowAccountAction(ActionEvent event) {

        var selectionErrorMsg = new PropertiesReader().readKey("message.error.account.not.selected");
        if (isNoneElementSelected(accountIdChoiceBoxAccount, selectionErrorMsg)) return;

        var id = readAccountId();

        messageTextBox.setText(id.toString());

        var account = handler.readAccount(id);
        if (account.isPresent()) {
            this.accountNameField.setText(account.get().getName());
            this.accountShortNameField.setText(account.get().getShortname());
            this.accountBalanceField.setText(String.valueOf(account.get().getBalance()));
        }
    }

    @FXML
    protected void showDetailsPanel(ActionEvent e) {
        String errorMsg = new PropertiesReader().readKey("error.message.account.not.selected");
        if (isNoneElementSelected(accountIdChoiceBoxAccount, errorMsg)) return;

        Trader.getInstance().activateAccountDetailsScreen();
    }

    @FXML
    protected void handleSaveBankAction(ActionEvent event) {
        var errorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        var saveErrorMsg = new PropertiesReader().readKey("message.error.bank.not.saved");
        var saveConfirmMsg = new PropertiesReader().readKey("message.confirm.bank.saved");
        if (isNoneElementSelected(bankIdChoiceBox, errorMsg)) return;

        if (handler.updateBank(new Bank(readBankId(), readBankName(), readBankShortName())))
            showInfoAlert(saveConfirmMsg);
        else showErrorAlert(saveErrorMsg);
    }

    @FXML
    protected void handleSaveAccountAction(ActionEvent event) {
        String errorMsgCustomer = new PropertiesReader().readKey("error.message.customer.not.selected");
        String errorMsgBank = new PropertiesReader().readKey("error.message.bank.not.selected");
        var saveErrorMsg = new PropertiesReader().readKey("message.error.account.not.saved");
        var saveConfirmMsg = new PropertiesReader().readKey("message.confirm.account.saved");
        if (isNoneElementSelected(accountIdChoiceBoxAccount, errorMsgCustomer)
                | isNoneElementSelected(bankIdChoiceBox, errorMsgBank)) return;

        var account = new CorporateAccount(readAccountShortName(), readAccountName(), readAccountUnits(), readBankId());

        if (handler.updateAccount(account))
            showInfoAlert(saveConfirmMsg);
        else showErrorAlert(saveErrorMsg);
    }

    @FXML
    protected void handleCreateBankAction(ActionEvent event) {
        String errorMsg = new PropertiesReader().readKey("error.message.bank.not.selected");
        var saveErrorMsg = new PropertiesReader().readKey("message.error.bank.not.saved");
        var saveConfirmMsg = new PropertiesReader().readKey("message.confirm.bank.saved");

        if (isNoneElementSelected(bankIdChoiceBox, errorMsg)) return;

        if (handler.createBank(readBankName(), readBankShortName()))
            showInfoAlert(saveConfirmMsg);
        else showErrorAlert(saveErrorMsg);
    }

    @FXML
    protected void handleCreateAccountAction(ActionEvent event) {
        var saveErrorMsg = new PropertiesReader().readKey("message.error.account.not.saved");
        var saveConfirmMsg = new PropertiesReader().readKey("message.confirm.account.saved");

        if (handler.createAccount(readAccountShortName(), readAccountName(), readAccountUnits(), readBankId()))
            showInfoAlert(saveConfirmMsg);
        else showErrorAlert(saveErrorMsg);
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
        bankIdChoiceBoxTransfer.setItems(list);
    }

    public void loadAccounts() {

        var list = FXCollections.observableArrayList(
                handler.readAccounts()
                        .stream()
                        .map(Account::getId)
                        .map(Object::toString)
                        .collect(Collectors.toList()));

        accountIdChoiceBoxAccount.setItems(list);
    }

    public void loadTransfers() {
        var trades = FXCollections
                .observableArrayList(
                        handler.readTransfers());

        var idColumn = new TableColumn<Transfer, String>("Id");
        var quantityColumn = new TableColumn<Transfer, String>("Units");
        var srcAccountColumn = new TableColumn<Transfer, String>("From");
        var destAccountColumn = new TableColumn<Transfer, String>("To");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("units"));
        srcAccountColumn.setCellValueFactory(new PropertyValueFactory<>("srcAccount"));
        destAccountColumn.setCellValueFactory(new PropertyValueFactory<>("destAccount"));

        transferTable.getItems().clear();
        transferTable.getColumns().clear();

        transferTable.setItems(trades);
        transferTable.getColumns().addAll(idColumn, quantityColumn, srcAccountColumn, destAccountColumn);
    }

    private Long readAccountId() {
        /*TODO*/
        var text = Optional.ofNullable(accountIdChoiceBoxAccount.getValue())
                .orElse("2001");

        return OptionalLong
                .of(Long.parseLong(text))
                .orElse(2001L);
    }

    private String readAccountShortName() {
        return Optional.ofNullable(this.accountShortNameField.getText())
                .orElse("");
    }

    private String readAccountName() {
        return Optional.ofNullable(this.accountNameField.getText())
                .orElse("");
    }

    private double readAccountUnits() {
        return OptionalDouble
                .of(Double.valueOf(this.accountBalanceField.getText()))
                .orElse(0);
    }

    private Long readBankId() {

        var bankId = bankIdChoiceBox.getValue();
        return bankId == null ? 2001L
                : Long.parseLong(bankId);
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

    private boolean isNoneElementSelected(ChoiceBox<String> choiceBox, String errorMessage) {
        if (isItemSelected(choiceBox)) return false;

        var selectionErrorMsg = new PropertiesReader().readKey("message.error.id.not.selected");
        showErrorAlert(selectionErrorMsg);
        return true;
    }

    public static void setHandler(PresenterHandler handler) {
        TraderViewController.handler = handler;
    }

    public void handleAccountSelection(MouseEvent mouseEvent) {
        final var accountId = Optional.of(this.accountIdChoiceBoxAccount)
                .map(ChoiceBox::getValue);

        if (accountId.isPresent()) {
            this.messageTextBox.setText(accountId.get());
            AccountDetailsPanelController.setAccountId(Long.valueOf(accountId.get()));
        }
    }
}
