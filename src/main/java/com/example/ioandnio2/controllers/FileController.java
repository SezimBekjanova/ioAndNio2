package com.example.ioandnio2.controllers;

import com.example.ioandnio2.responses.Response;
import com.example.ioandnio2.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/v1/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @PostMapping(value = "/upload", consumes =  "multipart/form-data")
    public Response upload(@RequestPart MultipartFile file){
        String fileName =  fileService.storeFile(file);
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/file/download")
                .path("//")
                .toUriString();
        return new Response(
                fileName,
                downloadUri,
                file.getContentType(),
                file.getSize()
        );
    }
}
