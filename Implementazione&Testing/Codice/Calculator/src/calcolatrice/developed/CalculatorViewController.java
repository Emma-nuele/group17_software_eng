/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.developed;

import calculator.exceptions.*;


import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;

/**
 *
 * @author manu
 */
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
    private ComboBox<Character> ComboBoxVariables;

    @Override //Aggiunta
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Character> letterList = FXCollections.observableArrayList();
        ComboBoxVariables.setItems(letterList);
        
        Character startLetter = 'a';
        Character endLetter = 'z';
        for (Character letter = startLetter; letter <= endLetter; letter++) {
            
                ComboBoxVariables.getItems().add(letter);
            
        }
        
        stack = new StackCalc();
        operations = new Operations(stack);
        variables = new Variables(stack);
        commands = new Commands(stack);

        StackList.getItems().clear(); //Cancella per sicurezza

    }

    @FXML
    private void quitApp(ActionEvent event) { //Da fare su scene builder
        Platform.exit();
    }
    
    @FXML
    private void swap(ActionEvent event) {
        
            try {
                commands.swap();
                updateDisplay();
            } catch (FullStackException| InsuffElemStackException e) {
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
    }

    @FXML
    private void over(ActionEvent event) {
        
            try {
                commands.over();
                updateDisplay();
            } catch (FullStackException | InsuffElemStackException e) {
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
    }

    @FXML
    private void clear(ActionEvent event)  {
            
            try{
            commands.clear();
            StackList.getItems().clear(); // Rimuovi elem da ListView (getItems() -> prende tutti oggetti dalla lista)
            }catch(InsuffElemStackException e){
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
            complexNumInProgress = ""; // reset stringa in composizione

            if (calcArea != null) { // Aggiorna calcArea
                calcArea.setText(""); //reset
            }
        
    }

    @FXML
    private void drop(ActionEvent event) {
        
            try {
                commands.drop();
                updateDisplay();
            } catch (InsuffElemStackException e) {
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
        
    }

    @FXML
    private void dup(ActionEvent event) {
        
            try {
                commands.dup();
                updateDisplay();
            } catch (FullStackException |InsuffElemStackException e) {
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
        
    }

    // Da completare
    //cancellare (tornare di un digit indietro)
    @FXML
    private void handleInput(ActionEvent event) {
        
            Button buttonClicked = (Button) event.getSource();
            String inputText = buttonClicked.getText();

            // Concatena l'input
            complexNumInProgress += inputText;

            if (calcArea != null) {
                calcArea.setText(complexNumInProgress);
            }
        
    }

    @FXML
    private void delete(ActionEvent event){
        calcArea.setText(complexNumInProgress=complexNumInProgress.substring(0, complexNumInProgress.length() - 1));
    }
 

      @FXML
    private void sum(ActionEvent event) {
        calcArea.setText(complexNumInProgress += "+");        
        }
     
    @FXML
    private void sub(ActionEvent event) {
        calcArea.setText(complexNumInProgress += "-");
    }

    @FXML
    private void division(ActionEvent event) {
        try {
            
                operations.division();
                updateDisplay();
                
        } catch (InsuffElemStackException | FullStackException | DivisionZeroException e) {
            System.out.println(e.getMessage());
            complexNumInProgress = ""; 
        }
    }

    @FXML
    private void multiplication(ActionEvent event) {
        try {
                operations.multiplication();
                updateDisplay();
        } catch (InsuffElemStackException | FullStackException e) {
            calcArea.setText("Errore" + e.getMessage());
            complexNumInProgress = ""; 
        }
    }

    @FXML
    private void sqrt(ActionEvent event) {
            try {
                    operations.sqrt();
                    updateDisplay();
                
            } catch (InsuffElemStackException | FullStackException e) {
                calcArea.setText("Errore" + e.getMessage());
                complexNumInProgress = ""; 
            }
        
    
        }

    //non funziona
    @FXML
    private void signReversal(ActionEvent event) {
        
            try {
                
                    operations.signReversal();
                    updateDisplay();
                
            } catch (InsuffElemStackException | FullStackException e) {
                calcArea.setText("Errore" + e.getMessage());
                complexNumInProgress = ""; 
            } 
        
    }

    //prob sbaglia stampa signReverse
    @FXML
    private void push(ActionEvent event) {
        if (complexNumInProgress.length() > 1 && complexNumInProgress.charAt(0) == '+') {
            // Rimuove il primo carattere
            complexNumInProgress = complexNumInProgress.substring(1);
        }
        
        if (complexNumInProgress.equals("j")){
            try {
                stack.push(new Number(0,1));
                calcArea.setText("");
                updateDisplay();
            } catch (FullStackException e) {
            calcArea.setText("Errore: " + e.getMessage());
            complexNumInProgress = ""; 
            }
        
        }
        else if (complexNumInProgress.equals("-j")){
            try {
                stack.push(new Number(0,-1));
                calcArea.setText("");
                updateDisplay();
            } catch (FullStackException e) {
            calcArea.setText("Errore: " + e.getMessage());
            complexNumInProgress = ""; 
            }
        
        }
        
        else if(complexNumInProgress.equals("+")){
            
            try{
                
                    operations.sum();
                    calcArea.setText("");
                    updateDisplay();
                    
            }catch (InsuffElemStackException | FullStackException e) {
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }  
        }else if(complexNumInProgress.equals("-")){
            try {
                
                    operations.sub();
                    calcArea.setText("");
                    updateDisplay();
                    
            } catch (InsuffElemStackException | FullStackException e) {
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
        }else{
            try{
                String number[], num = "";
                double real, complex;
                if (complexNumInProgress.contains("+")) { //verifico se il numero complesso contiene il +
                    complexNumInProgress = complexNumInProgress.replace("j", ""); //se è presente la j la rimuovo
                    number = complexNumInProgress.split(Pattern.quote("+"));
                    real = Double.parseDouble(number[0]);
                    complex = Double.parseDouble(number[1]);
                } else if (complexNumInProgress.contains("-")) { //verifico se il numero complesso o il numero reale contiene un meno
                    if(complexNumInProgress.startsWith("-")) { //verifico se il valore inizia con - 
                        complexNumInProgress = complexNumInProgress.substring(1); //prendo solo la sottostringa senza il -
                        num = "-";
                    }
                    if(!complexNumInProgress.contains("-")) {//verifico se il numero complesso presenta un ulteriore -
                        if (complexNumInProgress.contains("j")) {//se non c'è il -, verifico se il numero è complesso (contiene j)
                            complexNumInProgress = complexNumInProgress.replace("j", "");//tolgo la j
                            real = 0;
                            complex = Double.parseDouble(num.concat(complexNumInProgress));//concateno il valore con num in modo tale che se il numero era negativo allora rimetto il - altrimenti concateno con uno spazio vuoto
                        } else {//altrimenti il numero è un numero reale negativo
                            real = Double.parseDouble(num.concat(complexNumInProgress));
                            complex = 0;
                        }
                    }else {//è un numero complesso con parte immaginaria negativa
                        complexNumInProgress = complexNumInProgress.replace("j", "");
                        number = complexNumInProgress.split("-");
                        real = Double.parseDouble(num.concat(number[0]));
                        complex = Double.parseDouble("-".concat(number[1]));
                    }
                } else if (complexNumInProgress.contains("j")) { //verifico se il numero inserito contiene j, in questo caso ho un numero complesso senza parte reale
                    complexNumInProgress = complexNumInProgress.replace("j", "");
                    real = 0;
                    complex = Double.parseDouble(complexNumInProgress);
                } else {//verifico che il valore sia un numero reale senza parte immaginaria
                    real = Double.parseDouble(complexNumInProgress);
                    complex = 0;
                }
                stack.push(new Number(real, complex));
                complexNumInProgress = "";
                calcArea.setText("");
                updateDisplay();
            }catch(Exception e){
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
    }
    }


    private void updateDisplay() { 
        complexNumInProgress = ""; 
        StackList.getItems().clear();
        for (Number item : stack.getStack()) {
            StackList.getItems().add(0, item.toString());
            
        }
    }

    @FXML
    private void stackToVar(ActionEvent event) {
        // Logica per trasferire dallo stack alla variabile
        try{
        variables.stackToVar(ComboBoxVariables.getValue());
        updateDisplay();
        handleComboBoxAction();
        calcArea.setText("");
        }catch(InsuffElemStackException| InvalidArgException  e){
            calcArea.setText("Errore: " + e.getMessage());
        }
    }

    @FXML
    private void varToStack(ActionEvent event) {
        // Logica per trasferire dalla variabile allo stack
        try{
        variables.varToStack(ComboBoxVariables.getValue());
        updateDisplay();
        handleComboBoxAction();
        calcArea.setText("");
        }catch(FullStackException| InvalidArgException |UnusedVarException e){
            calcArea.setText("Errore: " + e.getMessage());
        }

    }

    @FXML
    private void addToVar(ActionEvent event)  {
        // Logica per aggiungere al valore della variabile
        try{
        variables.addToVar(ComboBoxVariables.getValue());
        updateDisplay();    
        handleComboBoxAction();
        calcArea.setText("");
        }catch( FullStackException| InsuffElemStackException| InvalidArgException|UnusedVarException e){
            calcArea.setText("Errore: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleComboBoxAction() {
        
        VariableList.getItems().clear();
        //List<String> list = new ArrayList<>(variables.getMap().keySet());
  
        Iterator<Map.Entry<Character,Number>> iterator = variables.getMap().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Character, Number> entry = iterator.next();
            VariableList.getItems().add(0,entry.getKey() + " = " + entry.getValue());
        }
    }
    

    @FXML
    private void subToVar(ActionEvent event) {
        // Logica per sottrarre dal valore della variabile
        try{
        variables.subToVar(ComboBoxVariables.getValue());
        updateDisplay(); 
        handleComboBoxAction();
        calcArea.setText("");
        }catch(FullStackException | InsuffElemStackException | InvalidArgException|UnusedVarException e){
            calcArea.setText("Errore: " + e.getMessage());
        }        
    }
}
    
