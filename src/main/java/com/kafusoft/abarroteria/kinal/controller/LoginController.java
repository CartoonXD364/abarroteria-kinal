package main.java.com.kafusoft.abarroteria.kinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.kafusoft.abarroteria.kinal.dto.request.LoginDTORequest;
import main.java.com.kafusoft.abarroteria.kinal.dto.response.LoginDTOResponse;
import main.java.com.kafusoft.abarroteria.kinal.service.AuthService;
import main.java.com.kafusoft.abarroteria.kinal.util.SceneManager;

public class LoginController implements Initializable {

    private final AuthService authService;
    private final SceneManager sceneManager;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private PasswordField txtFieldPassword;

    public LoginController(
            AuthService authService,
            SceneManager sceneManager) {

        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización
    }

    @FXML
    public void handleLogin()throws Exception {

    try {

        // Validar correo
        if (txtFieldEmail.getText() == null
                || txtFieldEmail.getText().isBlank()) {

            sceneManager.showAlertInfo(
                    "Campo vacío",
                    "El correo electrónico es obligatorio",
                    "Ingresa tu correo electrónico",
                    Alert.AlertType.INFORMATION
            );

            return;
        }

        // Validar contraseña
        if (txtFieldPassword.getText() == null
                || txtFieldPassword.getText().isBlank()) {

            sceneManager.showAlertInfo(
                    "Campo vacío",
                    "La contraseña es obligatoria",
                    "Ingresa tu contraseña",
                    Alert.AlertType.INFORMATION
            );

            return;
        }

        // Crear solicitud
        LoginDTORequest request = new LoginDTORequest(
                txtFieldEmail.getText().trim(),
                txtFieldPassword.getText()
        );

        // Intentar iniciar sesión
        LoginDTOResponse response = authService.login(request);

        // Credenciales incorrectas
        if (response == null) {

            sceneManager.showAlertInfo(
                    "Inicio de sesión",
                    "Credenciales incorrectas",
                    "El correo o la contraseña no son correctos",
                    Alert.AlertType.ERROR
            );

            return;
        }

        // Login correcto
        System.out.println(
                "Usuario: "
                + response.getNombre()
                + " "
                + response.getApellido()
                + " | Rol: "
                + response.getNombreRol()
        );

        sceneManager.showAlertInfo(
                "Bienvenido " + response.getNombre(),
                "Es bueno verte",
                "Inicio de sesión correcto",
                Alert.AlertType.CONFIRMATION
        );

    } catch (Exception e) {

        sceneManager.showAlertInfo(
                "Ocurrió un problema",
                "Error",
                "No se ha podido iniciar sesión",
                Alert.AlertType.ERROR
        );

        return;
    }

    // Abrir Dashboard después de que el login haya sido exitoso
    sceneManager.showDashboardView();
}
}

//Quitar los comentarios de Chat, problema de alerta solucionado