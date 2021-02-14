package com.phd.LoadCvsToDatabase;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@Entity
@Builder
@Getter
@Setter
@Table(name = "Scan_Data")
public class ScanData {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "LocalTime")
    private String LocalTime;
    @Column(name = "GPSTime")
    private String GPSTime;
    @Column(name = "ESSID")
    private String ESSID;
    @Column(name = "BSSID")
    private String BSSID;
    @Column(name = "Power")
    private String Power;
    @Column(name = "Security")
    private String Security;
    @Column(name = "Latitude")
    private String Latitude;
    @Column(name = "Longitude")
    private String Longitude;
    @Column(name = "LatitudeError")
    private String LatitudeError;
    @Column(name = "LongitudeError")
    private String LongitudeError;
    @Column(name = "Type")
    private String Type;
    private static AtomicLong uniqueId=new AtomicLong();


    public ScanData(Long id,String localTime, String GPSTime, String ESSID, String BSSID, String power, String security, String latitude, String longitude, String latitudeError, String longitudeError, String type) {
        this.id=uniqueId.getAndIncrement();
        LocalTime = localTime;
        this.GPSTime = GPSTime;
        this.ESSID = ESSID;
        this.BSSID = BSSID;
        Power = power;
        Security = security;
        Latitude = latitude;
        Longitude = longitude;
        LatitudeError = latitudeError;
        LongitudeError = longitudeError;
        Type = type;
    }

    public ScanData() {
    }
}
