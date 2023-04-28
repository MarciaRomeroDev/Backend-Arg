
package com.backportfolio.API.Security.Service;

import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.Security.DTO.JwtDto;
import com.backportfolio.API.Security.DTO.LoginUsuario;
import com.backportfolio.API.Security.DTO.NuevoUsuario;
import com.backportfolio.API.Security.Entity.Rol;
import com.backportfolio.API.Security.Entity.Usuario;
import com.backportfolio.API.Security.Enums.RolNombre;
import com.backportfolio.API.Security.Repository.UsuarioRepository;
import com.backportfolio.API.Security.jwt.JwtProvider;
import com.backportfolio.API.exceptions.CustomException;
import jakarta.transaction.Transactional;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public JwtDto login (LoginUsuario loginUsuario){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }

    public JwtDto refresh (JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }

    public Mensaje save(NuevoUsuario nuevoUsuario){
        if(usuarioRepository.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
           throw new CustomException(HttpStatus.BAD_REQUEST, "Ese nombre de usuario ya existe");
        if(usuarioRepository.existsByEmail(nuevoUsuario.getEmail()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese email ya existe");
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
        return new Mensaje(usuario.getNombreUsuario() + " ha sido creado");
    }
    
}
