package com.example.backend.responsemodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class FileModel {
    private String fileName;
    private String fileType;
    private LocalDate lastUpdated;
}
