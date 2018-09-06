/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.Controller;
import frames.LoginFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author nikol
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginFrame loginFrame = new LoginFrame();
        Scene loginScene = new Scene(loginFrame, 365, 120);

        Controller.getInstance().setPrimaryStage(primaryStage);
        Controller.getInstance().setLoginFrame(loginFrame);
        Controller.getInstance().setLoginScene(loginScene);
        Controller.getInstance().getPrimaryStage().setScene(loginScene);
        Controller.getInstance().getLoginFrame().setStyle("-fx-background-color: #A71D36");
        primaryStage.getIcons().add(new Image("./images/icon.png"));
        primaryStage.setTitle("FIT|Login");
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
