/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Controller;
import frames.OstaloFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class OstaloAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

        if (Controller.getInstance().getOstaloFrame() == null) {
            Controller.getInstance().setOstaloFrame(new OstaloFrame());
        }
        if (Controller.getInstance().getOstaloScene() == null) {
            Controller.getInstance().setOstaloScene(new Scene(Controller.getInstance().getOstaloFrame(), 350, 260));
        }

        Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba|Ostalo");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getOstaloScene());
        Controller.getInstance().getOstaloFrame().setStyle("-fx-background-color: gainsboro");
    }

}
