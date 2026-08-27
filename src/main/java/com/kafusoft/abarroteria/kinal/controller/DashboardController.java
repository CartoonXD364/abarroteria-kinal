package main.java.com.kafusoft.abarroteria.kinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import main.java.com.kafusoft.abarroteria.kinal.service.AuthService;
import main.java.com.kafusoft.abarroteria.kinal.service.usuario.UsuarioService;
import main.java.com.kafusoft.abarroteria.kinal.util.SceneManager;

public class DashboardController implements Initializable {
    
    private UsuarioService usuarioService;
    private SceneManager sceneManager;
    
    public DashboardController(UsuarioService usuariosService, SceneManager sceneManager){
        this.usuarioService = usuariosService;
        this.sceneManager = sceneManager;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void handleDashboardView()throws Exception{
        
        
        
    }
    
}
