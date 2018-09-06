/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackAction;
import controller.Connector;
import data.Rok;
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
public class RokFrame extends HBox {

    private final TextField oznakaField = new TextField();
    private final TextField trajanjeField = new TextField();
    private final Label oznakaLabel = new Label("Oznaka: ");
    private final Label trajanjeLabel = new Label("Trajanje: ");

    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);
    private TableView<Rok> rokTableView;
    private final Button backButton = new Button("Back");

    final Text caption = new Text("Ispitni rokovi");

    public RokFrame() {
        oznakaLabel.setTextFill(Color.web("#ffffff"));
        trajanjeLabel.setTextFill(Color.web("#ffffff"));
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));
        gridPane.add(oznakaLabel, 0, 0);
        gridPane.add(oznakaField, 1, 0);
        gridPane.add(trajanjeLabel, 0, 1);
        gridPane.add(trajanjeField, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        oznakaField.setAlignment(Pos.CENTER_RIGHT);
        trajanjeField.setAlignment(Pos.CENTER_RIGHT);

        insertButton.setMinWidth(60);
        updateButton.setMinWidth(60);
        deleteButton.setMinWidth(60);
        backButton.setMinWidth(60);

        caption.setFill(Color.BLACK);
        caption.setStyle("-fx-font: 35 ariel;");

        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        vBox.getChildren().addAll(caption, gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBox);

        TableColumn<Rok, String> oznakaColumn = new TableColumn<>("Oznaka");
        oznakaColumn.setCellValueFactory(new PropertyValueFactory<>("oznaka"));
        TableColumn<Rok, String> trajanjeColumn = new TableColumn<>("Trajanje");
        trajanjeColumn.setCellValueFactory(new PropertyValueFactory<>("trajanje"));
        rokTableView = new TableView<>();
        rokTableView.setMaxWidth(190);
        rokTableView.getColumns().addAll(oznakaColumn, trajanjeColumn);
        rokTableView.setRowFactory(tv -> {
            TableRow<Rok> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Rok rowData = row.getItem();
                    oznakaField.setText(rowData.getOznaka());
                    trajanjeField.setText(rowData.getTrajanje() + "");
                }
            });
            return row;
        });
        this.getChildren().addAll(rokTableView);
        backButton.setOnAction(new BackAction());
        updateButton.setOnAction(e -> {
            try {
                checkU(oznakaField.getText(), trajanjeField.getText());
                controller.Connector.getInstance().updateRok();
                controller.Connector.getInstance().refreshRoks();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        insertButton.setOnAction(e -> {
            try {
                checkI(oznakaField.getText(), trajanjeField.getText());
                controller.Connector.getInstance().addRok();
                controller.Connector.getInstance().refreshRoks();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        deleteButton.setOnAction((event) -> {
            try {
                checkD(oznakaField.getText());
                controller.Connector.getInstance().deleteRok();
                controller.Connector.getInstance().refreshRoks();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

    }

    public TableView<Rok> getRokTableView() {
        return rokTableView;
    }

    public void setRokTableView(TableView<Rok> rokTableView) {
        this.rokTableView = rokTableView;
    }

    public TextField getOznakaField() {
        return oznakaField;
    }

    public TextField getTrajanjeField() {
        return trajanjeField;
    }

    private void checkI(String oznaka, String trajanje) throws emptyString, unallowedString {

        if (oznaka.isEmpty()) {
            throw new emptyString("Unesite oznaku roka!");
        } else if (trajanje.isEmpty()) {
            throw new emptyString("Unesite trajanje roka!");
        }

        for (Rok p : Connector.getInstance().getRoks()) {
            if (p.getOznaka().equals(oznaka)) {
                throw new unallowedString("Rok već postoji!");
            }
        }

        if (oznaka.length() > 10) {
            throw new unallowedString("Oznaka moze imati najvise 10 karaktera!");
        }
        if (!trajanje.matches("^-?\\d+$")) {
            throw new unallowedString("Trajanje mora biti realan ceo broj!");
        }
    }

    private void checkU(String oznaka, String trajanje) throws emptyString, unallowedString {

        if (oznaka.isEmpty()) {
            throw new emptyString("Unesite oznaku roka!");
        } else if (trajanje.isEmpty()) {
            throw new emptyString("Unesite trajanje roka!");
        }

        boolean pom = false;
        for (Rok p : Connector.getInstance().getRoks()) {
            if (p.getOznaka().equals(oznaka)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Rok sa unešenom oznakom ne postoji!");
        }

        if (oznaka.length() > 10) {
            throw new unallowedString("Oznaka moze imati najvise 10 karaktera!");
        }
        if (!trajanje.matches("^-?\\d+$")) {
            throw new unallowedString("Trajanje mora biti realan ceo broj!");
        }
    }

    private void checkD(String oznaka) throws emptyString, unallowedString {
        if (oznaka.isEmpty()) {
            throw new emptyString("Unesite oznaku roka!");
        }
        boolean pom = false;
        for (Rok p : Connector.getInstance().getRoks()) {
            if (p.getOznaka().equals(oznaka)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Rok sa unešenom oznakom ne postoji!");
        }

        if (oznaka.length() > 10) {
            throw new unallowedString("Oznaka moze imati najvise 10 karaktera!");
        }
    }

}
