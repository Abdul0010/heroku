package com.phd.LoadCvsToDatabase;

import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class helper {
    public static String TYPE = "csv";
    static String[] HEADERs = { "Id", "LocalTime", "GPSTime", "ESSID", "BSSID","Power","Security","Latitude", "Longitude",
            "LatitudeError", "LongitudeError", "Type"};
    public static boolean hasCSVFormat(MultipartFile file) {
        String extension = "";
        String fileName=file.getOriginalFilename();

        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }
        if (TYPE.equals(extension)) {
            return true;
        }

        return false;
    }

    public static List<ScanData> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<ScanData> scanDataList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            csvParser.getHeaderMap().entrySet()
                    .stream()
                    .collect(Collectors.toMap(e->e.getKey().replaceAll("\\s+",""),e->e.getValue()));
            for (CSVRecord csvRecord : csvRecords) {
                ScanData scanData = new ScanData(
                        1l,
                        csvRecord.get("localTime"),
                        csvRecord.get("GPSTime"),
                        csvRecord.get("ESSID"),
                        csvRecord.get("BSSID"),
                        csvRecord.get("Power"),
                        csvRecord.get("Security"),
                        csvRecord.get("Latitude"),
                        csvRecord.get("Longitude"),
                        csvRecord.get("Latitude Error"),
                        csvRecord.get("Longitude Error"),
                        csvRecord.get("Type")
                );

                scanDataList.add(scanData);
            }

            return scanDataList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<ScanData> scanDataList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (ScanData scanData : scanDataList) {
                List<String> data = Arrays.asList(
                        String.valueOf(scanData.getId()),
                        scanData.getGPSTime(),
                        scanData.getESSID(),
                        scanData.getBSSID(),
                        scanData.getPower(),
                        scanData.getSecurity(),
                        scanData.getLatitude(),
                        scanData.getLongitude(),
                        scanData.getLatitudeError(),
                        scanData.getLatitudeError(),
                        scanData.getType()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

}
