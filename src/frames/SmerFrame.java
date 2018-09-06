/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackAction;
import controller.Connector;
import data.Smer;
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
public class SmerFrame extends HBox {

    private final TextField idField = new TextField();
    private final TextField nazivField = new TextField();
    private final TextField oznakaField = new TextField();
    private final TextField espbField = new TextField();
    private final Label idLabel = new Label("Id: ");
    private final Label nazivLabel = new Label("Naziv: ");
    private final Label oznakaLabel = new Label("Oznaka: ");
    private final Label espbLabel = new Label("ESPB: ");
    final Text caption = new Text("Smerovi");

    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final Button backButton = new Button("Back");

    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(15);

    private TableView<Smer> smerTableView = new TableView<>();

    public SmerFrame() {

        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));
        this.getChildren().addAll(vBox);
        this.getChildren().addAll(smerTableView);
        idLabel.setTextFill(Color.web("#ffffff"));
        nazivLabel.setTextFill(Color.web("#ffffff"));
        oznakaLabel.setTextFill(Color.web("#ffffff"));
        espbLabel.setTextFill(Color.web("#ffffff"));
        
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nazivLabel, 0, 1);
        gridPane.add(nazivField, 1, 1);
        gridPane.add(oznakaLabel, 0, 2);
        gridPane.add(oznakaField, 1, 2);
        gridPane.add(espbLabel, 0, 3);
        gridPane.add(espbField, 1, 3);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        idField.setAlignment(Pos.CENTER_RIGHT);
        nazivField.setAlignment(Pos.CENTER_RIGHT);
        oznakaField.setAlignment(Pos.CENTER_RIGHT);
        espbField.setAlignment(Pos.CENTER_RIGHT);
        
        insertButton.setMinWidth(60);
        updateButton.setMinWidth(60);
        deleteButton.setMinWidth(60);
        backButton.setMinWidth(60);
        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        
        caption.setFill(Color.BLACK);
        caption.setStyle("-fx-font: 35 ariel;");
        vBox.getChildren().addAll(caption, gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);

        TableColumn<Smer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("smer_id"));
        idColumn.setMaxWidth(45);
        TableColumn<Smer, String> nazivColumn = new TableColumn<>("Naziv");
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("smer_naziv"));
        nazivColumn.setMinWidth(180);
        TableColumn<Smer, String> oznakaColumn = new TableColumn<>("Oznaka");
        oznakaColumn.setCellValueFactory(new PropertyValueFactory<>("smer_oznaka"));
        oznakaColumn.setMaxWidth(65);
        TableColumn<Smer, Integer> espbColumn = new TableColumn<>("ESPB");
        espbColumn.setCellValueFactory(new PropertyValueFactory<>("smer_espb"));
        espbColumn.setMaxWidth(65);

        smerTableView.setMaxWidth(370);
        smerTableView.getColumns().addAll(idColumn, nazivColumn, oznakaColumn, espbColumn);
        smerTableView.setRowFactory(tv -> {
            TableRow<Smer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Smer rowData = row.getItem();
                    idField.setText(rowData.getSmer_id() + "");
                    nazivField.setText(rowData.getSmer_naziv());
                    oznakaField.setText(rowData.getSmer_oznaka());
                    espbField.setText(rowData.getSmer_espb() + "");
                }
            });
            return row;
        });

        backButton.setOnAction(new BackAction());
        updateButton.setOnAction(e -> {
            try {
                checkU(idField.getText(), nazivField.getText(), oznakaField.getText(), espbField.getText());
                controller.Connector.getInstance().updateSmer();
                controller.Connector.getInstance().refreshSmers();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        insertButton.setOnAction(e -> {
            try {
                checkI(idField.getText(), nazivField.getText(), oznakaField.getText(), espbField.getText());
                controller.Connector.getInstance().addSmer();
                controller.Connector.getInstance().refreshSmers();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        deleteButton.setOnAction((event) -> {
            try {
                checkD(idField.getText());
                controller.Connector.getInstance().deleteSmer();
                controller.Connector.getInstance().refreshSmers();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

    }

    public TableView<Smer> getSmerTableView() {
        return smerTableView;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getNazivField() {
        return nazivField;
    }

    public TextField getOznakaField() {
        return oznakaField;
    }

    public TextField getEspbField() {
        return espbField;
    }

    private void checkI(String id, String naziv, String oznaka, String espb) throws emptyString, unallowedString {

        if (id.isEmpty()) {
            throw new emptyString("Unesite id smera!");
        } else if (naziv.isEmpty()) {
            throw new emptyString("Unesite naziv smera!");
        } else if (oznaka.isEmpty()) {
            throw new emptyString("Unesite oznaku smera!");
        } else if (espb.isEmpty()) {
            throw new emptyString("Unesite espb smera!");
        }

        for (Smer s : Connector.getInstance().getSmers()) {
            if ((s.getSmer_id() + "").equals(id)) {
                throw new unallowedString("Smer sa unešenim id već postoji!");
            }
        }
        for (Smer s : Connector.getInstance().getSmers()) {
            if (s.getSmer_naziv().equals(naziv) && s.getSmer_oznaka().equals(oznaka) && (s.getSmer_espb() + "").equals(oznaka)) {
                throw new unallowedString("Smer već postoji, samo poseduje drugi id!");
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

    private void checkU(String id, String naziv, String oznaka, String espb) throws emptyString, unallowedString {

        if (id.isEmpty()) {
            throw new emptyString("Unesite id smera!");
        } else if (naziv.isEmpty()) {
            throw new emptyString("Unesite naziv smera!");
        } else if (oznaka.isEmpty()) {
            throw new emptyString("Unesite oznaku smera!");
        } else if (espb.isEmpty()) {
            throw new emptyString("Unesite espb smera!");
        }

        boolean pom = false;
        for (Smer s : Connector.getInstance().getSmers()) {
            if ((s.getSmer_id() + "").equals(id)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Smer sa unešenim id ne postoji!");
        }

        for (Smer s : Connector.getInstance().getSmers()) {
            if (s.getSmer_naziv().equals(naziv) && s.getSmer_oznaka().equals(oznaka) && (s.getSmer_espb() + "").equals(oznaka)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Smer sa unešenim id ne postoji!");
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
        for (Smer s : Connector.getInstance().getSmers()) {
            if (((s.getSmer_id() + "").equals(id))) {
                pom = true;
            }
        }
        if (!pom) {
            throw new unallowedString("Smer sa unešenim id ne postoji!");
        }
        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
    }

}
