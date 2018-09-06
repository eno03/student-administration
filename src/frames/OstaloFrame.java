/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackOstaloAction;
import actions.BackAction;
import actions.NacinAction;
import actions.StatusAction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author nikol
 */
public class OstaloFrame extends BorderPane {
    
    private final HBox pane = new HBox(15);
    private final GridPane gridPane = new GridPane();
    private final Button statusButton = new Button("Status");
    private final Button nacinButton = new Button("Nacin");
    private final Button backButton = new Button("Back");
    private final Label backLabel = new Label();
    private final Label nacinLabel = new Label();
    private final Label statusLabel = new Label();
    
    public OstaloFrame() {
        this.setPadding(new Insets(5));
        pane.setMaxSize(340, 84);
        pane.setMinSize(340, 84);
        statusButton.setMinSize(70, 25);
        nacinButton.setMinSize(70, 25);
        gridPane.add(statusLabel, 0, 0);
        statusLabel.setGraphic(new ImageView("./images/status.png"));
        gridPane.add(statusButton, 0, 1);
        gridPane.add(nacinLabel, 1, 0);
        nacinLabel.setGraphic(new ImageView("./images/nacin.png"));
        gridPane.add(nacinButton, 1, 1);
        gridPane.add(backLabel, 2, 0);
        backLabel.setGraphic(new ImageView("./images/back.png"));
        gridPane.add(backButton, 2, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(40);
        gridPane.setVgap(10);
        this.setTop(pane);
        pane.setStyle("-fx-background-image: url(\"./images/imageo.png\");");
        
        this.setCenter(gridPane);
        
        statusButton.setOnAction(new StatusAction());
        nacinButton.setOnAction(new NacinAction());
        backButton.setOnAction(new BackAction());
    }
    
}
