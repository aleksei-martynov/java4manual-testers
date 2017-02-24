package com.db.edu.etl;

import com.db.edu.etl.exception.DataExtractException;
import com.db.edu.etl.exception.DataLoadException;
import com.db.edu.etl.exception.EtlException;
import com.db.edu.etl.exception.ParseException;
import com.db.edu.etl.exception.TransformException;
import com.db.edu.etl.extractor.EtlExtractor;
import com.db.edu.etl.loader.EtlLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EtlControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EtlControllerTest.class);
    private static final String EXCEPTION_TEST_MESSAGE = "Exception test message";
    private static final int NUMBER_OF_DUMMIES = 2;
    private static final String TEST_EXTRACT_FILE_PATH = "csvPosTest.csv";

    private EtlExtractor stubExtractor;
    private EtlLoader mockLoader;
    private HashSet<ExtractedUser> dummyUsers;
    private EtlController controller;
    private File extractFilePath;

    @BeforeAll
    static void beforeAllTest() {
        logger.info("Start testing class:" + EtlController.class.getClass().getSimpleName());
    }

    @AfterAll
    static void afterAllTest() {
        logger.info("End testing class:" + EtlController.class.getClass().getSimpleName());
    }

    @BeforeEach
    void prepare() {
        stubExtractor = mock(EtlExtractor.class);
        mockLoader = mock(EtlLoader.class);
        extractFilePath = new File(TEST_EXTRACT_FILE_PATH);
        dummyUsers = prepareDummyUsersSet(NUMBER_OF_DUMMIES);
        controller = new EtlController(stubExtractor, new EtlLoader[]{mockLoader, mockLoader}, extractFilePath);
        extractFilePath = new File(TEST_EXTRACT_FILE_PATH);
    }

    @Test
    void shouldDoFullEtlWhenNoExceptions() throws DataExtractException, ParseException, TransformException, DataLoadException, IOException {
        // Given
        when(stubExtractor.extract(extractFilePath)).thenReturn(dummyUsers);
        // When
        controller.fullEtlProcess();
        // Then
        verify(mockLoader, times(2)).load(dummyUsers);
    }

    @Test
    void shouldThrowDataExtractException() throws DataExtractException, ParseException {
        // Given
        new ControllerBehaviourBuilder()
                .addExceptionToThrow(DataExtractException.class)
                .addExceptionMessage(EXCEPTION_TEST_MESSAGE)
                .build(stubExtractor);
        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals("Please stop data extracting process!", caughtException.getMessage());

        final DataExtractException cause = (DataExtractException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE, cause.getMessage());
    }

    @Test
    void shouldThrowParseException() throws DataExtractException, ParseException {
        // Given
        new ControllerBehaviourBuilder()
                .addExceptionToThrow(ParseException.class)
                .addExceptionMessage(EXCEPTION_TEST_MESSAGE)
                .build(stubExtractor);
        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals("Please stop parsing!", caughtException.getMessage());

        final ParseException cause = (ParseException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE, cause.getMessage());
    }

    @Test
    void shouldThrowDataLoadException() throws DataExtractException, ParseException, TransformException, DataLoadException {
        // Given
        new ControllerBehaviourBuilder()
                .addExceptionToThrow(DataLoadException.class)
                .addExceptionMessage(EXCEPTION_TEST_MESSAGE)
                .build(mockLoader);
        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals("Please stop data loading process!", caughtException.getMessage());
        final DataLoadException cause = (DataLoadException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE, cause.getMessage());
    }

    @Test
    void shouldThrowTransformException() throws DataExtractException, ParseException, TransformException, DataLoadException {
        // Given
        new ControllerBehaviourBuilder()
                .addExceptionToThrow(TransformException.class)
                .addExceptionMessage(EXCEPTION_TEST_MESSAGE)
                .build(mockLoader);
        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals("Please stop transforming!", caughtException.getMessage());
        final TransformException cause = (TransformException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE, cause.getMessage());
    }


    private HashSet<ExtractedUser> prepareDummyUsersSet(Integer number) {
        if (number < 1) {
            throw new RuntimeException("Incorrect number of values");
        }
        dummyUsers = new HashSet<>();

        Integer counter = 1;
        while (counter <= number) {
            dummyUsers.add(new ExtractedUser("000"+counter.toString(), "qa0"+counter.toString()));
            counter++;
        }


        ExtractedUser dummyUser01 = new ExtractedUser("0001", "qa01");
        ExtractedUser dummyUser02 = new ExtractedUser("0002", "qa02");
        dummyUsers = new HashSet<>();
        dummyUsers.add(dummyUser01);
        dummyUsers.add(dummyUser02);
        return dummyUsers;
    }
}

class ControllerBehaviourBuilder {
    private Class<? extends Throwable> exceptionToThrow;
    private String exceptionMessage;

    ControllerBehaviourBuilder addExceptionToThrow(Class<? extends Throwable> exceptionType) {
        this.exceptionToThrow = exceptionType;
        return this;
    }

    ControllerBehaviourBuilder addExceptionMessage(String message) {
        this.exceptionMessage = message;
        return this;
    }

    void build(EtlExtractor e) {
        try {
            when(e.extract(any(File.class))).thenThrow(this.exceptionToThrow.getConstructor(String.class).newInstance(exceptionMessage));
        } catch (Exception e1) {
            throw new RuntimeException("Build for EtlExtractor failed. Debug build method!", e1);
        }
    }

    void build(EtlLoader e) {
        try {
            when(e.load(any())).thenThrow(this.exceptionToThrow.getConstructor(String.class).newInstance(exceptionMessage));
        } catch (Exception e1) {
            throw new RuntimeException("Build for EtlLoader failed. Debug build method!", e1);
        }
    }
}