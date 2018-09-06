/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.RokFrame;
import frames.SmerFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class RokAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getRokFrame() == null) {
            Controller.getInstance().setRokFrame(new RokFrame());
        }
        if (Controller.getInstance().getRokScene() == null) {
            Controller.getInstance().setRokScene(new Scene(Controller.getInstance().getRokFrame(), 510, 260));
        }
        Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba|Rokovi");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getRokScene());
        Controller.getInstance().getRokFrame().setStyle("-fx-background-color: #A71D36");
        Connector.getInstance().getAllRok();
        Controller.getInstance().getRokFrame().getRokTableView().setItems(Connector.getInstance().getRoks());
    }

}
