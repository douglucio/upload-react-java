package br.com.rnd.uploads.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rnd.uploads.models.Files;
import br.com.rnd.uploads.service.files.FilesService;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class FilesController {
    FilesService service;

    public FilesController(FilesService service) {
        this.service = service;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("music") String music, @RequestParam("artist") String artist,
            @RequestParam("gender") String gender) throws IOException {
        Files files = service.save(file, music, artist, gender);
        return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
                .path(files.getUuid().toString()).toUriString());
    }

    @GetMapping("files/list")
    public ResponseEntity<List<Files>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }
}
