/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Account;
import frames.EvidencijaFrame;
import frames.LoginFrame;
import frames.MainFrame;
import frames.NacinFrame;
import frames.OstaloFrame;
import frames.PredmetFrame;
import frames.RokFrame;
import frames.SmerFrame;
import frames.StatusFrame;
import frames.StudentFrame;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nikol
 */
public class Controller {

    private static Controller instance = null;
    private Stage primaryStage;
    private Account account;

    private Scene loginScene;
    private LoginFrame loginFrame;
    private Scene mainScene;
    private MainFrame mainFrame;
    private Scene smerScene;
    private SmerFrame smerFrame;
    private Scene studentScene;
    private StudentFrame studentFrame;
    private Scene predmetScene;
    private PredmetFrame predmetFrame;
    private Scene rokScene;
    private RokFrame rokFrame;
    private Scene evidencijaScene;
    private EvidencijaFrame evidencijaFrame;
    private Scene ostaloScene;
    private OstaloFrame ostaloFrame;
    private Scene nacinScene;
    private NacinFrame nacinFrame;
    private Scene statusScene;
    private StatusFrame statusFrame;

    private Controller() {
        loginScene = null;
        loginFrame = null;
        mainScene = null;
        mainFrame = null;
        smerScene = null;
        smerFrame = null;
        studentScene = null;
        studentFrame = null;
        predmetScene = null;
        predmetFrame = null;
        rokScene = null;
        rokFrame = null;
        evidencijaScene = null;
        evidencijaFrame = null;
        nacinScene = null;
        nacinFrame = null;
        statusScene = null;
        statusFrame = null;
        ostaloScene=null;
        ostaloFrame=null;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public Scene getSmerScene() {
        return smerScene;
    }

    public void setSmerScene(Scene smerScene) {
        this.smerScene = smerScene;
    }

    public SmerFrame getSmerFrame() {
        return smerFrame;
    }

    public void setSmerFrame(SmerFrame smerFrame) {
        this.smerFrame = smerFrame;
    }

    public Scene getStudentScene() {
        return studentScene;
    }

    public void setStudentScene(Scene studentScene) {
        this.studentScene = studentScene;
    }

    public StudentFrame getStudentFrame() {
        return studentFrame;
    }

    public void setStudentFrame(StudentFrame studentFrame) {
        this.studentFrame = studentFrame;
    }

    public Scene getPredmetScene() {
        return predmetScene;
    }

    public PredmetFrame getPredmetFrame() {
        return predmetFrame;
    }

    public void setPredmetScene(Scene predmetScene) {
        this.predmetScene = predmetScene;
    }

    public void setPredmetFrame(PredmetFrame predmetFrame) {
        this.predmetFrame = predmetFrame;
    }

    public Scene getRokScene() {
        return rokScene;
    }

    public void setRokScene(Scene rokScene) {
        this.rokScene = rokScene;
    }

    public RokFrame getRokFrame() {
        return rokFrame;
    }

    public void setRokFrame(RokFrame rokFrame) {
        this.rokFrame = rokFrame;
    }

    public Scene getEvidencijaScene() {
        return evidencijaScene;
    }

    public void setEvidencijaScene(Scene evidencijaScene) {
        this.evidencijaScene = evidencijaScene;
    }

    public EvidencijaFrame getEvidencijaFrame() {
        return evidencijaFrame;
    }

    public void setEvidencijaFrame(EvidencijaFrame evidencijaFrame) {
        this.evidencijaFrame = evidencijaFrame;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Scene getNacinScene() {
        return nacinScene;
    }

    public void setNacinScene(Scene nacinScene) {
        this.nacinScene = nacinScene;
    }

    public NacinFrame getNacinFrame() {
        return nacinFrame;
    }

    public void setNacinFrame(NacinFrame nacinFrame) {
        this.nacinFrame = nacinFrame;
    }

    public Scene getStatusScene() {
        return statusScene;
    }

    public void setStatusScene(Scene statusScene) {
        this.statusScene = statusScene;
    }

    public StatusFrame getStatusFrame() {
        return statusFrame;
    }

    public void setStatusFrame(StatusFrame statusFrame) {
        this.statusFrame = statusFrame;
    }

    public Scene getOstaloScene() {
        return ostaloScene;
    }

    public void setOstaloScene(Scene ostaloScene) {
        this.ostaloScene = ostaloScene;
    }

    public OstaloFrame getOstaloFrame() {
        return ostaloFrame;
    }

    public void setOstaloFrame(OstaloFrame ostaloFrame) {
        this.ostaloFrame = ostaloFrame;
    }

}
