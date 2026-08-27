package main.java.com.kafusoft.abarroteria.kinal.service.usuario;

import main.java.com.kafusoft.abarroteria.kinal.repository.usuario.UsuarioRepository;

/**
 *
 * @author Dell
 */
public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    
}
