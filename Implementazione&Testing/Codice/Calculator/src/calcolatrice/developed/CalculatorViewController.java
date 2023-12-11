package calculator.developed;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class CalculatorViewController {

    @FXML
    private TextArea calcArea;
    @FXML
    private Button DropButton, DupButton, SwapButton, OverButton, ClearButton, EnterButton;
    @FXML
    private Button Button7, Button8, Button9, Button4, Button5, Button6, Button1, Button2, Button3, Button0, ButtonPoint;
    @FXML
    private Button ButtonPlus, ButtonLess, ButtonMultiplier, ButtonSlash, RadButton, PlusLessButton, ArrowLeftButton;
    @FXML
    private ComboBox<String> comboBoxVariables;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // Logica di inizializzazione qui
    }

    @FXML
    private void quitApp(ActionEvent event) {
         Platform.exit();
    }

    @FXML
    private void swap(ActionEvent event) {
        // Logica per SWAP
    }
    @FXML
    private void over (ActionEvent event){
        //Logica per over
    }
    @FXML
     private void clear (ActionEvent event){
     }
    @FXML
     private void drop (ActionEvent event){
        //logica per drop
     }
    @FXML
     private void dup (ActionEvent event){
        //logica per dup
     }

    @FXML
    private void handleInput(ActionEvent event) {
        // Logica per gestire l'input generico (pulsanti numerici, ecc.)
    }

    @FXML
    private void sum(ActionEvent event) {
        // Logica per la somma
    }

    @FXML
    private void sub(ActionEvent event) {
        // Logica per la sottrazione
    }
    @FXML
    private void division(ActionEvent event) {
        // Logica per la divisione
    }

      @FXML
    private void multiplication(ActionEvent event) {
        // Logica per la moltiplicazione
    }

    @FXML
    private void sqrt(ActionEvent event) {
        // Logica per il calcolo della radice quadrata
    }

    @FXML
    private void signReversal(ActionEvent event) {
        // Logica per l'inversione del segno
    }

    @FXML
    private void push(ActionEvent event) {
        // Logica per PUSH
    }

    @FXML
    private void stackToVar(ActionEvent event) {
        // Logica per trasferire dallo stack alla variabile
    }

    @FXML
    private void varToStack(ActionEvent event) {
        // Logica per trasferire dalla variabile allo stack
    }

    @FXML
    private void addToVar(ActionEvent event) {
        // Logica per aggiungere al valore della variabile
    }

    @FXML
    private void subToVar(ActionEvent event) {
        // Logica per sottrarre dal valore della variabile
 
    }
    @FXML
    private void handleComboBoxAction(ActionEvent event) {
        // Logica per gestire gli eventi della ComboBox
        // Puoi ottenere l'elemento selezionato dalla ComboBox usando comboBoxVariables.getValue()
    }

}
