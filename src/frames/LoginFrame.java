/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.LoginAction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author nikol
 */
public class LoginFrame extends VBox {

    private final TextField userField = new TextField();
    private final TextField passField = new PasswordField();
    private final Button cancelButton = new Button("Cancel");
    private final Button enterButton = new Button("Enter");
    private final GridPane gridPane = new GridPane();
    private final HBox hBox = new HBox(20);
    private final Label usernameLabel = new Label("username:");
    private final Label passwordLabel = new Label("password:");

    public LoginFrame() {
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 5, 5));
        usernameLabel.setTextFill(Color.web("#ffffff"));
        passwordLabel.setTextFill(Color.web("#ffffff"));
        cancelButton.setMinWidth(60);
        enterButton.setMinWidth(60);
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        userField.setAlignment(Pos.CENTER_RIGHT);
        passField.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().add(gridPane);
        hBox.getChildren().addAll(cancelButton, enterButton);
        this.getChildren().add(hBox);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        cancelButton.setOnAction((event) -> {
            System.exit(0);
        });
        enterButton.setOnAction(new LoginAction());

    }

    public TextField getUserField() {
        return userField;
    }

    public TextField getPassField() {
        return passField;
    }

}
