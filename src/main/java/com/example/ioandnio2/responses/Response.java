package com.example.ioandnio2.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String fileName;
    private String downloadUri;
    private String fileType;
    private long size;
}
