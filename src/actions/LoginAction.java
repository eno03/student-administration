/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Controller;
import data.Account;
import frames.MainFrame;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javax.swing.JOptionPane;

/**
 *
 * @author nikol
 */
public class LoginAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        String user = Controller.getInstance().getLoginFrame().getUserField().getText();
        String pass = Controller.getInstance().getLoginFrame().getPassField().getText();

        try {
            if (controller.Connector.getInstance().loginDatabase(user, pass)) {
                Controller.getInstance().setAccount(new Account(user, pass));
                JOptionPane.showMessageDialog(null, "Uspešno prijavljen admin: " + user);
                if (Controller.getInstance().getMainFrame() == null) {
                    Controller.getInstance().setMainFrame(new MainFrame());
                    Controller.getInstance().getMainFrame().getAccLabel().setText("Account: " + controller.Controller.getInstance().getAccount().getUsername());

                }
                if (Controller.getInstance().getMainScene() == null) {
                    Controller.getInstance().setMainScene(new Scene(Controller.getInstance().getMainFrame(), 750, 260));
                }

                Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba");
                Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getMainScene());
                Controller.getInstance().getMainFrame().setStyle("-fx-background-color: gainsboro");
            } else {
                JOptionPane.showMessageDialog(null, "Šifra koju ste uneli je netačna!\nPokušajte ponovo?");
                Controller.getInstance().getLoginFrame().getPassField().clear();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo pristupiti bazi podataka!");
        }

    }

}
