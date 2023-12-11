/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package calcolatrice.developed;

import calcolatrice.exceptions.FullStackException;
import calcolatrice.exceptions.InsuffElemStackException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Calcolatrice extends Application {
    
 @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CalculatorView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    /*  
        StackCalc stack = new StackCalc();
        try{
            //stack.pop();
            //System.out.println(stack.top());
            Operations op = new Operations(stack);
            //op.signReversal();
            stack.push( new Number(5,-9));
            //op.sum();
            //System.out.println(stack.top());
            stack.push( new Number(5,3));
            stack.push( new Number(150,7));
            System.out.println(stack.top());
            stack.push( new Number(-3,3));
            stack.push( new Number(71,(float) 13555555.45464841313578992));
            
        }catch(InsuffElemStackException | FullStackException e){
            System.out.println(e.getMessage());
        }

        System.out.println(stack.toString());
        */
        
        launch(args);
    }
    
}
