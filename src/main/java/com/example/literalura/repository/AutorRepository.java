package com.example.literalura.repository;

import com.example.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Método para obtener autores vivos en un año específico
    @Query("SELECT a FROM Autor a WHERE (a.anoFallecimiento IS NULL OR a.anoFallecimiento >= :anio) AND a.anoNacimiento <= :anio")
    List<Autor> findAutoresVivosEnAnio(int anio);

    // Método para obtener autores por idioma
    List<Autor> findByIdioma(String idioma);  // Uso de convención estándar
}
