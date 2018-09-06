/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.PredmetFrame;
import frames.SmerFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class PredmetAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getPredmetFrame() == null) {
            Controller.getInstance().setPredmetFrame(new PredmetFrame());
        }
        if (Controller.getInstance().getPredmetScene() == null) {
            Controller.getInstance().setPredmetScene(new Scene(Controller.getInstance().getPredmetFrame(), 780, 260));
        }
        Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba|Predmeti");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getPredmetScene());
        Controller.getInstance().getPredmetFrame().setStyle("-fx-background-color: #A71D36");
        Connector.getInstance().getAllPredmet();
        Controller.getInstance().getPredmetFrame().getPredmetTableView().setItems(Connector.getInstance().getPredmets());
    }

}
