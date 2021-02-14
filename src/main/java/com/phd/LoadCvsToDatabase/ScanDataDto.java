package com.phd.LoadCvsToDatabase;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class ScanDataDto {

    private String message;
    private String fileUrl;

    public ScanDataDto(String message, String fileDownloadUri) {
        this.fileUrl=fileDownloadUri;
        this.message=message;
    }
}
