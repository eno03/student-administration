/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackAction;
import actions.OstaloAction;
import controller.Connector;
import data.Nacin;
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
public class NacinFrame extends HBox {

    private final TextField idField = new TextField();
    private final TextField infoField = new TextField();
    private final Label idLabel = new Label("Id: ");
    private final Label infoLabel = new Label("Info: ");

    private final Text caption = new Text("Načini polaganja");
    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);
    private TableView<Nacin> nacinTableView;
    private final Button backButton = new Button("Back");

    public NacinFrame() {
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));

        idLabel.setTextFill(Color.web("#ffffff"));
        infoLabel.setTextFill(Color.web("#ffffff"));

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(infoLabel, 0, 1);
        gridPane.add(infoField, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        idField.setAlignment(Pos.CENTER_RIGHT);
        infoField.setAlignment(Pos.CENTER_RIGHT);

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

        TableColumn<Nacin, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMaxWidth(55);
        TableColumn<Nacin, String> infoColumn = new TableColumn<>("Info");
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));
        infoColumn.setMinWidth(190);
        nacinTableView = new TableView<>();
        nacinTableView.setMinWidth(162);
        nacinTableView.getColumns().addAll(idColumn, infoColumn);
        nacinTableView.setRowFactory(tv -> {
            TableRow<Nacin> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Nacin rowData = row.getItem();
                    idField.setText(rowData.getId() + "");
                    infoField.setText(rowData.getInfo());
                }
            });
            return row;
        });
        this.getChildren().addAll(nacinTableView);
        backButton.setOnAction(new OstaloAction());
        updateButton.setOnAction(e -> {
            try {
                checkU(idField.getText(), infoField.getText());
                controller.Connector.getInstance().updateNacin();
                controller.Connector.getInstance().refreshNacins();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        insertButton.setOnAction(e -> {
            try {
                checkI(idField.getText(), infoField.getText());
                controller.Connector.getInstance().addNacin();
                controller.Connector.getInstance().refreshNacins();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        deleteButton.setOnAction((event) -> {
            try {
                checkD(idField.getText());
                controller.Connector.getInstance().deleteNacin();
                controller.Connector.getInstance().refreshNacins();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

    }

    public TableView<Nacin> getNacinTableView() {
        return nacinTableView;
    }

    public void setNacinTableView(TableView<Nacin> nacinTableView) {
        this.nacinTableView = nacinTableView;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getInfoField() {
        return infoField;
    }

    private void checkI(String id, String info) throws emptyString, unallowedString {

        if (id.isEmpty()) {
            throw new emptyString("Unesite id!");
        } else if (info.isEmpty()) {
            throw new emptyString("Unesite info!");
        }

        for (Nacin p : Connector.getInstance().getNacins()) {
            if ((p.getId() + "").equals(id)) {
                throw new unallowedString("Nacin sa zadatim id-om već postoji!");
            }
        }

        if (info.length() > 20) {
            throw new unallowedString("Info moze imati najvise 20 karaktera!");
        }
        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
    }

    private void checkU(String id, String info) throws emptyString, unallowedString {

        if (id.isEmpty()) {
            throw new emptyString("Unesite id!");
        } else if (info.isEmpty()) {
            throw new emptyString("Unesite info!");
        }

        boolean pom = false;
        for (Nacin p : Connector.getInstance().getNacins()) {
            if ((p.getId() + "").equals(id)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Nacin sa unešenim id-om ne postoji!");
        }

        if (info.length() > 20) {
            throw new unallowedString("Info moze imati najvise 20 karaktera!");
        }
        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
    }

    private void checkD(String id) throws emptyString, unallowedString {
        if (id.isEmpty()) {
            throw new emptyString("Unesite id!");
        }
        boolean pom = false;
        for (Nacin p : Connector.getInstance().getNacins()) {
            if ((p.getId() + "").equals(id)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Nacin sa unešenim id-om ne postoji!");
        }
    }
}
