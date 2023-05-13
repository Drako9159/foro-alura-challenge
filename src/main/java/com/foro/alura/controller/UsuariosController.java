package com.foro.alura.controller;

import com.foro.alura.domain.usuarios.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private final UsuariosRepository usuariosRepository;

    public UsuariosController(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        Usuarios usuario = usuariosRepository.save(new Usuarios(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo()
        );
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListUsuario>> listadoUsuario(Pageable paginacion) {
        return ResponseEntity.ok(usuariosRepository.findAll(paginacion).map(DatosListUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> RetornarDatosUsuario(@PathVariable Long id) {
        if (!usuariosRepository.existsById(id)) return new ResponseEntity("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
        Usuarios usuario = usuariosRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo()
        );
        return ResponseEntity.ok(datosUsuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        if (!usuariosRepository.existsById(id)) return new ResponseEntity("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
        Usuarios usuario = usuariosRepository.getReferenceById(id);
        usuariosRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        if (!usuariosRepository.existsById(datosActualizarUsuario.id()))
            return new ResponseEntity("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
        Usuarios usuario = usuariosRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatos(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo())
        );

    }


}
