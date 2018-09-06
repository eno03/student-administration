/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.EvidencijaFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class EvidencijaAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getEvidencijaFrame() == null) {
            Controller.getInstance().setEvidencijaFrame(new EvidencijaFrame());
        }
        if (Controller.getInstance().getEvidencijaScene() == null) {
            Controller.getInstance().setEvidencijaScene(new Scene(Controller.getInstance().getEvidencijaFrame(), 960, 360));
        }
        Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba|Evidencija");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getEvidencijaScene());
        Controller.getInstance().getEvidencijaFrame().setStyle("-fx-background-color: #A71D36");
        Connector.getInstance().ucitajRokove();
        Controller.getInstance().getEvidencijaFrame().getRokBox().getItems().addAll(Connector.getInstance().getRokovi());
        Connector.getInstance().ucitajPredmete();
        Controller.getInstance().getEvidencijaFrame().getPredmetBox().getItems().addAll(Connector.getInstance().getPredmeti());
        Connector.getInstance().ucitajNacine();
        Controller.getInstance().getEvidencijaFrame().getNacinPolaganjaBox().getItems().addAll(Connector.getInstance().getNacini());
        Connector.getInstance().getAllEvidencijas();
        Controller.getInstance().getEvidencijaFrame().getEvidencijaTableView().setItems(Connector.getInstance().getEvidencijas());

    }

}
