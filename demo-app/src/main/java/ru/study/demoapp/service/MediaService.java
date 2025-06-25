package ru.study.demoapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.study.demoapp.model.MediaFile;
import ru.study.demoapp.repository.MediaStorage;

import java.io.IOException;

@Service
public class MediaService {

    private final MediaStorage memoryStorage;

    public MediaService(MediaStorage memoryStorage) {
        this.memoryStorage = memoryStorage;
    }

    public String save(MultipartFile file) throws IOException {
        return memoryStorage.storeFile(file);
    }

    public MediaFile load(String id) {
        return memoryStorage.getFile(id);
    }
}
