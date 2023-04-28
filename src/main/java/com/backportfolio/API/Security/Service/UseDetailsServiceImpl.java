
package com.backportfolio.API.Security.Service;

import com.backportfolio.API.Security.Entity.Usuario;
import com.backportfolio.API.Security.Entity.UsuarioPrincipal;
import com.backportfolio.API.Security.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UseDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Ese usuario no existe"));
        return UsuarioPrincipal.build(usuario);

    }   
}
