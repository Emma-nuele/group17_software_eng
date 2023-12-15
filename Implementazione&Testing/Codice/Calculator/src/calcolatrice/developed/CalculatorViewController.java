/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.developed;

import calculator.exceptions.*;
import static java.lang.Character.isDigit;


import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
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

    private String complexNumInProgress = "";

    @FXML
    private ListView<String> StackList;
    @FXML
    private TextArea calcArea;
    @FXML
    private ListView<String> VariableList; 
    @FXML
    private Button DropButton, DupButton, SwapButton, OverButton, ClearButton, EnterButton;
    @FXML
    private Button Button7, Button8, Button9, Button4, Button5, Button6, Button1, Button2, Button3, Button0,
            ButtonPoint;
    @FXML
    private Button ButtonPlus, ButtonLess, ButtonMultiplier, ButtonSlash, RadButton, PlusLessButton, ArrowLeftButton;
    @FXML
    private ComboBox<Character> ComboBoxVariables;

    @Override 
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

        StackList.getItems().clear(); 

    }

    @FXML
    private void quitApp(ActionEvent event) { 
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
            StackList.getItems().clear(); 
            }catch(InsuffElemStackException e){
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
            complexNumInProgress = ""; 

            if (calcArea != null) { 
                calcArea.setText(""); 
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

    @FXML
    private void handleInput(ActionEvent event) {
        
            Button buttonClicked = (Button) event.getSource();
            String inputText = buttonClicked.getText();

            complexNumInProgress += inputText;

            if (calcArea != null) {
                calcArea.setText(complexNumInProgress);
            }
        
    }

    @FXML
    private void delete(ActionEvent event){
        
        if(!complexNumInProgress.equals(""))
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
            updateDisplay();
            calcArea.setText("Errore: " + e.getMessage());
            complexNumInProgress = ""; 
        }
    }

    @FXML
    private void multiplication(ActionEvent event) {
        try {
                operations.multiplication();
                updateDisplay();
        } catch (InsuffElemStackException | FullStackException e) {
            calcArea.setText("Errore: " + e.getMessage());
            complexNumInProgress = ""; 
        }
    }

    @FXML
    private void sqrt(ActionEvent event) {
            try {
                    operations.sqrt();
                    updateDisplay();
                
            } catch (InsuffElemStackException | FullStackException e) {
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            }
        
    
        }

    @FXML
    private void signReversal(ActionEvent event) {
        
            try {
                
                    operations.signReversal();
                    updateDisplay();
                
            } catch (InsuffElemStackException | FullStackException e) {
                calcArea.setText("Errore: " + e.getMessage());
                complexNumInProgress = ""; 
            } 
        
    }

    @FXML
    private void push(ActionEvent event) {
        if (  complexNumInProgress.charAt(0) == '+' &&  complexNumInProgress.length() > 1 && isDigit(complexNumInProgress.charAt(1))) {
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
                if (complexNumInProgress.contains("+")) { 
                    complexNumInProgress = complexNumInProgress.replace("j", ""); 
                    number = complexNumInProgress.split(Pattern.quote("+"));
                    real = Double.parseDouble(number[0]);
                    complex = Double.parseDouble(number[1]);
                } else if (complexNumInProgress.contains("-")) { 
                    if(complexNumInProgress.startsWith("-")) {
                        complexNumInProgress = complexNumInProgress.substring(1); 
                        num = "-";
                    }
                    if(!complexNumInProgress.contains("-")) {
                        if (complexNumInProgress.contains("j")) {
                            complexNumInProgress = complexNumInProgress.replace("j", "");
                            real = 0;
                            complex = Double.parseDouble(num.concat(complexNumInProgress));
                        } else {
                            real = Double.parseDouble(num.concat(complexNumInProgress));
                            complex = 0;
                        }
                    }else {
                        complexNumInProgress = complexNumInProgress.replace("j", "");
                        number = complexNumInProgress.split("-");
                        real = Double.parseDouble(num.concat(number[0]));
                        complex = Double.parseDouble("-".concat(number[1]));
                    }
                } else if (complexNumInProgress.contains("j")) { 
                    complexNumInProgress = complexNumInProgress.replace("j", "");
                    real = 0;
                    complex = Double.parseDouble(complexNumInProgress);
                } else {
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
  
        Iterator<Map.Entry<Character,Number>> iterator = variables.getMap().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Character, Number> entry = iterator.next();
            VariableList.getItems().add(0,entry.getKey() + " = " + entry.getValue());
        }
    }
    

    @FXML
    private void subToVar(ActionEvent event) {
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
    
