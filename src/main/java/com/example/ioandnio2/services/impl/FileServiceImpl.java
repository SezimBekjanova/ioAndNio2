package com.example.ioandnio2.services.impl;

import com.example.ioandnio2.services.FileService;
import com.example.ioandnio2.utils.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
 public class FileServiceImpl implements FileService {
    private  final Path storagePath;
    public FileServiceImpl(FileStorageProperties fileStorageProperties){
        storagePath= Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath()
                .normalize();
        if(!Files.exists(storagePath)){
            try {
                Files.createDirectories(storagePath);
            } catch (IOException e) {
                throw new RuntimeException(" Cannot create storage");
            }
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String folder = UUID.randomUUID().toString();
        Path path = storagePath.resolve(folder).resolve(fileName);
        try {
            Files.createDirectories(path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return folder.concat("/").concat(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Cannot store file!",e);
        }
    }

    @Override
    public Resource getFileByName(String fileName) {
        return null;
    }
}
