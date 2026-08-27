package main.java.com.kafusoft.abarroteria.kinal.service;

import main.java.com.kafusoft.abarroteria.kinal.dto.request.LoginDTORequest;
import main.java.com.kafusoft.abarroteria.kinal.dto.response.LoginDTOResponse;
import main.java.com.kafusoft.abarroteria.kinal.repository.AuthRepository;

public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LoginDTOResponse login(LoginDTORequest request) {

        if (request == null) {
            throw new RuntimeException("Los datos están vacíos.");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {

            throw new RuntimeException( "El correo electrónico está vacío.");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {

            throw new RuntimeException("La contraseña está vacía.");
        }

        LoginDTOResponse response = authRepository.findUserByEmail(request);

        if (response == null) {
            return null;
        }

        if (response.getContrasenaHash() == null || response.getContrasenaHash().isBlank()) {

            throw new RuntimeException("El usuario no tiene una contraseña registrada.");
        }

        if (request.getPassword().equals(response.getContrasenaHash())) {

            return response;
        }

        return null;
    }
}