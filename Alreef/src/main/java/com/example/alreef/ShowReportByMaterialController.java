package com.example.alreef;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

public class ShowReportByMaterialController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<StatementLineRecord, Integer> Qant;

    @FXML
    private Label fromdate;

    @FXML
    private Label mID;

    @FXML
    private Label mname;

    @FXML
    private TableColumn<StatementLineRecord, Double> mprice;

    @FXML
    private TableColumn<StatementLineRecord, Double> mtotal;

    @FXML
    private TableColumn<StatementLineRecord, String> odate;

    @FXML
    private TableColumn<StatementLineRecord, Integer> orderno;

    @FXML
    private Label ortotal;

    @FXML
    private TableColumn<StatementLineRecord, Integer> sid;

    @FXML
    private TableView<StatementLineRecord> table;

    @FXML
    private Label todate;
    @FXML
    void goBack(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SupplierAccountStatement.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToHome(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportRecord.ototal = 0.0;
        ObservableList<StatementLineRecord> list = getData();

        ortotal.setText(String.valueOf(ReportRecord.ototal));
        mname.setText(ReportRecord.name);
        mID.setText(ReportRecord.ID);
        fromdate.setText(ReportRecord.fdate);
        todate.setText(ReportRecord.tdate);

        orderno.setCellValueFactory(new PropertyValueFactory<>("Number"));
        odate.setCellValueFactory(new PropertyValueFactory<>("date"));
        sid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Qant.setCellValueFactory(new PropertyValueFactory<>("Qan"));
        mtotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        mprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(list);

    }

    public static ObservableList<StatementLineRecord> getData() {
        connecter connecter = new connecter();
        System.out.println("select * from per_line , per_order where per_order.ODate between '" +ReportRecord.fdate + "' AND '"+ReportRecord.tdate + "' and per_line.Id="+ReportRecord.ID);

        String SQL = "select * from per_line , per_order where per_order.ODate between '" +ReportRecord.fdate + "' AND '"+ReportRecord.tdate + "' and per_line.Id="+ReportRecord.ID;
        // String SQL2 = null;
        ObservableList<StatementLineRecord> list = FXCollections.observableArrayList();

        try {
           /* String ordernumber = new String();
            String orderdate = new String();*/
            Connection con = connecter.connectDB();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            /*PreparedStatement st2 = null;
            ResultSet rs2 = null;
            while (rs.next()) {
                ordernumber = rs.getString("NO_order");
                orderdate = rs.getString("ODate");
                SQL2 = "select * from per_line where NO_order= " + ordernumber;
                st2 = con.prepareStatement(SQL2);
                rs2 = st2.executeQuery();*/

            while (rs.next()) {
                list.add(new StatementLineRecord(Integer.parseInt(rs.getString("NO_order")),
                        rs.getString("ODate"),
                        Integer.parseInt(rs.getString("Sid")),
                        Double.parseDouble(rs.getString("price")),
                        Integer.parseInt(rs.getString("Qan"))));


                ReportRecord.ototal = ReportRecord.ototal +
                        Double.parseDouble(rs.getString("price")) *
                                Integer.parseInt(rs.getString("Qan"));

            }

            //  }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
