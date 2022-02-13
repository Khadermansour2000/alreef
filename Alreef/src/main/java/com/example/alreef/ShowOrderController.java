package com.example.alreef;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowOrderController implements Initializable {
    @FXML
    private TableColumn<OrderRecord, Integer> ID;

    @FXML
    private TableColumn<OrderRecord, Integer> Onumber;

    @FXML
    private TableColumn<OrderRecord, String> date;

    @FXML
    private Button home;

    @FXML
    private TableView<OrderRecord> table;

    @FXML
    private TableColumn<OrderRecord, Double> total;
    @FXML
    private TextField inputid;
    @FXML
    private DatePicker inputdate;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private static String SQL="select * from per_order";


    public void goHome(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<OrderRecord> list = getData();
        Onumber.setCellValueFactory(new PropertyValueFactory<>("Onumber"));
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));

        table.setItems(list);
    }

    public static ObservableList<OrderRecord> getData() {
        connecter connecter = new connecter();
        ObservableList<OrderRecord> list = FXCollections.observableArrayList();

        try {
            Connection con = connecter.connectDB();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new OrderRecord(Integer.parseInt(rs.getString("NO_order")),
                        Integer.parseInt(rs.getString("Sid")),
                        rs.getString("ODate"),
                        Double.parseDouble(rs.getString("Total"))));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void setFindId() {
        SQL="select * from per_order where Sid="+ inputid.getText();
        URL url = null;
        ResourceBundle resourceBundle = null;
        initialize( url,  resourceBundle);
    }
    public void setFindDate() {
        SQL="select * from per_order where ODate='"+ inputdate.getValue()+"'";
        URL url = null;
        ResourceBundle resourceBundle = null;
        initialize( url,  resourceBundle);
    }
}
