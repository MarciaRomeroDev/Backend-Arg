
package com.backportfolio.API.Security.Controller;

import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.Security.DTO.JwtDto;
import com.backportfolio.API.Security.DTO.LoginUsuario;
import com.backportfolio.API.Security.DTO.NuevoUsuario;
import com.backportfolio.API.Security.Service.UsuarioService;
import jakarta.validation.Valid;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UsuarioService usuarioService;


    @PostMapping("/nuevo")
    public ResponseEntity<Mensaje> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario){
        return ResponseEntity.ok(usuarioService.save(nuevoUsuario));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login (@Valid @RequestBody LoginUsuario loginUsuario){
        return ResponseEntity.ok(usuarioService.login(loginUsuario));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refreshToken (@RequestBody JwtDto jwtDto) throws ParseException {
        return ResponseEntity.ok(usuarioService.refresh(jwtDto));
    }
    
}
