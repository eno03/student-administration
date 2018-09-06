/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Controller;
import frames.LoginFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class LogoutAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        Controller.getInstance().setAccount(null);
        if (Controller.getInstance().getLoginFrame() == null) {
            Controller.getInstance().setLoginFrame(new LoginFrame());
        }
        if (Controller.getInstance().getLoginScene() == null) {
            Controller.getInstance().setLoginScene(new Scene(Controller.getInstance().getLoginFrame(), 365, 120));
        }
        Controller.getInstance().getPrimaryStage().setTitle("FIT|Login");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getLoginScene());
        Controller.getInstance().getLoginFrame().setStyle("-fx-background-color: #A71D36");
    }

}
