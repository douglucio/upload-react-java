package br.com.rnd.uploads.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rnd.uploads.models.Files;

public interface FilesRepository extends JpaRepository<Files, UUID> {
    
}
