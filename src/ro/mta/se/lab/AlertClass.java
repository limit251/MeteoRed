package ro.mta.se.lab;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertClass {

        Alert alert;
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

        public AlertClass(int val, String title, String message){
            if(val == 1){
                alert =  new Alert(Alert.AlertType.ERROR);
            }
            else if (val == 2){
                alert = new Alert(Alert.AlertType.INFORMATION);
            }
            alert.setTitle(title);
            alert.setContentText(message);
            //alert.getDialogPane().getButtonTypes().add(type);
        }
        public void showAlert(){
            alert.show();
        }


}
