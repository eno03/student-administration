/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.NacinFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class NacinAction implements EventHandler<ActionEvent> {

    public NacinAction() {
    }

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getNacinFrame() == null) {
            Controller.getInstance().setNacinFrame(new NacinFrame());
        }
        if (Controller.getInstance().getNacinScene() == null) {
            Controller.getInstance().setNacinScene(new Scene(Controller.getInstance().getNacinFrame(),580, 210));
        }
        Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba|Nacin");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getNacinScene());
        Controller.getInstance().getNacinFrame().setStyle("-fx-background-color: #A71D36");
        Connector.getInstance().getAllNacin();
        Controller.getInstance().getNacinFrame().getNacinTableView().setItems(Connector.getInstance().getNacins());
    }

}
