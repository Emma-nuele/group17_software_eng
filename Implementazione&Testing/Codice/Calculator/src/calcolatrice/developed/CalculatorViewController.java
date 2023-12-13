package calculator.developed;

import calculator.exceptions.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;

public class CalculatorViewController implements Initializable {

    private StackCalc stack;
    private Operations operations;
    private Variables variables;
    private Commands commands;

    private String complexNumInProgress = ""; // variabile tiene traccia num complesso utente compone

    @FXML
    private ListView<String> StackList;
    @FXML
    private TextArea calcArea;
    @FXML
    private ListView<String> VariableList; //Aggiunta - (fxid già impostato)
    @FXML
    private Button DropButton, DupButton, SwapButton, OverButton, ClearButton, EnterButton;
    @FXML
    private Button Button7, Button8, Button9, Button4, Button5, Button6, Button1, Button2, Button3, Button0,
            ButtonPoint;
    @FXML
    private Button ButtonPlus, ButtonLess, ButtonMultiplier, ButtonSlash, RadButton, PlusLessButton, ArrowLeftButton;
    @FXML
    private ComboBox<String> comboBoxVariables;

    @Override //Aggiunta
    public void initialize(URL url, ResourceBundle rb) {

        stack = new StackCalc();
        operations = new Operations(stack);
        variables = new Variables(stack);
        commands = new Commands(stack);

        StackList.getItems().clear(); //Cancella per sicurezza

        calcArea.setText("Nessun elemento nello stack.");
    }

    @FXML
    private void quitApp(ActionEvent event) { //Da fare su scene builder
        Platform.exit();
    }

