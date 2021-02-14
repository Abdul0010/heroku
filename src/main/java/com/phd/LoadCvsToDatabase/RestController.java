package com.phd.LoadCvsToDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    ScanDataService scanDataService;

    @RequestMapping(path="/scanData", method= RequestMethod.GET)
    public List<ScanData> getAllEmployees(){
        return scanDataService.getAllTutorials();
    }


}
