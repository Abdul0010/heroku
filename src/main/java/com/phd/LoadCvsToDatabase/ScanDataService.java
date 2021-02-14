package com.phd.LoadCvsToDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ScanDataService {

    @Autowired
    private ScanDataInterface scanDataRepo;

    public void saveScannedData(MultipartFile file) throws IOException {
        List<ScanData> scanDataList=helper.csvToTutorials(file.getInputStream());
        scanDataRepo.saveAll(scanDataList);
    }

    public ByteArrayInputStream load() {
        List<ScanData> tutorials = scanDataRepo.findAll();

        ByteArrayInputStream in = helper.tutorialsToCSV(tutorials);
        return in;
    }
    public List<ScanData> getAllTutorials() {
        return scanDataRepo.findAll();
    }
}
