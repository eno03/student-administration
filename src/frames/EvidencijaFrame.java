/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackAction;
import controller.Connector;
import data.Evidencija;
import data.Student;
import exceptions.emptyString;
import exceptions.unallowedString;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 *
 * @author nikol
 */
public class EvidencijaFrame extends HBox {

    private final TextField idField = new TextField();
    private final ComboBox<String> rokBox = new ComboBox<>();
    private final TextField indeksField = new TextField();
    private final ComboBox<String> predmetBox = new ComboBox<>();
    private final DatePicker datumOdrzavanjaPicker = new DatePicker();
    private final ComboBox<String> nacinPolaganjaBox = new ComboBox<>();
    private final TextField ocena = new TextField();

    private final Label idLabel = new Label("Id: ");
    private final Label rokLabel = new Label("Rok: ");
    private final Label indeksLabel = new Label("Indeks: ");
    private final Label predmetLabel = new Label("Predmet: ");
    private final Label datumOdrzavanjaLabel = new Label("Datum održavanja: ");
    private final Label nacinPolaganjaLabel = new Label("Način polaganja: ");
    private final Label ocenaLabel = new Label("Ocena: ");

    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final Button backButton = new Button("Back");
    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);
    private TableView<Evidencija> evidencijaTableView;
    private final Text caption = new Text("Evidencija");

    public EvidencijaFrame() {
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));

        idLabel.setTextFill(Color.web("#ffffff"));
        rokLabel.setTextFill(Color.web("#ffffff"));
        indeksLabel.setTextFill(Color.web("#ffffff"));
        predmetLabel.setTextFill(Color.web("#ffffff"));
        datumOdrzavanjaLabel.setTextFill(Color.web("#ffffff"));
        nacinPolaganjaLabel.setTextFill(Color.web("#ffffff"));
        ocenaLabel.setTextFill(Color.web("#ffffff"));
        rokBox.setMinWidth(175);
        predmetBox.setMinWidth(175);
        nacinPolaganjaBox.setMinWidth(175);
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(indeksLabel, 0, 1);
        gridPane.add(indeksField, 1, 1);
        gridPane.add(rokLabel, 0, 2);
        gridPane.add(rokBox, 1, 2);
        gridPane.add(predmetLabel, 0, 3);
        gridPane.add(predmetBox, 1, 3);
        gridPane.add(datumOdrzavanjaLabel, 0, 4);
        gridPane.add(datumOdrzavanjaPicker, 1, 4);
        gridPane.add(nacinPolaganjaLabel, 0, 5);
        gridPane.add(nacinPolaganjaBox, 1, 5);
        gridPane.add(ocenaLabel, 0, 6);
        gridPane.add(ocena, 1, 6);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        caption.setFill(Color.BLACK);
        caption.setStyle("-fx-font: 35 ariel;");
        insertButton.setMinWidth(60);
        updateButton.setMinWidth(60);
        deleteButton.setMinWidth(60);
        backButton.setMinWidth(60);
        
        String pattern = "yyyy-MM-dd";

        datumOdrzavanjaPicker.setPromptText(pattern.toLowerCase());
        datumOdrzavanjaPicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        vBox.getChildren().addAll(caption, gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBox);

        TableColumn<Evidencija, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMaxWidth(45);
        TableColumn<Evidencija, String> rokColumn = new TableColumn<>("Rok");
        rokColumn.setCellValueFactory(new PropertyValueFactory<>("rok"));
        TableColumn<Evidencija, String> indeksColumn = new TableColumn<>("Indeks");
        indeksColumn.setCellValueFactory(new PropertyValueFactory<>("indeks"));
        indeksColumn.setMaxWidth(80);
        TableColumn<Evidencija, String> predmetColumn = new TableColumn<>("Predmet");
        predmetColumn.setCellValueFactory(new PropertyValueFactory<>("predmet"));
        TableColumn<Evidencija, Date> datumColumn = new TableColumn<>("Datum odrzavanja");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        datumColumn.setMinWidth(115);
        TableColumn<Evidencija, String> nacinColumn = new TableColumn<>("Nacin polaganja");
        nacinColumn.setCellValueFactory(new PropertyValueFactory<>("nacin"));
        nacinColumn.setMinWidth(125);
        TableColumn<Evidencija, String> ocenaColumn = new TableColumn<>("Ocena");
        ocenaColumn.setCellValueFactory(new PropertyValueFactory<>("ocena"));
        evidencijaTableView = new TableView<>();
        evidencijaTableView.getColumns().addAll(idColumn, indeksColumn, rokColumn, predmetColumn, datumColumn, nacinColumn, ocenaColumn);
        this.getChildren().addAll(evidencijaTableView);
        backButton.setOnAction(new BackAction());
        updateButton.setOnAction(e -> {
            try {
                checkU(idField.getText(), indeksField.getText(), rokBox.getSelectionModel().getSelectedItem(), predmetBox.getSelectionModel().getSelectedItem(), datumOdrzavanjaPicker.getEditor().getText(), nacinPolaganjaBox.getSelectionModel().getSelectedItem(), ocena.getText());
                controller.Connector.getInstance().updateEvidencija();
                controller.Connector.getInstance().refreshEvidencija();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        insertButton.setOnAction(e -> {
            try {
                checkI(idField.getText(), indeksField.getText(), rokBox.getSelectionModel().getSelectedItem(), predmetBox.getSelectionModel().getSelectedItem(), datumOdrzavanjaPicker.getEditor().getText(), nacinPolaganjaBox.getSelectionModel().getSelectedItem(), ocena.getText());
                controller.Connector.getInstance().addEvidencija();
                controller.Connector.getInstance().refreshEvidencija();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        deleteButton.setOnAction((event) -> {
            try {
                checkD(idField.getText());
                controller.Connector.getInstance().deleteEvidencija();
                controller.Connector.getInstance().refreshEvidencija();
            } catch (emptyString ex) {
                Logger.getLogger(EvidencijaFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        evidencijaTableView.setRowFactory(tv -> {
            TableRow<Evidencija> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Evidencija rowData = row.getItem();
                    idField.setText(rowData.getId() + "");
                    indeksField.setText(rowData.getIndeks() + "");
                    rokBox.setValue(rowData.getRok());
                    predmetBox.setValue(rowData.getPredmet());
                    datumOdrzavanjaPicker.getEditor().setText(rowData.getDatum());
                    nacinPolaganjaBox.setValue(rowData.getNacin());
                    ocena.setText(rowData.getOcena() + "");

                }
            });
            return row;
        });
    }

    public TableView<Evidencija> getEvidencijaTableView() {
        return evidencijaTableView;
    }

    public void setEvidencijaTableView(TableView<Evidencija> evidencijaTableView) {
        this.evidencijaTableView = evidencijaTableView;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getIndeksField() {
        return indeksField;
    }

    public ComboBox<String> getRokBox() {
        return rokBox;
    }

    public ComboBox<String> getPredmetBox() {
        return predmetBox;
    }

    public DatePicker getDatumOdrzavanjaPicker() {
        return datumOdrzavanjaPicker;
    }

    public ComboBox<String> getNacinPolaganjaBox() {
        return nacinPolaganjaBox;
    }

    public TextField getOcena() {
        return ocena;
    }

    private void checkI(String id, String indeks, String rok, String predmetF, String datum, String nacinF, String ocena) throws emptyString, unallowedString {

        String predmet = "";
        String nacin = "";

        if (id.isEmpty()) {
            throw new emptyString("Unesite id evidencije!");
        } else if (indeks.isEmpty()) {
            throw new emptyString("Unesite indeks studenta!");
        } else if (rok.isEmpty()) {
            throw new emptyString("Unesite oznaku roka!");
        } else if (predmetF.isEmpty()) {
            throw new emptyString("Izaberite predmet!");
        } else if (datum.isEmpty()) {
            throw new emptyString("Unesite datum!");
        } else if (nacin.isEmpty()) {
            throw new emptyString("Izaberite nacin polaganja!");
        } else if (ocena.isEmpty()) {
            throw new emptyString("Unesite ocenu!");
        }

        for (Evidencija e : Connector.getInstance().getEvidencijas()) {
            if ((e.getId() + "").equals(id)) {
                throw new unallowedString("Evidencija sa unešenim id-om već postoji!");
            }
        }
        boolean pom = false;
        for (Student s : Connector.getInstance().getStudents()) {
            if ((s.getIndeks() + "").equals(indeks)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Student sa unešenim indeksom ne postoji!");
        }

        for (Student s : Connector.getInstance().getStudents()) {
            if ((s.getIndeks() + "").equals(indeks)) {
                if (Date.valueOf(s.getDatumUpisa()).after(Date.valueOf(datum))) {
                    throw new unallowedString("Datum nije validan!");
                }
            }
        }
        //    String podaci[] = nacinF.split("\\.");
        //  String podaci2[] = predmetF.split("\\.");

        for (Evidencija e : Connector.getInstance().getEvidencijas()) {
            if ((e.getIndeks() + "").equals(indeks) && e.getRok().equals(rok) && e.getPredmet().equals(predmet) && e.getDatum().equals(datum) && (e.getNacin() + "").equals(nacin) && (e.getOcena() + "").equals(ocena)) {
                throw new unallowedString("Evidencija već postoji, samo poseduje drugi id!");
            }
        }

        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
        if (!indeks.matches("^-?\\d+$")) {
            throw new unallowedString("Indeks mora biti realan ceo broj!");
        }
        if (!ocena.matches("^-?\\d+$")) {
            throw new unallowedString("Ocena mora biti realan ceo broj!");
        }
        if (ocena.length() > 4 && ocena.length() < 11) {
            throw new unallowedString("Ocena može biti u opsegu (5-10)");
        }
    }

    private void checkU(String id, String indeks, String rok, String predmet, String datum, String nacin, String ocena) throws emptyString, unallowedString {

        if (id.isEmpty()) {
            throw new emptyString("Unesite id evidencije!");
        } else if (indeks.isEmpty()) {
            throw new emptyString("Unesite indeks studenta!");
        } else if (rok.isEmpty()) {
            throw new emptyString("Unesite oznaku roka!");
        } else if (predmet.isEmpty()) {
            throw new emptyString("Izaberite predmet!");
        } else if (datum.isEmpty()) {
            throw new emptyString("Unesite datum!");
        } else if (nacin.isEmpty()) {
            throw new emptyString("Izaberite nacin polaganja!");
        } else if (ocena.isEmpty()) {
            throw new emptyString("Unesite ocenu!");
        }

        boolean pom = false;
        for (Evidencija e : Connector.getInstance().getEvidencijas()) {
            if ((e.getId() + "").equals(id)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Evidencija sa unešenim id-om ne postoji!");
        }

        pom = false;
        for (Student s : Connector.getInstance().getStudents()) {
            if ((s.getIndeks() + "").equals(indeks)) {
                pom = true;
                break;
            }
        }
        if (!pom) {
            throw new unallowedString("Student sa unešenim indeksom ne postoji!");
        }
        for (Student s : Connector.getInstance().getStudents()) {
            if ((s.getIndeks() + "").equals(indeks)) {
                if (Date.valueOf(s.getDatumUpisa()).after(Date.valueOf(datum))) {
                    throw new unallowedString("Datum nije validan!");
                }
            }
        }
        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
        if (!indeks.matches("^-?\\d+$")) {
            throw new unallowedString("Indeks mora biti realan ceo broj!");
        }
        if (!ocena.matches("^-?\\d+$")) {
            throw new unallowedString("Ocena mora biti realan ceo broj!");
        }
        if (ocena.length() > 4 && ocena.length() < 11) {
            throw new unallowedString("Ocena može biti u opsegu (5-10)");
        }
    }

    private void checkD(String id) throws emptyString, unallowedString {
        if (id.isEmpty()) {
            throw new emptyString("Unesite id evidencije!");
        }
        boolean pom = true;
        for (Evidencija s : Connector.getInstance().getEvidencijas()) {
            if (((s.getId() + "").equals(id))) {
                pom = true;
            }
        }
        if (!pom) {
            throw new unallowedString("Evidencija sa unešenim-om id ne postoji!");
        }
        if (!id.matches("^-?\\d+$")) {
            throw new unallowedString("Id mora biti realan ceo broj!");
        }
    }
}
