package com.phd.LoadCvsToDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/scan-data")
public class ScanDataController {

    @Autowired
    private ScanDataService scanDataService;

    @RequestMapping(path="/", method=RequestMethod.GET)
    public String goHome(){
        return "index";
    }
    @PostMapping("/upload")

    public ResponseEntity<ScanDataDto> uploadData(@RequestParam("file") MultipartFile file) throws IOException {
        String message=null;

        if(helper.hasCSVFormat(file)){
            scanDataService.saveScannedData(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/csv/download/")
                    .path(file.getOriginalFilename())
                    .toUriString();
            return ResponseEntity.status(HttpStatus.OK).body(new ScanDataDto(message,fileDownloadUri));
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ScanDataDto(message,""));
    }

    @GetMapping("/scanData")
    public String getAllTutorials(Model model) {
            List<ScanData> tutorials = scanDataService.getAllTutorials();

            model.addAttribute("scandata", scanDataService.getAllTutorials());
            return "data";


    }
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(scanDataService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }


}
