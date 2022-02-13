package com.example.alreef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierAccountStatmentController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField ID;

    @FXML
    private DatePicker fdate;

    @FXML
    private Label name;

    @FXML
    private DatePicker tdate;

    @FXML
    void goToHome(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void goToShow(ActionEvent e) throws IOException {
        ReportRecord.ID = ID.getText();
        ReportRecord.fdate = String.valueOf(fdate.getValue());
        ReportRecord.tdate = String.valueOf(tdate.getValue());


        root = FXMLLoader.load(getClass().getResource("ShowStatment.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showName(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SQL = "select * from supplier where Sid=" + ID.getText();
        connecter connecter = new connecter();
        Connection con = connecter.connectDB();
        PreparedStatement st = con.prepareStatement(SQL);
        ResultSet rs = st.executeQuery();
        String SNAME = new String();
        while (rs.next()) {
            SNAME = rs.getString("Sname");
        }
        name.setText(SNAME);
        ReportRecord.name=SNAME;
    }

}
