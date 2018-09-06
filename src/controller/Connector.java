/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import data.Evidencija;
import data.Nacin;
import data.Predmet;
import data.Rok;
import data.Smer;
import data.Status;
import data.Student;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author wint4
 */
public class Connector {

    private static Connector instance = null;
    private Connection connection;
    private PreparedStatement ps;
    private final String url = "jdbc:mysql://localhost/it350_fit";
    private final String user = "root";
    private final String pass = "";
    private String query = "";
    private ObservableList<Smer> smers;
    private ObservableList<Student> students;
    private ObservableList<Predmet> predmets;
    private ObservableList<Rok> roks;
    private ObservableList<Evidencija> evidencijas;
    private ObservableList<Status> statuss;
    private ObservableList<Nacin> nacins;
    private ArrayList<String> smerovi;
    private ArrayList<String> rokovi;
    private ArrayList<String> predmeti;
    private ArrayList<String> nacini;
    private ArrayList<String> statusi;

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    private Connector() {

        try {
            connection = (Connection) DriverManager.getConnection(url, user, pass);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Sistem nije uspeo pristupiti bazi podataka!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnnection() throws SQLException {
        connection.close();
    }

    public boolean loginDatabase(String user, String pass) throws SQLException {
        query = "SELECT `username`,`password` FROM `administrator` WHERE `username`= ? AND `password` = ?";
        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet result = ps.executeQuery();
            if (!result.next()) {
                return false;
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Sistem nije uspeo pristupiti bazi podataka!");
        }
        return true;
    }

    public void getAllSmer() {
        smers = FXCollections.observableArrayList();
        query = "SELECT * FROM `smer`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String naziv = resultSet.getString(2);
                String oznaka = resultSet.getString(3);
                int espb = Integer.parseInt(resultSet.getString(4));
                Smer smer = new Smer(id, naziv, oznaka, espb);
                smers.add(smer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Smer> getSmers() {
        return smers;
    }

    public void updateSmer() {
        query = "UPDATE `smer` SET `smer_naziv` = ?, `smer_oznaka` = ?,`smer_espb`=? WHERE `smer_id` = ? ";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getSmerFrame().getNazivField().getText());
            ps.setString(2, Controller.getInstance().getSmerFrame().getOznakaField().getText());
            ps.setInt(3, Integer.parseInt(Controller.getInstance().getSmerFrame().getEspbField().getText()));
            ps.setInt(4, Integer.parseInt(Controller.getInstance().getSmerFrame().getIdField().getText()));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addSmer() {
        String query = "INSERT INTO `smer` (`smer_id`, `smer_naziv`, `smer_oznaka`, `smer_espb`) VALUES (?, ?, ?, ?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getSmerFrame().getIdField().getText()));
            ps.setString(2, Controller.getInstance().getSmerFrame().getNazivField().getText());
            ps.setString(3, Controller.getInstance().getSmerFrame().getOznakaField().getText());
            ps.setInt(4, Integer.parseInt(Controller.getInstance().getSmerFrame().getEspbField().getText()));

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteSmer() {
        String query = "DELETE FROM `smer` WHERE `smer`.`smer_id` =?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getSmerFrame().getIdField().getText()));
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudent() {
        query = "UPDATE `student` SET `smer_id`=?,`ime`=?,`prezime`=?,`datum_rodjenja`=?, `mesto_rodjenja` = ?, `staratelj` = ?,`datum_upisa`=?,`status_id`=? WHERE `student`.`indeks` = ?";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            String podaci[] = Controller.getInstance().getStudentFrame().getSmerBox().getSelectionModel().getSelectedItem().split("\\.");
            ps.setInt(1, Integer.parseInt(podaci[0]));
            ps.setString(2, Controller.getInstance().getStudentFrame().getImeField().getText());
            ps.setString(3, Controller.getInstance().getStudentFrame().getPrezimeField().getText());
            ps.setDate(4, Date.valueOf(Controller.getInstance().getStudentFrame().getDatumRodjenja().getEditor().getText()));
            ps.setString(5, Controller.getInstance().getStudentFrame().getMestoRodjenjaField().getText());
            ps.setString(6, Controller.getInstance().getStudentFrame().getStarateljField().getText());
            ps.setDate(7, Date.valueOf(Controller.getInstance().getStudentFrame().getDatumUpisa().getEditor().getText()));
            String podaci2[] = Controller.getInstance().getStudentFrame().getStatusBox().getSelectionModel().getSelectedItem().split("\\.");
            ps.setInt(8, Integer.parseInt(podaci2[0]));
            ps.setInt(9, Integer.parseInt(Controller.getInstance().getStudentFrame().getIndeksField().getText()));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addStudent() {
        String query = "INSERT INTO `student` (`indeks`, `smer_id`, `ime`, `prezime`, `datum_rodjenja`, `mesto_rodjenja`, `staratelj`, `datum_upisa`,`status_id`) "
                + "VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getStudentFrame().getIndeksField().getText()));
            String podaci[] = Controller.getInstance().getStudentFrame().getSmerBox().getSelectionModel().getSelectedItem().split("\\.");
            ps.setInt(2, Integer.parseInt(podaci[0]));
            ps.setString(3, Controller.getInstance().getStudentFrame().getImeField().getText());
            ps.setString(4, Controller.getInstance().getStudentFrame().getPrezimeField().getText());
            ps.setDate(5, Date.valueOf(Controller.getInstance().getStudentFrame().getDatumRodjenja().getEditor().getText()));
            ps.setString(6, Controller.getInstance().getStudentFrame().getMestoRodjenjaField().getText());
            ps.setString(7, Controller.getInstance().getStudentFrame().getStarateljField().getText());
            ps.setDate(8, Date.valueOf(Controller.getInstance().getStudentFrame().getDatumUpisa().getEditor().getText()));
            String podaci2[] = Controller.getInstance().getStudentFrame().getStatusBox().getSelectionModel().getSelectedItem().split("\\.");
            ps.setInt(9, Integer.parseInt(podaci2[0]));
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStudent() {
        String query = "DELETE FROM `student` WHERE `student`.`indeks` =?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getStudentFrame().getIndeksField().getText()));
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllStudents() {
        students = FXCollections.observableArrayList();
        query = "SELECT * FROM `student`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int indeks = resultSet.getInt(1);
                int id = resultSet.getInt(2);
                String ime = resultSet.getString(3);
                String prezime = resultSet.getString(4);
                String datumRodjenja = resultSet.getString(5);
                String mestoRodjenja = resultSet.getString(6);
                String staratelj = resultSet.getString(7);
                String datumUpisa = resultSet.getString(8);
                int idstatus = resultSet.getInt(9);

                String smer = "";
                String status = "";

                for (String str : smerovi) {
                    String podaci[] = str.split("\\.");
                    if (Integer.parseInt(podaci[0]) == id) {
                        smer = str;
                    }
                }
                for (String str : statusi) {
                    String podaci[] = str.split("\\.");
                    if (Integer.parseInt(podaci[0]) == idstatus) {
                        status = str;
                    }
                }

                Student student = new Student(indeks, smer, ime, prezime, datumRodjenja, mestoRodjenja, staratelj, datumUpisa, status);
                students.add(student);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void updatePredmet() {
        query = "UPDATE `predmet` SET `predmet_oznaka` = ?,`predmet_naziv` = ?, `predmet_espb` = ? WHERE `predmet_id` = ? ";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getPredmetFrame().getOznakaField().getText());
            ps.setString(2, Controller.getInstance().getPredmetFrame().getNazivField().getText());
            ps.setInt(3, Integer.parseInt(Controller.getInstance().getPredmetFrame().getEspbField().getText()));
            ps.setInt(4, Integer.parseInt(Controller.getInstance().getPredmetFrame().getIdField().getText()));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addPredmet() {
        String query = "INSERT INTO `predmet` (`predmet_id`, `predmet_oznaka`,`predmet_naziv`, `predmet_espb`) VALUES (?,?, ?, ?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getPredmetFrame().getIdField().getText()));
            ps.setString(2, Controller.getInstance().getPredmetFrame().getOznakaField().getText());
            ps.setString(3, Controller.getInstance().getPredmetFrame().getNazivField().getText());
            ps.setInt(4, Integer.parseInt(Controller.getInstance().getPredmetFrame().getEspbField().getText()));

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletePredmet() {
        String query = "DELETE FROM `predmet` WHERE `predmet`.`predmet_id` = ?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getPredmetFrame().getIdField().getText()));
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getAllPredmet() {
        predmets = FXCollections.observableArrayList();
        query = "SELECT * FROM `predmet`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String oznaka = resultSet.getString(2);
                String naziv = resultSet.getString(3);
                int espb = Integer.parseInt(resultSet.getString(4));
                Predmet predmet = new Predmet(id, oznaka, naziv, espb);
                predmets.add(predmet);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Predmet> getPredmets() {
        return predmets;
    }

    public void updateRok() {
        query = "UPDATE `ispitni_rok` SET `rok_trajanje` = ? WHERE `rok_oznaka` = ? ";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getRokFrame().getTrajanjeField().getText()));
            ps.setString(2, Controller.getInstance().getRokFrame().getOznakaField().getText());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addRok() {
        String query = "INSERT INTO `ispitni_rok` (`rok_oznaka`, `rok_trajanje`) VALUES (?,?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getRokFrame().getOznakaField().getText());
            ps.setInt(2, Integer.parseInt(Controller.getInstance().getRokFrame().getTrajanjeField().getText()));

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRok() {
        String query = "DELETE FROM `ispitni_rok` WHERE `ispitni_rok`.`rok_oznaka` = ?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getRokFrame().getOznakaField().getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllRok() {
        roks = FXCollections.observableArrayList();
        query = "SELECT * FROM `ispitni_rok`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String oznaka = resultSet.getString(1);
                int trajanje = resultSet.getInt(2);
                Rok rok = new Rok(oznaka, trajanje);
                roks.add(rok);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Rok> getRoks() {
        return roks;
    }

    public void updateEvidencija() {
        query = "UPDATE `evidencija` SET `rok_oznaka` = ?, `indeks`= ?, `nacin_polaganja_id`= ?, `predmet_id`= ?, `datum_odrzavanja`= ?, `ocena`= ?  WHERE `evidencija_id` = ? ";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getEvidencijaFrame().getRokBox().getSelectionModel().getSelectedItem());
            ps.setInt(2, Integer.parseInt(Controller.getInstance().getEvidencijaFrame().getIndeksField().getText()));
            String podaci[] = Controller.getInstance().getEvidencijaFrame().getNacinPolaganjaBox().getSelectionModel().getSelectedItem().split("\\.");
            ps.setInt(3, Integer.parseInt(podaci[0]));
            String podaci2[] = Controller.getInstance().getEvidencijaFrame().getPredmetBox().getSelectionModel().getSelectedItem().split("\\.");
            ps.setInt(4, Integer.parseInt(podaci2[0]));
            ps.setString(5, Controller.getInstance().getEvidencijaFrame().getDatumOdrzavanjaPicker().getEditor().getText());
            ps.setInt(6, Integer.parseInt(Controller.getInstance().getEvidencijaFrame().getOcena().getText()));
            ps.setInt(7, Integer.parseInt(Controller.getInstance().getEvidencijaFrame().getIdField().getText()));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addEvidencija() {
        String query = "INSERT INTO `evidencija` (`evidencija_id`,`rok_oznaka`,`indeks`,`nacin_polaganja_id`,`predmet_id`,`datum_odrzavanja`,`ocena`) VALUES (?,?,?,?,?,?,?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getEvidencijaFrame().getIdField().getText()));
            ps.setString(2, Controller.getInstance().getEvidencijaFrame().getRokBox().getSelectionModel().getSelectedItem());
            ps.setInt(3, Integer.parseInt(Controller.getInstance().getEvidencijaFrame().getIndeksField().getText()));
            String podaci[] = Controller.getInstance().getEvidencijaFrame().getNacinPolaganjaBox().getSelectionModel().getSelectedItem().split("\\.");
            ps.setInt(4, Integer.parseInt(podaci[0]));
            String podaci2[] = Controller.getInstance().getEvidencijaFrame().getPredmetBox().getSelectionModel().getSelectedItem().split("\\.");
            ps.setInt(5, Integer.parseInt(podaci2[0]));
            ps.setString(6, Controller.getInstance().getEvidencijaFrame().getDatumOdrzavanjaPicker().getEditor().getText());
            ps.setInt(7, Integer.parseInt(Controller.getInstance().getEvidencijaFrame().getOcena().getText()));

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteEvidencija() {
        String query = "DELETE FROM `evidencija` WHERE `evidencija`.`evidencija_id` = ?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getEvidencijaFrame().getIdField().getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllEvidencijas() {
        evidencijas = FXCollections.observableArrayList();
        query = "SELECT * FROM `evidencija`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                int indeks = resultSet.getInt(2);
                String rok = resultSet.getString(3);
                int idpredmet = resultSet.getInt(4);
                String predmet = "";
                for (String str : predmeti) {
                    String podaci[] = str.split("\\.");
                    if (Integer.parseInt(podaci[0]) == idpredmet) {
                        predmet = str;
                    }
                }
                String datum = resultSet.getString(5);
                int idnacin = resultSet.getInt(6);
                String nacin = "";

                for (String str : nacini) {
                    String podaci[] = str.split("\\.");
                    if (Integer.parseInt(podaci[0]) == idnacin) {
                        nacin = str;
                    }
                }
                int ocena = resultSet.getInt(7);
                Evidencija evidencija = new Evidencija(id, indeks, rok, predmet, datum, nacin, ocena);
                evidencijas.add(evidencija);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Evidencija> getEvidencijas() {
        return evidencijas;
    }

    public void refreshEvidencija() {
        Controller.getInstance().getEvidencijaFrame().getEvidencijaTableView().getItems().clear();
        evidencijas.clear();
        getAllEvidencijas();
        Controller.getInstance().getEvidencijaFrame().getEvidencijaTableView().setItems(evidencijas);
    }

    public void refreshStudents() {
        Controller.getInstance().getStudentFrame().getStudentTableView().getItems().clear();
        students.clear();
        getAllStudents();
        Controller.getInstance().getStudentFrame().getStudentTableView().setItems(students);
    }

    public void refreshSmers() {
        Controller.getInstance().getSmerFrame().getSmerTableView().getItems().clear();
        smers.clear();
        getAllSmer();
        Controller.getInstance().getSmerFrame().getSmerTableView().setItems(smers);
    }

    public void refreshPredmets() {
        Controller.getInstance().getPredmetFrame().getPredmetTableView().getItems().clear();
        predmets.clear();
        getAllPredmet();
        Controller.getInstance().getPredmetFrame().getPredmetTableView().setItems(predmets);
    }

    public void refreshRoks() {
        Controller.getInstance().getRokFrame().getRokTableView().getItems().clear();
        roks.clear();
        getAllRok();
        Controller.getInstance().getRokFrame().getRokTableView().setItems(roks);
    }

    public void updateNacin() {
        query = "UPDATE `nacin_polaganja` SET `nacin_polaganja_info` = ? WHERE `nacin_polaganja_id` = ? ";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getNacinFrame().getInfoField().getText());
            ps.setInt(2, Integer.parseInt(Controller.getInstance().getNacinFrame().getIdField().getText()));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshNacins() {
        Controller.getInstance().getNacinFrame().getNacinTableView().getItems().clear();
        nacins.clear();
        getAllNacin();
        Controller.getInstance().getNacinFrame().getNacinTableView().setItems(nacins);
    }

    public void addNacin() {
        String query = "INSERT INTO `nacin_polaganja` (`nacin_polaganja_id`, `nacin_polaganja_info`) VALUES (?,?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getNacinFrame().getIdField().getText()));
            ps.setString(2, Controller.getInstance().getNacinFrame().getInfoField().getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteNacin() {
        String query = "DELETE FROM `nacin_polaganja` WHERE `nacin_polaganja`.`nacin_polaganja_id` = ?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getNacinFrame().getIdField().getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStatus() {
        query = "UPDATE `status` SET `status_opis` = ? WHERE `status_id` = ? ";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getStatusFrame().getInfoField().getText());
            ps.setInt(2, Integer.parseInt(Controller.getInstance().getStatusFrame().getIdField().getText()));

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshStatuss() {
        Controller.getInstance().getStatusFrame().getStatusTableView().getItems().clear();
        statuss.clear();
        getAllStatus();
        Controller.getInstance().getStatusFrame().getStatusTableView().setItems(statuss);
    }

    public void addStatus() {
        String query = "INSERT INTO `status` (`status_id`, `status_opis`) VALUES (?,?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getStatusFrame().getIdField().getText()));
            ps.setString(2, Controller.getInstance().getStatusFrame().getInfoField().getText());

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStatus() {
        String query = "DELETE FROM `status` WHERE `status`.`status_id` = ?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getStatusFrame().getIdField().getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllStatus() {
        statuss = FXCollections.observableArrayList();
        query = "SELECT * FROM `status`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String opis = resultSet.getString(2);
                Status status = new Status(id, opis);
                statuss.add(status);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Status> getStatuss() {
        return statuss;
    }

    public void getAllNacin() {
        nacins = FXCollections.observableArrayList();
        query = "SELECT * FROM `nacin_polaganja`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String opis = resultSet.getString(2);
                Nacin nacin = new Nacin(id, opis);
                nacins.add(nacin);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Nacin> getNacins() {
        return nacins;
    }

    public void ucitajSmerove() {
        smerovi = new ArrayList<>();
        query = "SELECT `smer_id`,`smer_naziv` FROM `smer`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String naziv = resultSet.getString(2);
                String str = id + "." + naziv;
                smerovi.add(str);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> getSmerovi() {
        return smerovi;
    }

    public void ucitajRokove() {
        rokovi = new ArrayList<>();
        query = "SELECT `rok_oznaka` FROM `ispitni_rok`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String str = resultSet.getString(1);
                rokovi.add(str);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getRokovi() {
        return rokovi;
    }

    public void ucitajPredmete() {
        predmeti = new ArrayList<>();
        query = "SELECT `predmet_id`,`predmet_oznaka` FROM `predmet` ";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String oznaka = resultSet.getString(2);
                String str = id + "." + oznaka;
                predmeti.add(str);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getPredmeti() {
        return predmeti;
    }

    public void ucitajNacine() {
        nacini = new ArrayList<>();
        query = "SELECT `nacin_polaganja_id`,`nacin_polaganja_info` FROM `nacin_polaganja` ";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String info = resultSet.getString(2);
                String str = id + "." + info;
                nacini.add(str);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getNacini() {
        return nacini;
    }

    public void ucitajStatuse() {
        statusi = new ArrayList<>();
        query = "SELECT `status_id`,`status_opis`FROM `status`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String info = resultSet.getString(2);
                String str = id + "." + info;
                statusi.add(str);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getStatusi() {
        return statusi;
    }

}
