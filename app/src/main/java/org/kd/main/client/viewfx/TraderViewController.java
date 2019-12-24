package org.kd.main.client.viewfx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.kd.main.client.presenter.PresenterHandler;
import org.kd.main.client.viewfx.lib.PropertiesReader;
import org.kd.main.client.viewfx.lib.NumberTextField;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.CorporateAccount;
import org.kd.main.common.entities.Transfer;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.kd.main.client.viewfx.AlertHelper.showErrorAlert;
import static org.kd.main.client.viewfx.AlertHelper.showInfoAlert;

public class TraderViewController {

    private static PresenterHandler handler;

    @FXML
    private Tab devTab;

    @FXML
    private TableView<Transfer> transferTable;

    @FXML
    private ChoiceBox<Long> bankIdChoiceBox;

    @FXML
    private ChoiceBox<Long> accountIdChoiceBoxAccount;

    @FXML
    private TextField bankNameField;

    @FXML
    private TextField shortBankNameField;

    @FXML
    private ChoiceBox<Long> srcAccountIdChoiceBoxTransfer;

    @FXML
    private ChoiceBox<Long> destAccountChoiceBoxTransfer;

    @FXML
    private NumberTextField amountTextBox;

    @FXML
    private TextArea accountsField;

    @FXML
    private Button showBankButton;

    @FXML
    private TextField accountNameField;

    @FXML
    private TextField accountShortNameField;

    @FXML
    private NumberTextField accountBalanceField;

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
        var bookConfirmMsg = new PropertiesReader().readKey("message.confirm.transfer.booked");
        var bookingErrorMgs = new PropertiesReader().readKey("error.message.transfer.not.booked");

        var errorMsg = new PropertiesReader().readKey("error.message.account.not.selected");
        if (isNoneElementSelected(srcAccountIdChoiceBoxTransfer, errorMsg)) return;
        if (isNoneElementSelected(destAccountChoiceBoxTransfer, errorMsg)) return;

        var srcAccount = readAccountId(srcAccountIdChoiceBoxTransfer);
        var destAccount = readAccountId(destAccountChoiceBoxTransfer);
        var amount = amountTextBox.getDouble();
        if (handler.bookTransfer(srcAccount, destAccount, amount)) {
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
            loadAssociatedAccounts();
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

        var id = readAccountId(accountIdChoiceBoxAccount);
        if (handler.deleteAccount(id))
            showInfoAlert(deleteConfirmMsg);
        else
            showErrorAlert(deleteErrorMsg);
    }

    @FXML
    protected void handleShowAccountAction(ActionEvent event) {

        var selectionErrorMsg = new PropertiesReader().readKey("message.error.account.not.selected");
        if (isNoneElementSelected(accountIdChoiceBoxAccount, selectionErrorMsg)) return;

        var id = readAccountId(accountIdChoiceBoxAccount);

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

        var account = new CorporateAccount(readAccountShortName(), readAccountName()
                , accountBalanceField.getDouble(), readBankId());

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

        if (handler.createAccount(readAccountShortName(), readAccountName()
                , accountBalanceField.getDouble(), readBankId()))
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
                        .collect(Collectors.toList()));

        bankIdChoiceBox.setItems(list);

    }

    public void loadAccounts() {

        var list = FXCollections.observableArrayList(
                handler.readAccounts()
                        .stream()
                        .map(Account::getId)
                        .collect(Collectors.toList()));

        accountIdChoiceBoxAccount.setItems(list);
        srcAccountIdChoiceBoxTransfer.setItems(list);
        destAccountChoiceBoxTransfer.setItems(list);
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

    public void loadAssociatedAccounts() {
        var associatedAccounts = FXCollections
                .observableArrayList(
                        handler.readAssociatedAccounts(readBankId())
                );

        var accounts = new StringBuilder(associatedAccounts.size());

        associatedAccounts.stream().map(Account::getId).collect(Collectors.toList())
                .forEach(account -> accounts.append(account.toString().concat(" ")));

        accountsField.setText(accounts.toString());
    }

    private Long readAccountId(ChoiceBox<Long> choiceBox) {
        /*TODO*/
        return Optional.ofNullable(choiceBox.getValue())
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

    private Long readBankId() {

        return Optional.ofNullable(bankIdChoiceBox.getValue())
                .orElse(2001L);
    }

    private String readBankShortName() {
        return this.shortBankNameField.getText();
    }

    private String readBankName() {
        return this.bankNameField.getText();
    }

    private boolean isItemSelected(ChoiceBox<Long> choiceBox) {

        return Optional.ofNullable(choiceBox)
                .map(ChoiceBox::getValue)
                .isPresent();
    }

    private boolean isNoneElementSelected(ChoiceBox<Long> choiceBox, String errorMessage) {
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
            this.messageTextBox.setText(accountId.get().toString());
            AccountDetailsPanelController.setAccountId(accountId.get());
        }
    }
}
