/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.StatusFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class StatusAction implements EventHandler<ActionEvent> {

    public StatusAction() {
    }

    @Override
    public void handle(ActionEvent event) {
     if (Controller.getInstance().getStatusFrame() == null) {
            Controller.getInstance().setStatusFrame(new StatusFrame());
        }
        if (Controller.getInstance().getStatusScene() == null) {
            Controller.getInstance().setStatusScene(new Scene(Controller.getInstance().getStatusFrame(), 580, 210));
        }
        Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba|Status");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getStatusScene());
        Controller.getInstance().getStatusFrame().setStyle("-fx-background-color: #A71D36");
        Connector.getInstance().getAllStatus();
        Controller.getInstance().getStatusFrame().getStatusTableView().setItems(Connector.getInstance().getStatuss());
    }
    
}
