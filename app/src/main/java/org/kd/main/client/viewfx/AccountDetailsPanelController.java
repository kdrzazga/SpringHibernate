package org.kd.main.client.viewfx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kd.main.client.presenter.PresenterHandler;
import org.kd.main.common.entities.Card;
import org.kd.main.common.entities.Credit;
import org.kd.main.common.entities.CreditCard;

public class AccountDetailsPanelController {

    private static PresenterHandler handler;
    private static Long accountId = 2001L;

    @FXML
    private Label customerIdLabel;

    @FXML
    private TableView debitCardTable;

    @FXML
    private TableView creditCardTable;

    @FXML
    private TableView creditTable;

    public static void setHandler(PresenterHandler handler) {
        AccountDetailsPanelController.handler = handler;
    }

    public static void setAccountId(Long accountId) {
        AccountDetailsPanelController.accountId = accountId;
    }

    @FXML
    public void initialize() {
        customerIdLabel.setText(accountId.toString());
    }

    @FXML
    public void returnToMainScreen() {
        Trader.getInstance().activateMainScreen();
    }

    public void loadDebitCards(Long id) {
        var cards = FXCollections
                .observableArrayList(
                        handler.readDebitCards(id));

        var idColumn = new TableColumn<Card, String>("Id");
        var balanceColumn = new TableColumn<Card, String>("Balance");
        var bankColumn = new TableColumn<Card, String>("Bank");
        var currencyColumn = new TableColumn<Card, String>("Currency");
        var activeColumn = new TableColumn<Card, String>("Active");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        bankColumn.setCellValueFactory(new PropertyValueFactory<>("bank"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));

        debitCardTable.getColumns().clear();
        debitCardTable.setItems(cards);
        debitCardTable.getColumns().addAll(idColumn, balanceColumn, currencyColumn, bankColumn, activeColumn);
    }

    public void loadCreditCards(Long id) {
        var cards = FXCollections
                .observableArrayList(
                        handler.readCreditCards(id));

        var idColumn = new TableColumn<Card, String>("Id");
        var balanceColumn = new TableColumn<Card, String>("Balance");
        var bankColumn = new TableColumn<Card, String>("Bank");
        var currencyColumn = new TableColumn<Card, String>("Currency");
        var activeColumn = new TableColumn<Card, String>("Active");
        var limitColumn = new TableColumn<CreditCard, String>("Limit");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        bankColumn.setCellValueFactory(new PropertyValueFactory<>("bank"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        limitColumn.setCellValueFactory(new PropertyValueFactory<>("credit_limit"));

        creditCardTable.getColumns().clear();
        creditCardTable.setItems(cards);
        creditCardTable.getColumns().addAll(idColumn, balanceColumn, currencyColumn, limitColumn, bankColumn, activeColumn);
    }

    public void loadCredits(Long id) {
        var credits = FXCollections
                .observableArrayList(
                        handler.readCredits(id));

        var idColumn = new TableColumn<Credit, String>("Id");
        var amountColumn = new TableColumn<Credit, String>("Amount");
        var bankColumn = new TableColumn<Credit, String>("Bank");
        var currencyColumn = new TableColumn<Credit, String>("Currency");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        bankColumn.setCellValueFactory(new PropertyValueFactory<>("bank"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));

        creditTable.getColumns().clear();
        creditTable.setItems(credits);
        creditTable.getColumns().addAll(idColumn, amountColumn, currencyColumn, bankColumn);

    }
}