    @FXML
    private void swap(ActionEvent event) {
        if (stack != null) {
            try {
                commands.swap();
                updateDisplay();
            } catch (InsuffElemStackException e) {
                System.out.println("Errore: " + e.getMessage());
            } catch (FullStackException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        } else {
            System.out.println("Lo stack non è stato inizializzato.");
        }
    }

    @FXML
    private void over(ActionEvent event) {
        if (stack != null) {
            try {
                commands.over();
                updateDisplay();
            } catch (InsuffElemStackException e) {
                System.out.println("Errore: " + e.getMessage());
            } catch (FullStackException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        } else {
            System.out.println("Lo stack non è stato inizializzato.");
        }
    }

    @FXML
    private void clear(ActionEvent event) throws InsuffElemStackException {
        if (stack != null) {

            commands.clear();
            StackList.getItems().clear(); // Rimuovi elem da ListView (getItems() -> prende tutti oggetti dalla lista)

            complexNumInProgress = ""; // reset stringa in composizione

            if (calcArea != null) { // Aggiorna calcArea
                calcArea.setText(""); //reset
            }
        } else {
            System.out.println("Lo stack non è stato inizializzato.");
        }
    }

    @FXML
    private void drop(ActionEvent event) {
        if (stack != null) {
            try {
                commands.drop();
                updateDisplay();
            } catch (InsuffElemStackException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Lo stack non è stato inizializzato.");
        }
    }

    @FXML
    private void dup(ActionEvent event) {
        if (stack != null) {
            try {
                commands.dup();
                updateDisplay();
            } catch (FullStackException e) {

                System.out.println("Errore: " + e.getMessage());
            } catch (InsuffElemStackException e) {

                System.out.println("Errore: " + e.getMessage());
            }
        } else {
            System.out.println("Lo stack non è stato inizializzato.");
        }
    }

    // Da completare
    //cancellare (tornare di un digit indietro)
    @FXML
    private void handleInput(ActionEvent event) {
        if (stack != null) {
            Button buttonClicked = (Button) event.getSource();
            String inputText = buttonClicked.getText();

            // Concatena l'input
            complexNumInProgress += inputText;

            if (calcArea != null) {
                calcArea.setText(complexNumInProgress);
            }
        } else {
            System.out.println("Lo stack non è stato inizializzato.");
        }
    }

 

      @FXML
    private void sum(ActionEvent event) {
        try {
            if (stack != null && operations != null) { //Controllo su null 
                operations.sum();
                StackList.getItems().clear(); 
                for (Number item : stack.getStack()) {
                    StackList.getItems().add(0, item.toString()); //Aggiunta - inserimento visuale in top position (VoperazioneBinaria)
                }
                if (calcArea != null && stack.leastOne()) {
                    calcArea.setText(stack.top().toString());
                } else {
                    calcArea.setText("Lo stack è vuoto.");
                }
            } else {
                System.out.println("Lo stack o le operations non sono stati inizializzati.");
            }
        } catch (InsuffElemStackException | FullStackException e) {
            System.out.println(e.getMessage());
        }
    }
     
    @FXML
    private void sub(ActionEvent event) {
        try {
            if (stack != null && operations != null) {
                operations.sub();
                StackList.getItems().clear(); // Pulisce gli elementi correnti
                for (Number item : stack.getStack()) {
                    StackList.getItems().add(0, item.toString());
                }
                if (calcArea != null && stack.leastOne()) {
                    calcArea.setText(stack.top().toString());
                } else {
                    calcArea.setText("Lo stack è vuoto.");
                }
            } else {
                System.out.println("Lo stack o le operations non sono stati inizializzati.");
            }
        } catch (InsuffElemStackException | FullStackException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void division(ActionEvent event) {
        try {
            if (stack != null && operations != null) {
                operations.division();
                StackList.getItems().clear(); // Pulisce gli elementi correnti
                for (Number item : stack.getStack()) {
                    StackList.getItems().add(0, item.toString());
                }
                if (calcArea != null && stack.leastOne()) {
                    calcArea.setText(stack.top().toString());
                } else {
                    calcArea.setText("Lo stack è vuoto.");
                }
            } else {
                System.out.println("Lo stack o le operations non sono stati inizializzati.");
            }
        } catch (InsuffElemStackException | FullStackException | DivisionZeroException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void multiplication(ActionEvent event) {
        try {
            if (stack != null && operations != null) {
                operations.multiplication();
                StackList.getItems().clear(); // Pulisce gli elementi correnti
                for (Number item : stack.getStack()) {
                    StackList.getItems().add(0, item.toString());
                }
                if (calcArea != null && stack.leastOne()) {
                    calcArea.setText(stack.top().toString());
                } else {
                    calcArea.setText("Lo stack è vuoto.");
                }
            } else {
                System.out.println("Lo stack o le operations non sono stati inizializzati.");
            }
        } catch (InsuffElemStackException | FullStackException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void sqrt(ActionEvent event) {
        if (stack != null) {
            try {
                if (stack.leastOne()) {
                    operations.sqrt();
                    updateDisplay();
                } else {
                    System.out.println("Impossibile eseguire il comando SQRT, non ci sono elementi nello stack.");
                }
            } catch (InsuffElemStackException e) {
                System.out.println(e.getMessage());
            } catch (FullStackException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Lo stack non è stato inizializzato.");
        }
    }

    //non funziona
    @FXML
    private void signReversal(ActionEvent event) {
        if (stack != null) {
            try {
                if (stack.leastOne()) {
                    operations.signReversal();
                    updateDisplay();
                } else {
                    System.out
                            .println("Impossibile eseguire l'inversione del segno, non ci sono elementi nello stack.");
                }
            } catch (InsuffElemStackException e) {
                System.out.println(e.getMessage());
            } catch (FullStackException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Lo stack non è stato inizializzato.");
        }
    }

    //prob sbaglia stampa signReverse
    @FXML
    private void push(ActionEvent event) {
        // TODO
        if (!complexNumInProgress.isEmpty()) {
            try {
                /// Split
                String[] parts = complexNumInProgress.split("\\+\\-|j"); //aggiunta \\-
                double realPart = 0;
                double imaginaryPart = 0;

                if (parts.length > 0 && !parts[0].isEmpty()) {
                    String realPartStr = parts[0];
                    if (realPartStr.contains("+")) {
                        realPart = Double.parseDouble(realPartStr.split("\\+")[0]);
                    } else if (realPartStr.contains("-")) {
                        realPart = Double.parseDouble(realPartStr.split("-")[0]);
                    } else {
                        realPart = Double.parseDouble(realPartStr);
                    }
                }

                if (parts.length > 1 && !parts[1].isEmpty()) {
                    imaginaryPart = Double.parseDouble(parts[1]);
                }

                // Nuovo
                Number number = new Number(realPart, imaginaryPart);
                stack.push(number);

                // Reset
                complexNumInProgress = "";
                calcArea.setText("");
                updateDisplay();

            } catch (NumberFormatException e) {
                calcArea.setText("Formato numero non valido: " + e.getMessage());
            } catch (FullStackException e) {
                calcArea.setText("Stack pieno: " + e.getMessage());
            }
        }
    }

    private void updateDisplay() { 
        StackList.getItems().clear();
        for (Number item : stack.getStack()) {
            StackList.getItems().add(0, item.toString());
        }
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

    }

}
