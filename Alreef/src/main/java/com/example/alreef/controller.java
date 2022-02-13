package com.example.alreef;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class controller {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void goToAdd() throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddMeterialScene.fxml"));
        stage = ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToAddItem() throws IOException  {
        root = FXMLLoader.load(getClass().getResource("additem.fxml"));
        stage = ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToDeleteItem() throws IOException  {
        root = FXMLLoader.load(getClass().getResource("Deleteitem1.fxml"));
        stage = ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToUpdateItem() throws IOException  {
        root = FXMLLoader.load(getClass().getResource("updateitem.fxml"));
        stage = ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToShowMeterials() throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShowMeterials.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDeleteMeterials() throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteMeterial.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToReportByMaterial() throws IOException {
        root = FXMLLoader.load(getClass().getResource("ReportByMaterial.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goToNewOreder() throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreateOrder.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToAddSupplier() throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddSupplierScene.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToShowsuppliers() throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShowSuppliers.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToDeleteSuppplier() throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteSupplierScene.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void goShowOrders() throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShowOrders.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToPursheseReport() throws IOException {
        root = FXMLLoader.load(getClass().getResource("SupplierAccountStatement.fxml"));
        stage =ReportRecord.stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
