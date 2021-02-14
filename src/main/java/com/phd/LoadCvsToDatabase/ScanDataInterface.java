package com.phd.LoadCvsToDatabase;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Registered
public interface ScanDataInterface extends JpaRepository<ScanData,Long> {

    List<ScanData> findAll();
}
