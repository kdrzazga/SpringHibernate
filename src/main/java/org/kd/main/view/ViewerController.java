package org.kd.main.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.kd.main.model.DataModelManager;

import java.util.Optional;

public class ViewerController {

    @FXML
    private ChoiceBox<String> cptyIdChoiceBox;

    @FXML
    private ChoiceBox<String> fundIdChoiceBox;

    @FXML
    private TextField cptyNameField;

    @FXML
    private TextField shortCptyNameField;

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

    private static final String FORM_ERROR = "Form Error!";

    @FXML
    protected void handleShowCptyAction(ActionEvent event) {

        if (!isItemSelected(cptyIdChoiceBox)) {
            var owner = showCptyButton.getScene().getWindow();
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, FORM_ERROR,
                    "Please select Party Id.");
            return;
        }

        long id = Long.parseLong(cptyIdChoiceBox.getValue());
        var cpty = DataModelManager.getPartyDao().get(id);
        if (cpty != null) {
            this.cptyNameField.setText(cpty.getName());
            this.shortCptyNameField.setText(cpty.getShortName());
        }
    }

    @FXML
    protected void handleShowFundAction(ActionEvent event) {
        if (!isItemSelected(fundIdChoiceBox)) {
            var owner = showFundButton.getScene().getWindow();
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, FORM_ERROR,
                    "Please select Fund Id.");
            return;
        }

        Long id = Long.parseLong(fundIdChoiceBox.getValue());
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
}
