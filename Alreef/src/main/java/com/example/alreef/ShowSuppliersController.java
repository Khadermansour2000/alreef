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

public class ShowSuppliersController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<SupplierRecord, Integer> ID;
    @FXML
    private TableColumn<SupplierRecord, String> name;
    @FXML
    private TableColumn<SupplierRecord, String> address;
    @FXML
    private TableColumn<SupplierRecord, Integer> Phone;

    @FXML
    private TableView<SupplierRecord> table;

    @FXML
    private Button home;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<SupplierRecord> list = getData();
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        table.setItems(list);
    }

    public static ObservableList<SupplierRecord> getData() {
        connecter connecter = new connecter();
        String SQL = "select * from supplier";
        ObservableList<SupplierRecord> list = FXCollections.observableArrayList();

        try {
            Connection con = connecter.connectDB();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new SupplierRecord(Integer.parseInt(rs.getString("Sid")), rs.getString("Sname"),
                        rs.getString("Saddress"), Integer.parseInt(rs.getString("Sphone"))));
                //     System.out.println(rs.getString("Sid")+ rs.getString("Sname")+
                //           rs.getString("Saddress")+rs.getString("Sphone"));
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
