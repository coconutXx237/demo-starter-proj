package ru.study.demoapp.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.study.demoapp.model.MediaFile;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MediaStorage {

    private final Map<String, MediaFile> storage = new ConcurrentHashMap<>();

    public String storeFile(MultipartFile file) throws IOException {
        String id = UUID.randomUUID().toString();
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileName(file.getOriginalFilename());
        mediaFile.setContentType(file.getContentType());
        mediaFile.setData(file.getBytes());

        storage.put(id, mediaFile);
        return id;
    }

    public MediaFile getFile(String id) {
        MediaFile file = storage.get(id);
        if (file == null) {
            throw new NoSuchElementException("File not found: " + id);
        }
        return file;
    }
}
