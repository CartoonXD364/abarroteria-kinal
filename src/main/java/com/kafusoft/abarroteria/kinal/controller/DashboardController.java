package main.java.com.kafusoft.abarroteria.kinal.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.kafusoft.abarroteria.kinal.model.Producto;
import main.java.com.kafusoft.abarroteria.kinal.service.DashboardService;
import main.java.com.kafusoft.abarroteria.kinal.util.SceneManager;

public class DashboardController implements Initializable {
    
    private final DashboardService dashboardService;
    private final SceneManager sceneManager;
    
    @FXML
    private TableView<Producto> tableProducto;
    
    @FXML
    private TableColumn<Producto, String> tableColumnIdProducto;
    
    @FXML
    private TableColumn<Producto, String> tableColumnNombreProducto;
    
    @FXML
    private TableColumn<Producto, Integer> tableColumnStock;
    
    @FXML
    private TableColumn<Producto, BigDecimal> tableColumnPrecio;
    
    public DashboardController(
            DashboardService dashboardService,
            SceneManager sceneManager){
        
        this.dashboardService = dashboardService;
        this.sceneManager = sceneManager;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleLoadDataTableView();
    }
    
    @FXML
    private void handleLoadDataTableView(){
        
        tableColumnIdProducto.setCellValueFactory(
                new PropertyValueFactory<>("idProducto"));
        
        tableColumnNombreProducto.setCellValueFactory(
                new PropertyValueFactory<>("nombreProducto"));
        
        tableColumnStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));
        
        tableColumnPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio"));
        
        tableProducto.setItems(dashboardService.findProducto());
    }
    
    @FXML
    private void handleEliminarProducto(){
        
        Producto productoSeleccionado =
                tableProducto.getSelectionModel().getSelectedItem();
        
        if(productoSeleccionado == null){
            
            sceneManager.showAlertInfo(
                    "Eliminar producto",
                    "No hay un producto seleccionado",
                    "Selecciona un producto de la tabla para poder eliminarlo.",
                    Alert.AlertType.WARNING
            );
            
            return;
        }
        
        Alert confirmacion =
                new Alert(Alert.AlertType.CONFIRMATION);
        
        confirmacion.initOwner(
                tableProducto.getScene().getWindow());
        
        confirmacion.setTitle("Eliminar producto");
        
        confirmacion.setHeaderText(
                "¿Deseas eliminar este producto?");
        
        confirmacion.setContentText(
                "ID: " + productoSeleccionado.getIdProducto()
                + "\nNombre: "
                + productoSeleccionado.getNombreProducto()
        );
        
        confirmacion.showAndWait().ifPresent(respuesta -> {
            
            if(respuesta == ButtonType.OK){
                
                try{
                    
                    boolean eliminado =
                            dashboardService.eliminarProducto(
                                    productoSeleccionado.getIdProducto()
                            );
                    
                    if(eliminado){
                        
                        tableProducto.getItems()
                                .remove(productoSeleccionado);
                        
                        sceneManager.showAlertInfo(
                                "Producto eliminado",
                                "Eliminación exitosa",
                                "El producto se eliminó correctamente.",
                                Alert.AlertType.INFORMATION
                        );
                        
                    }else{
                        
                        sceneManager.showAlertInfo(
                                "No se eliminó el producto",
                                "Producto no encontrado",
                                "El producto ya no existe en la base de datos.",
                                Alert.AlertType.WARNING
                        );
                    }
                    
                }catch(Exception e){
                    
                    sceneManager.showAlertInfo(
                            "Error al eliminar",
                            "No se pudo eliminar el producto",
                            "Ocurrió un error al eliminar el producto de la base de datos.",
                            Alert.AlertType.ERROR
                    );
                }
            }
        });
    }
    
}