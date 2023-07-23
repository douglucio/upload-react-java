package br.com.rnd.uploads.service.files;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.rnd.uploads.models.Files;

public interface FilesService {
    Files save(MultipartFile file, String music, String artist, String gender);

    List<Files> findAll();
}
