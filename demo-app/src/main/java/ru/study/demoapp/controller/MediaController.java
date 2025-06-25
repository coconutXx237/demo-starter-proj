package ru.study.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.study.demoapp.model.MediaFile;
import ru.study.demoapp.service.MediaService;

import java.io.IOException;

@RequestMapping("/media")
@RestController
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String id = mediaService.save(file);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        MediaFile mediaFile = mediaService.load(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaFile.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + mediaFile.getFileName() + "\"")
                .body(mediaFile.getData());
    }
}
