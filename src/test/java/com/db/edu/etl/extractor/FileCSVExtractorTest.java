package com.db.edu.etl.extractor;

import com.db.edu.etl.ExtractedUser;
import com.db.edu.etl.exception.DataExtractException;
import com.db.edu.etl.exception.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileCSVExtractorTest {
    private static final String POS_TEST_DATA = "testData\\extractorTestData\\csvPosTest.csv";
    private static final String NEG_TEST_DATA = "testData\\extractorTestData\\csvNegTest.csv";
    private static final String NOT_EXISTED_TEST_DATA = "testData\\extractorTestData\\csvNotExTest.csv";

    private static final Logger logger = LoggerFactory.getLogger(FileCSVExtractorTest.class);

    private EtlExtractor fileCSVExtractor;

    @BeforeAll
    static void beforeAllTest() {
        logger.info("Start testing class:" + FileCSVExtractor.class.getClass().getSimpleName());
    }

    @AfterAll
    static void afterAllTest() {
        logger.info("End testing class:" + FileCSVExtractor.class.getClass().getSimpleName());
    }

    @BeforeEach
    void setUp() {
        fileCSVExtractor = new FileCSVExtractor();
    }

    @Test
    void shouldExtractValidFile() throws IOException {
        HashSet<ExtractedUser> dummyUsers = new HashSet<>();

        dummyUsers.add(new ExtractedUser("01","user1"));
        dummyUsers.add(new ExtractedUser("02","user2"));
        dummyUsers.add(new ExtractedUser("03","user3"));

        HashSet<ExtractedUser> extractedUsers = fileCSVExtractor.extract(new File(POS_TEST_DATA));

        for(ExtractedUser iterator: dummyUsers) {
            if (!extractedUsers.contains(iterator)) {
                 throw new RuntimeException("dummyUsers does not contain " + iterator.toString());
            }
        }
    }

    @Test
    void shouldThrowDataExtractExceptionCausedFNFE() {
        File notExistedFilePath = new File(NOT_EXISTED_TEST_DATA);

        final Throwable caughtException = assertThrows(
                DataExtractException.class,
                () -> fileCSVExtractor.extract(notExistedFilePath)
        );
        assertEquals(FileNotFoundException.class, caughtException.getCause().getClass());
        assertEquals("Open file exception", caughtException.getMessage());
    }

    @Test
    void shouldThrowParseException() throws IOException {
        File badDataFilePath = new File(NEG_TEST_DATA);

        assertThrows(
                ParseException.class,
                () -> fileCSVExtractor.extract(badDataFilePath)
        );
    }
}
