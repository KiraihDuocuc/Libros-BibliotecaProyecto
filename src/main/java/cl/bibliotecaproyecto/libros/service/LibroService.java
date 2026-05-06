package cl.bibliotecaproyecto.libros.service;


import cl.bibliotecaproyecto.libros.dto.LibroRequestDTO;
import cl.bibliotecaproyecto.libros.dto.LibroResponseDTO;
import cl.bibliotecaproyecto.libros.model.Libro;
import cl.bibliotecaproyecto.libros.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroService {
    private final LibroRepository libroRepository;

    private LibroResponseDTO mapToDTO(Libro libro){
        return new LibroResponseDTO(
                libro.getId(),
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getAnio(),
                libro.getIdCategoria(),
                libro.getIdAutor()
        );
    }

    //obtener todos los libros
    public List<LibroResponseDTO> obtenerTodos(){
        return libroRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //obtener por id
    public Optional<LibroResponseDTO> obtenerPorId(Long id) {
        return libroRepository.findById(id).map(this::mapToDTO);
    }

    //guardar
    public LibroResponseDTO guardar(LibroRequestDTO dto){
            Libro libro = new Libro(
                    null,
                    dto.getIsbn(),
                    dto.getTitulo(),
                    dto.getAnio(),
                    dto.getIdCategoria(),
                    dto.getIdAutor()
            );
            return mapToDTO(libroRepository.save(libro));
        }

    //actualizar
    public Optional<LibroResponseDTO> actualizar(Long id, LibroRequestDTO dto) {
        return libroRepository.findById(id).map(existente -> {

            existente.setIsbn(dto.getIsbn());
            existente.setTitulo(dto.getTitulo());
            existente.setAnio(dto.getAnio());
            existente.setIdCategoria(dto.getIdCategoria());
            existente.setIdAutor(dto.getIdAutor());

            return mapToDTO(libroRepository.save(existente));
        });
    }

    //eliminar
    public void eliminar(Long id){
        libroRepository.deleteById(id);
    }

    //buscar por titulo y categoria
    public List<LibroResponseDTO> buscarPorTitulo(String texto) {
        return libroRepository.findByTituloContainingIgnoreCase(texto)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<LibroResponseDTO> buscarPorCategoria(Long categoriaId) {
        return libroRepository.findByIdCategoria(categoriaId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
