package br.com.rnd.uploads.service.files.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.rnd.uploads.models.Files;
import br.com.rnd.uploads.models.Files.FilesBuilder;
import br.com.rnd.uploads.repository.FilesRepository;
import br.com.rnd.uploads.service.files.FilesService;

@Service
public class FilesServiceImpl implements FilesService {

    FilesRepository filesRepository;

    public FilesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public Files save(MultipartFile file, String music, String artist, String gender) {
        try {
            return uploadFile(file, music, artist, gender);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Files uploadFile(MultipartFile file, String music, String artist, String gender) throws IOException {
        byte[] data = file.getBytes();
        String type = file.getContentType();
        String filename = file.getOriginalFilename();

        validateFilename(filename);

        Files files = filesRepository.saveAndFlush(FilesBuilder.newBuilder(data).withFilename(filename).withType(type)
                .withMusic(music).withArtist(artist).withGender(gender).build());

        return files;
    }

    private void validateFilename(String filename) {
        if (filename.contains("..")) {
            throw new RuntimeException("Sorry! filename invalid [" + filename + "]");
        }
    }

    public List<Files> findAll() {
        return this.filesRepository.findAll();
    }

}
