/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackAction;
import controller.Connector;
import data.Predmet;
import exceptions.emptyString;
import exceptions.unallowedString;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 *
 * @author nikol
 */
public class PredmetFrame extends HBox {

    private final TextField idField = new TextField();
    private final TextField oznakaField = new TextField();
    private final TextField nazivField = new TextField();
    private final TextField espbField = new TextField();
    
    private final Label idLabel = new Label("Id: ");
    private final Label oznakaLabel = new Label("Oznaka: ");
    private final Label nazivLabel = new Label("Naziv: ");
    private final Label espbLabel = new Label("ESPB: ");
    final Text caption = new Text("Predmeti");

    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final Button backButton = new Button("Back");

    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);

    private TableView<Predmet> predmetTableView = new TableView<>();

    public PredmetFrame() {

        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));
        this.getChildren().addAll(vBox);
        this.getChildren().add(predmetTableView);
        idLabel.setTextFill(Color.web("#ffffff"));
        nazivLabel.setTextFill(Color.web("#ffffff"));
        oznakaLabel.setTextFill(Color.web("#ffffff"));
        espbLabel.setTextFill(Color.web("#ffffff"));

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(oznakaLabel, 0, 1);
        gridPane.add(oznakaField, 1, 1);
        gridPane.add(nazivLabel, 0, 2);
        gridPane.add(nazivField, 1, 2);
        gridPane.add(espbLabel, 0, 3);
        gridPane.add(espbField, 1, 3);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        

        idField.setAlignment(Pos.CENTER_RIGHT);
        oznakaField.setAlignment(Pos.CENTER_RIGHT);
        nazivField.setAlignment(Pos.CENTER_RIGHT);
        espbField.setAlignment(Pos.CENTER_RIGHT);
        
        caption.setFill(Color.BLACK);
        caption.setStyle("-fx-font: 35 ariel;");

        insertButton.setMinWidth(60);
        updateButton.setMinWidth(60);
        deleteButton.setMinWidth(60);
        backButton.setMinWidth(60);
        
        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        vBox.getChildren().addAll(caption,gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);

        TableColumn<Predmet, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMaxWidth(50);
        TableColumn<Predmet, String> oznakaColumn = new TableColumn<>("Oznaka");
        oznakaColumn.setCellValueFactory(new PropertyValueFactory<>("oznaka"));
        oznakaColumn.setMaxWidth(70);
        TableColumn<Predmet, String> nazivColumn = new TableColumn<>("Naziv");
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        nazivColumn.setMinWidth(250);
        TableColumn<Predmet, Integer> espbColumn = new TableColumn<>("ESPB");
        espbColumn.setCellValueFactory(new PropertyValueFactory<>("espb"));
        espbColumn.setMaxWidth(60);

        predmetTableView.getColumns().addAll(idColumn, oznakaColumn, nazivColumn, espbColumn);
        predmetTableView.setRowFactory(tv -> {
            TableRow<Predmet> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Predmet rowData = row.getItem();
                    idField.setText(rowData.getId() + "");
                    oznakaField.setText(rowData.getOznaka());
                    nazivField.setText(rowData.getNaziv());
                    espbField.setText(rowData.getEspb() + "");
                }
            });
            return row;
        });

        backButton.setOnAction(new BackAction());
        updateButton.setOnAction(e -> {
            try {
                checkU(idField.getText(), oznakaField.getText(), nazivField.getText(), espbField.getText());
                controller.Connector.getInstance().updatePredmet();
                controller.Connector.getInstance().refreshPredmets();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        insertButton.setOnAction(e -> {
            try {
                checkI(idField.getText(), oznakaField.getText(), nazivField.getText(), espbField.getText());
                controller.Connector.getInstance().addPredmet();
                controller.Connector.getInstance().refreshPredmets();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        deleteButton.setOnAction((event) -> {
            try {
                checkD(idField.getText());
                controller.Connector.getInstance().deletePredmet();
                controller.Connector.getInstance().refreshPredmets();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

    }

    public TableView<Predmet> getPredmetTableView() {
        return predmetTableView;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getNazivField() {
        return nazivField;
    }

    public TextField getEspbField() {
        return espbField;
    }

    public TextField getOznakaField() {
        return oznakaField;
    }

    private void checkI(String id, String oznaka, String naziv, String espb) throws emptyString, unallowedString {

        if (id.isEmpty()) {
            throw new emptyString("Unesite id predmeta!");
        } else if (oznaka.isEmpty()) {
            throw new emptyString("Unesite naziv predmeta!");
        } else if (naziv.isEmpty()) {
            throw new emptyString("Unesite oznaku predmeta!");
        } else if (espb.isEmpty()) {
            throw new emptyString("Unesite espb predmeta!");
        }

        for (Predmet p : Connector.getInstance().getPredmets()) {
            if ((p.getId() + "").equals(id)) {
                throw new unallowedString("Predmet sa unešenim id već postoji!");
            }
        }
        for (Predmet p : Connector.getInstance().getPredmets()) {
            if (p.getOznaka().equals(oznaka) && p.getNaziv().equals(oznaka) && (p.getEspb() + "").equals(oznaka)) {
                throw new unallowedString("Predmet već postoji, samo poseduje drugi id!");
            }
        }

        if (naziv.length() > 50) {
            throw new unallowedString("Naziv moze imati najvise 50 karaktera!");
        }
        if (oznaka.length() > 5) {
            throw new unallowedString("Oznaka moze imati najvise 5 karaktera!");
        }
        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
        if (!espb.matches("^-?\\d+$")) {
            throw new unallowedString("Espb mora biti realan ceo broj!");
        }
    }

    private void checkU(String id, String oznaka, String naziv, String espb) throws emptyString, unallowedString {

        if (id.isEmpty()) {
            throw new emptyString("Unesite id predmeta!");
        } else if (naziv.isEmpty()) {
            throw new emptyString("Unesite naziv predmeta!");
        } else if (oznaka.isEmpty()) {
            throw new emptyString("Unesite oznaku predmeta!");
        } else if (espb.isEmpty()) {
            throw new emptyString("Unesite espb predmeta!");
        }

        boolean pom = false;
        for (Predmet p : Connector.getInstance().getPredmets()) {
            if ((p.getId() + "").equals(id)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Predmet sa unešenim id ne postoji!");
        }

        for (Predmet p : Connector.getInstance().getPredmets()) {
            if (p.getOznaka().equals(oznaka) && p.getNaziv().equals(naziv) && (p.getEspb() + "").equals(oznaka)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Predmet sa unešenim id ne postoji!");
        }

        if (naziv.length() > 50) {
            throw new unallowedString("Naziv moze imati najvise 50 karaktera!");
        }
        if (oznaka.length() > 5) {
            throw new unallowedString("Oznaka moze imati najvise 5 karaktera!");
        }
        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
        if (!espb.matches("^-?\\d+$")) {
            throw new unallowedString("Espb mora biti realan ceo broj!");
        }
    }

    private void checkD(String id) throws emptyString, unallowedString {
        if (id.isEmpty()) {
            throw new emptyString("Unesite id smera!");
        }
        boolean pom = true;
        for (Predmet p : Connector.getInstance().getPredmets()) {
            if (((p.getId() + "").equals(id))) {
                pom = true;
            }
        }
        if (!pom) {
            throw new unallowedString("Predmet sa unešenim id ne postoji!");
        }
        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
    }
}
