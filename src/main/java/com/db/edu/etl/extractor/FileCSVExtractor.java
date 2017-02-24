package com.db.edu.etl.extractor;

import com.db.edu.etl.ExtractedUser;
import com.db.edu.etl.exception.DataExtractException;
import com.db.edu.etl.exception.ParseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class FileCSVExtractor implements EtlExtractor {
    private static final String DEFAULT_FIELD__ID = "ID";
    private static final String DEFAULT_FIELD__USER_NAME = "UserName";

    private static final String[] CSV_DEFAULT_MAPPING = {DEFAULT_FIELD__ID, DEFAULT_FIELD__USER_NAME};

    @Override
    public HashSet<ExtractedUser> extract(File filePath) throws ParseException, DataExtractException {
        HashSet<ExtractedUser> users = new HashSet<>();

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(CSV_DEFAULT_MAPPING);

        try (FileReader fileReader = new FileReader(filePath)) {

            CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);
            List<CSVRecord> csvRecords = csvFileParser.getRecords();

            for (CSVRecord record : csvRecords) {
                if (record.size()!= CSV_DEFAULT_MAPPING.length) {
                    throw new ParseException("Record sizes do not match" + record.toString());
                }
                ExtractedUser user = new ExtractedUser(record.get(DEFAULT_FIELD__ID), record.get(DEFAULT_FIELD__USER_NAME));
                users.add(user);
            }
        } catch (IOException e) {
            throw new DataExtractException("Open file exception", e);
        }
        return users;
    }
}