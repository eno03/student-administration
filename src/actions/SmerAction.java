/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.SmerFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class SmerAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getSmerFrame() == null) {
            Controller.getInstance().setSmerFrame(new SmerFrame());
        }
        if (Controller.getInstance().getSmerScene() == null) {
            Controller.getInstance().setSmerScene(new Scene(Controller.getInstance().getSmerFrame(), 685, 260));
        }
        Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba|Smerovi");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getSmerScene());
        Controller.getInstance().getSmerFrame().setStyle("-fx-background-color: #A71D36");
        Connector.getInstance().getAllSmer();
        Controller.getInstance().getSmerFrame().getSmerTableView().setItems(Connector.getInstance().getSmers());
    }

}
