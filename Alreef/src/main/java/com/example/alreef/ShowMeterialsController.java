package com.example.alreef;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowMeterialsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<MeterialRecord, Integer> MID;
    @FXML
    private TableColumn<MeterialRecord, String> MName;
    @FXML
    private TableColumn<MeterialRecord, Double> MPrice;
    @FXML
    private TableView<MeterialRecord> table;
    @FXML
    private Button home;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<MeterialRecord> list = getData();
        MID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        MName.setCellValueFactory(new PropertyValueFactory<>("name"));
        MPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(list);
    }

    public static ObservableList<MeterialRecord> getData() {
        connecter connecter = new connecter();
        String SQL = "select * from matrial";
        ObservableList<MeterialRecord> list = FXCollections.observableArrayList();

        try {
            Connection con = connecter.connectDB();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new MeterialRecord(Integer.parseInt(rs.getString("Id")), rs.getString("Mname"),
                        Double.parseDouble(rs.getString("price"))));
                //System.out.println(rs.getString("Id")+rs.getString("Mname")+rs.getString("price"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void goHome(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
