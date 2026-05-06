package cl.bibliotecaproyecto.libros.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroResponseDTO {

    private Long id;
    private String isbn;
    private String titulo;
    private Integer anio;

    private long idCategoria;
    private Long idAutor;


}
