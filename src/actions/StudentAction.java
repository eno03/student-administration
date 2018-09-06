/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.StudentFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class StudentAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getStudentFrame() == null) {
            Controller.getInstance().setStudentFrame(new StudentFrame());
        }
        if (Controller.getInstance().getStudentScene() == null) {
            Controller.getInstance().setStudentScene(new Scene(Controller.getInstance().getStudentFrame(), 1200, 400));
        }
        Controller.getInstance().getPrimaryStage().setTitle("FIT|Studentska sluzba|Studenti");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getStudentScene());
        Controller.getInstance().getStudentFrame().setStyle("-fx-background-color: #A71D36");
        Connector.getInstance().ucitajSmerove();
        Controller.getInstance().getStudentFrame().getSmerBox().getItems().addAll(Connector.getInstance().getSmerovi());
        Connector.getInstance().ucitajStatuse();
        Controller.getInstance().getStudentFrame().getStatusBox().getItems().addAll(Connector.getInstance().getStatusi());
        Connector.getInstance().getAllStudents();
        Controller.getInstance().getStudentFrame().getStudentTableView().setItems(Connector.getInstance().getStudents());
    }

}
