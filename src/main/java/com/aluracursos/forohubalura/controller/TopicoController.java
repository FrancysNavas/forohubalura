package com.aluracursos.forohubalura.controller;

import com.aluracursos.forohubalura.domain.curso.CursoRepository;
import com.aluracursos.forohubalura.domain.topico.DatosActualizarTopico;
import com.aluracursos.forohubalura.domain.topico.DatosListadoTopicos;
import com.aluracursos.forohubalura.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohubalura.domain.topico.DatosRespuestaTopico;
import com.aluracursos.forohubalura.domain.topico.DatosTopico;
import com.aluracursos.forohubalura.domain.topico.Estado;
import com.aluracursos.forohubalura.domain.topico.Topico;
import com.aluracursos.forohubalura.domain.topico.TopicoRepository;
import com.aluracursos.forohubalura.domain.usuario.DatosRespuestaUsuario;
import com.aluracursos.forohubalura.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    private DatosTopico datosTopico;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico dRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {

        // Validar y buscar Usuario
        var usuario = usuarioRepository.findById(dRegistroTopico.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dRegistroTopico.usuarioId()));

        // Validar y buscar Curso
        var curso = cursoRepository.findById(dRegistroTopico.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + dRegistroTopico.cursoId()));

        // Crear el Topico con las relaciones correctas
        var topico = new Topico(dRegistroTopico);
        topico.setUsuario(usuario);
        topico.setCurso(curso);

        // Verificar si estatus es null y asignar un valor predeterminado en caso necesario
        if (topico.getEstatus() == null) {
            topico.setEstatus(Estado.SOLUCIONADO); // O cualquier valor predeterminado según tu negocio
        }

        // Guardar Topico
        topico = topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuestaTopico = null;

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping(value = "/listado")
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 10) Pageable paginacion){

        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopico(@PathVariable Long id){
        // Verificar si el recurso no existe
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devolver un 404 sin cuerpo
        }
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                new DatosRespuestaUsuario(topico.getUsuario().getId(), topico.getUsuario().getNombre(), topico.getUsuario().getPerfil().toString()));
        return ResponseEntity.ok(datosTopico);
    }

    // Actualizar usuario existente (PUT)
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){

        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());

        topico.actualizarDatos(datosActualizarTopico);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                new DatosRespuestaUsuario(topico.getUsuario().getId(), topico.getUsuario().getNombre(), topico.getUsuario().getPerfil().toString()));
        return ResponseEntity.ok(datosTopico);
    }

    //DELETE EN LA BASE DE DATOS
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        // Verificar si el recurso no existe
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El recurso con ID " + id + " no existe.");
        }
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.ok("Recurso eliminado con éxito.");
    }

}
