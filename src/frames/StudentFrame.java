/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import data.Student;
import actions.BackAction;
import controller.Connector;
import exceptions.emptyString;
import exceptions.unallowedString;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class StudentFrame extends HBox {

    private final TextField indeksField = new TextField();
    private final ComboBox<String> smerBox = new ComboBox<>();
    private final TextField imeField = new TextField();
    private final TextField prezimeField = new TextField();
    private final DatePicker datumRodjenja = new DatePicker();
    private final TextField mestoRodjenjaField = new TextField();
    private final TextField starateljField = new TextField();
    private final DatePicker datumUpisa = new DatePicker();
    private final ComboBox<String> statusBox = new ComboBox<>();
    private final ArrayList<String> status = new ArrayList<>();

    private final Label indeksLabel = new Label("Indeks: ");
    private final Label smerLabel = new Label("Smer: ");
    private final Label imeLabel = new Label("Ime: ");
    private final Label prezimeLabel = new Label("Prezime: ");
    private final Label datumLabel = new Label("Datum rodjenja: ");
    private final Label mestoRodjenjaLabel = new Label("Mesto rodjenja: ");
    private final Label starateljLabel = new Label("Staratelj: ");
    private final Label datumUpisaLabel = new Label("Datum upisa: ");
    private final Label statusLabel = new Label("Status: ");

    private final Text caption = new Text("Studenti");
    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);
    private TableView<Student> studentTableView;
    private final Button backButton = new Button("Back");

    public StudentFrame() {

        statusBox.getItems().addAll(status);
        statusBox.getSelectionModel().selectFirst();
        indeksLabel.setTextFill(Color.web("#ffffff"));
        smerLabel.setTextFill(Color.web("#ffffff"));
        imeLabel.setTextFill(Color.web("#ffffff"));
        prezimeLabel.setTextFill(Color.web("#ffffff"));
        datumLabel.setTextFill(Color.web("#ffffff"));
        mestoRodjenjaLabel.setTextFill(Color.web("#ffffff"));
        starateljLabel.setTextFill(Color.web("#ffffff"));
        datumUpisaLabel.setTextFill(Color.web("#ffffff"));
        statusLabel.setTextFill(Color.web("#ffffff"));
        caption.setFill(Color.BLACK);
        caption.setStyle("-fx-font: 35 ariel;");

        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));
        gridPane.add(indeksLabel, 0, 0);
        gridPane.add(indeksField, 1, 0);
        gridPane.add(smerLabel, 0, 1);
        gridPane.add(smerBox, 1, 1);
        smerBox.setMinWidth(170);
        gridPane.add(imeLabel, 0, 2);
        gridPane.add(imeField, 1, 2);
        gridPane.add(prezimeLabel, 0, 3);
        gridPane.add(prezimeField, 1, 3);
        gridPane.add(datumLabel, 0, 4);
        gridPane.add(datumRodjenja, 1, 4);
        gridPane.add(mestoRodjenjaLabel, 0, 5);
        gridPane.add(mestoRodjenjaField, 1, 5);
        gridPane.add(starateljLabel, 0, 6);
        gridPane.add(starateljField, 1, 6);
        gridPane.add(datumUpisaLabel, 0, 7);
        gridPane.add(datumUpisa, 1, 7);
        gridPane.add(statusLabel, 0, 8);
        gridPane.add(statusBox, 1, 8);
        statusBox.setMinWidth(190);
        datumRodjenja.setMinWidth(190);
        datumUpisa.setMinWidth(190);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        insertButton.setMinWidth(60);
        updateButton.setMinWidth(60);
        deleteButton.setMinWidth(60);
        backButton.setMinWidth(60);
        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        vBox.getChildren().addAll(caption, gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBox);

        String pattern = "yyyy-MM-dd";

        datumRodjenja.setPromptText(pattern.toLowerCase());
        datumRodjenja.setConverter(new StringConverter<LocalDate>() {
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

        datumUpisa.setPromptText(pattern.toLowerCase());
        datumUpisa.setConverter(new StringConverter<LocalDate>() {
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

        TableColumn<Student, Integer> indeksColumn = new TableColumn<>("Indeks");
        indeksColumn.setCellValueFactory(new PropertyValueFactory<>("indeks"));
        TableColumn<Student, String> smerColumn = new TableColumn<>("Smer");
        smerColumn.setCellValueFactory(new PropertyValueFactory<>("smer"));
        smerColumn.setMinWidth(150);
        TableColumn<Student, String> imeColumn = new TableColumn<>("Ime");
        imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        TableColumn<Student, String> prezimeColumn = new TableColumn<>("Prezime");
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        TableColumn<Student, Date> datumRodjenjaColumn = new TableColumn<>("Datum rodjenja");
        datumRodjenjaColumn.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
        datumRodjenjaColumn.setMinWidth(100);
        TableColumn<Student, String> mestoRodnjaColumn = new TableColumn<>("Mesto rodjenja");
        mestoRodnjaColumn.setCellValueFactory(new PropertyValueFactory<>("mestoRodjenja"));
        mestoRodnjaColumn.setMinWidth(100);
        TableColumn<Student, String> starateljColumn = new TableColumn<>("Staratelj");
        starateljColumn.setCellValueFactory(new PropertyValueFactory<>("staratelj"));
        TableColumn<Student, Date> datumUpisaColumn = new TableColumn<>("Datum upisa");
        datumUpisaColumn.setCellValueFactory(new PropertyValueFactory<>("datumUpisa"));
        TableColumn<Student, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setMinWidth(110);
        studentTableView = new TableView<>();
        studentTableView.setMinWidth(870);
        studentTableView.getColumns().addAll(indeksColumn, smerColumn, imeColumn, prezimeColumn, datumRodjenjaColumn, mestoRodnjaColumn, starateljColumn, datumUpisaColumn, statusColumn);
        this.getChildren().addAll(studentTableView);
        backButton.setOnAction(new BackAction());
        updateButton.setOnAction(e -> {
            try {
                checkU(indeksField.getText(), smerBox.getValue(), imeField.getText(), prezimeField.getText(),
                        datumRodjenja.getEditor().getText(), mestoRodjenjaField.getText(), starateljField.getText(),
                        datumUpisa.getEditor().getText(), statusBox.getValue());
                controller.Connector.getInstance().updateStudent();
                controller.Connector.getInstance().refreshStudents();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        insertButton.setOnAction(e -> {
            try {
                checkI(indeksField.getText(), smerBox.getValue(), imeField.getText(), prezimeField.getText(),
                        datumRodjenja.getEditor().getText(), mestoRodjenjaField.getText(), starateljField.getText(),
                        datumUpisa.getEditor().getText(), statusBox.getValue());
                controller.Connector.getInstance().addStudent();
                controller.Connector.getInstance().refreshStudents();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        deleteButton.setOnAction((event) -> {
            try {
                checkD(indeksField.getText());
                controller.Connector.getInstance().deleteStudent();
                controller.Connector.getInstance().refreshStudents();
            } catch (emptyString | unallowedString ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        studentTableView.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Student rowData = row.getItem();
                    indeksField.setText(rowData.getIndeks() + "");
                    smerBox.setValue(rowData.getSmer());
                    imeField.setText(rowData.getIme());
                    prezimeField.setText(rowData.getPrezime());
                    datumRodjenja.getEditor().setText(rowData.getDatumRodjenja());
                    mestoRodjenjaField.setText(rowData.getMestoRodjenja());
                    starateljField.setText(rowData.getStaratelj());
                    datumUpisa.getEditor().setText(rowData.getDatumUpisa());
                    statusBox.setValue(rowData.getStatus());
                }
            });
            return row;
        });
    }

    public TextField getIndeksField() {
        return indeksField;
    }

    public TextField getImeField() {
        return imeField;
    }

    public TextField getPrezimeField() {
        return prezimeField;
    }

    public DatePicker getDatumRodjenja() {
        return datumRodjenja;
    }

    public TextField getMestoRodjenjaField() {
        return mestoRodjenjaField;
    }

    public TextField getStarateljField() {
        return starateljField;
    }

    public DatePicker getDatumUpisa() {
        return datumUpisa;
    }

    public TableView<Student> getStudentTableView() {
        return studentTableView;
    }

    public ComboBox<String> getSmerBox() {
        return smerBox;
    }

    public ComboBox<String> getStatusBox() {
        return statusBox;

    }

    private void checkI(String indeks, String smer, String ime, String prezime,
            String datum_rodjenja, String mesto_rodjenja, String staratelj,
            String datum_upisa, String status) throws emptyString, unallowedString {

        if (indeks.isEmpty()) {
            throw new emptyString("Unesite indeks studenta!");
        } else if (smer.isEmpty()) {
            throw new emptyString("Unesite smer studenta!");
        } else if (ime.isEmpty()) {
            throw new emptyString("Unesite ime studenta!");
        } else if (prezime.isEmpty()) {
            throw new emptyString("Unesite prezime studenta!");
        } else if (datum_rodjenja.isEmpty()) {
            throw new emptyString("Unesite datum rodjenja!");
        } else if (datum_upisa.isEmpty()) {
            throw new emptyString("Izaberite datum upisa!");
        } else if (status.isEmpty()) {
            throw new emptyString("Unesite status studenta!");
        }

        for (Student s : Connector.getInstance().getStudents()) {
            if ((s.getIndeks() + "").equals(indeks)) {
                throw new unallowedString("Student sa unešenim indeksom već postoji!");
            }
        }

        if (Date.valueOf(datum_rodjenja).after(Date.valueOf(datum_upisa))) {
            throw new unallowedString("Datum nije validan!");
        }

        for (Student s : Connector.getInstance().getStudents()) {
            if (s.getSmer().equals(smer) && s.getIme().equals(ime) && s.getPrezime().equals(prezime)
                    && s.getDatumRodjenja().equals(datum_rodjenja) && s.getDatumUpisa().equals(datum_upisa)
                    && s.getMestoRodjenja().equals(mesto_rodjenja) && s.getStaratelj().equals(staratelj) && s.getStatus().equals(status)) {
                throw new unallowedString("Student već postoji, samo poseduje drugi indeks!");
            }
        }

        if (!indeks.matches("^-?\\d+$")) {
            throw new unallowedString("Indeks mora biti realan ceo broj!");
        }
    }

    private void checkU(String indeks, String smer, String ime, String prezime,
            String datum_rodjenja, String mesto_rodjenja, String staratelj,
            String datum_upisa, String status) throws emptyString, unallowedString {

        if (indeks.isEmpty()) {
            throw new emptyString("Unesite indeks studenta!");
        } else if (smer.isEmpty()) {
            throw new emptyString("Unesite smer studenta!");
        } else if (ime.isEmpty()) {
            throw new emptyString("Unesite ime studenta!");
        } else if (prezime.isEmpty()) {
            throw new emptyString("Unesite prezime studenta!");
        } else if (datum_rodjenja.isEmpty()) {
            throw new emptyString("Unesite datum rodjenja!");
        } else if (datum_upisa.isEmpty()) {
            throw new emptyString("Izaberite datum upisa!");
        } else if (status.isEmpty()) {
            throw new emptyString("Unesite status studenta!");
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

        if (Date.valueOf(datum_rodjenja).after(Date.valueOf(datum_upisa))) {
            throw new unallowedString("Datum nije validan!");
        }

        if (!indeks.matches("^-?\\d+$")) {
            throw new unallowedString("Indeks mora biti realan ceo broj!");
        }
    }

    private void checkD(String indeks) throws emptyString, unallowedString {
        if (indeks.isEmpty()) {
            throw new emptyString("Unesite indeks studenta!");
        }
        boolean pom = true;
        for (Student s : Connector.getInstance().getStudents()) {
            if (((s.getIndeks() + "").equals(indeks))) {
                pom = true;
            }
        }
        if (!pom) {
            throw new unallowedString("Evidencija sa unešenim-om id ne postoji!");
        }
        if (!indeks.matches("^-?\\d+$")) {
            throw new unallowedString("Indeks mora biti realan ceo broj!");
        }
    }
}
