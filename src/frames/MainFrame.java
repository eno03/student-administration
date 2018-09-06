/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.OstaloAction;
import actions.EvidencijaAction;
import actions.RokAction;
import actions.PredmetAction;
import actions.StudentAction;
import actions.SmerAction;
import actions.LogoutAction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author nikol
 */
public class MainFrame extends BorderPane {

    private final HBox pane = new HBox(15);
    private final GridPane gridPane = new GridPane();
    private final Label accLabel = new Label();
    private final Button studentButton = new Button("Studenti");
    private final Button evidencijaButton = new Button("Evidencija");
    private final Button predmetButton = new Button("Predmeti");
    private final Button smerButton = new Button("Smerovi");
    private final Button rokoviButton = new Button("Rokovi");
    private final Button ostaloButton = new Button("Ostalo");
   // private final Button statusButton = new Button("Status");
    //rivate final Button nacinButton = new Button("Nacin");
    private final Label studentLabel = new Label();
    private final Label evidencijaLabel = new Label();
    private final Label predmetLabel = new Label();
    private final Label smerLabel = new Label();
    private final Label rokoviLabel = new Label();
    private final Label ostaloLabel = new Label();
    
   // private final Label nacinLabel = new Label();
    //rivate final Label statusLabel = new Label();
    private final Button logoutButton = new Button("Log Out");

    public MainFrame() {
        this.setPadding(new Insets(5));
        pane.setMaxSize(740, 84);
        pane.setMinSize(740, 84);
        studentButton.setMinSize(70, 25);
        evidencijaButton.setMinSize(70, 25);
        predmetButton.setMinSize(70, 25);
        smerButton.setMinSize(70, 25);
        rokoviButton.setMinSize(70, 25);
        ostaloButton.setMinSize(70, 25);
        gridPane.add(studentLabel, 1, 0);
        studentLabel.setGraphic(new ImageView("./images/student.png"));
        gridPane.add(studentButton, 1, 1);
        gridPane.add(evidencijaLabel, 4, 0);
        evidencijaLabel.setGraphic(new ImageView("./images/evidencija.png"));
        gridPane.add(evidencijaButton, 4, 1);
        gridPane.add(predmetLabel, 2, 0);
        predmetLabel.setGraphic(new ImageView("./images/predmet.png"));
        gridPane.add(predmetButton, 2, 1);
        gridPane.add(smerLabel, 0, 0);
        smerLabel.setGraphic(new ImageView("./images/smer.png"));
        gridPane.add(smerButton, 0, 1);
        gridPane.add(rokoviLabel, 3, 0);
        rokoviLabel.setGraphic(new ImageView("./images/rok.png"));
        gridPane.add(rokoviButton, 3, 1);
         gridPane.add(ostaloLabel, 5, 0);
        ostaloLabel.setGraphic(new ImageView("./images/ostalo.png"));
        gridPane.add(ostaloButton, 5, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        logoutButton.setMaxHeight(15);
        this.setTop(pane);
        accLabel.setTextFill(Color.web("#ffffff"));
        pane.getChildren().addAll(accLabel, logoutButton);
        pane.setAlignment(Pos.TOP_RIGHT);
        pane.setStyle("-fx-background-image: url(\"./images/image.png\");");
        
        this.setCenter(gridPane);
        logoutButton.setAlignment(Pos.BOTTOM_RIGHT);
        smerButton.setOnAction(new SmerAction());
        studentButton.setOnAction(new StudentAction());
        predmetButton.setOnAction(new PredmetAction());
        rokoviButton.setOnAction(new RokAction());
        evidencijaButton.setOnAction(new EvidencijaAction());
        ostaloButton.setOnAction(new OstaloAction());
        logoutButton.setOnAction(new LogoutAction());

    }

    public Label getAccLabel() {
        return accLabel;
    }

}
