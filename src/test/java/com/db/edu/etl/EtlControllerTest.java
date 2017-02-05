package com.db.edu.etl;

import com.db.edu.etl.Exception.*;
import com.db.edu.etl.Extractor.EtlExtractor;
import com.db.edu.etl.Loader.EtlLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EtlControllerTest {

    public static final Logger logger = LoggerFactory.getLogger(EtlControllerTest.class);
    public static final String EXCEPTION_TEST_MESSAGE = "Exception test message";

    @BeforeAll
    static void BeforAllTest(){
        logger.info("@BeforAll works!!!");
    }

    @AfterAll
    static void AfterAllTest(){
        logger.info("@AfterAll works!!!");
    }

    @Test
    void shouldDoFullEtlWhenNoExceptions() throws DataExtractException, ParseException, TransformException, DataLoadException {
         // Given
        final EtlExtractor stubExtractor = mock(EtlExtractor.class);
        final EtlLoader mockLoader = mock(EtlLoader.class);
        final ExtractedUser[] dummyUsers = {
                new ExtractedUser("0001","qa01"),
                new ExtractedUser("0002","qa02")
        };

        // Some Mokito magic!!
        when(stubExtractor.extract()).thenReturn(dummyUsers);

        EtlController controller = new EtlController(
                stubExtractor,
                new EtlLoader[]{mockLoader, mockLoader}
        );

        // When
        controller.fullEtlProcess();

        // Then
        verify(mockLoader,times(2)).load(dummyUsers);

        logger.debug("No Exception path tested");
    }

    @Test
    void shouldThrowDataExtractException() throws DataExtractException, ParseException {
        // Given
        final EtlExtractor stubExtractor = mock(EtlExtractor.class);
        final EtlLoader mockLoader = mock(EtlLoader.class);
//        final Class<DataExtractException> exceptionType =  DataExtractException.class;

        // Some Mokito magic!!
        when(stubExtractor.extract()).thenThrow(new DataExtractException(EXCEPTION_TEST_MESSAGE));

        EtlController controller = new EtlController(
                stubExtractor,
                new EtlLoader[]{mockLoader, mockLoader}
        );

        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals(
                "Please stop data extracting process!", caughtException.getMessage()
        );

        final DataExtractException cause = (DataExtractException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE,cause.getMessage());

        logger.debug("DataExtractException path tested");
    }

    @Test
    void shouldThrowParseException() throws DataExtractException, ParseException {
        // Given
        final EtlExtractor stubExtractor = mock(EtlExtractor.class);
        final EtlLoader mockLoader = mock(EtlLoader.class);
//        final Class<DataExtractException> exceptionType =  DataExtractException.class;

        // Some Mokito magic!!
        when(stubExtractor.extract()).thenThrow(new ParseException(EXCEPTION_TEST_MESSAGE));

        EtlController controller = new EtlController(
                stubExtractor,
                new EtlLoader[]{mockLoader, mockLoader}
        );

        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals(
                "Please stop parsing!", caughtException.getMessage()
        );

        final ParseException cause = (ParseException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE,cause.getMessage());

        logger.debug("ParseException path tested");
    }

    @Test
    void shouldThrowDataLoadException() throws DataExtractException, ParseException, TransformException, DataLoadException {
        // Given
        final EtlExtractor stubExtractor = mock(EtlExtractor.class);
        final EtlLoader mockLoader = mock(EtlLoader.class);
//        final Class<DataExtractException> exceptionType =  DataExtractException.class;
        final ExtractedUser[] dummyUsers = {
                new ExtractedUser("0001","qa01"),
                new ExtractedUser("0002","qa02")
        };

        // Some Mokito magic!!
        doThrow(new DataLoadException(EXCEPTION_TEST_MESSAGE)).when(mockLoader).load(any());

        EtlController controller = new EtlController(
                stubExtractor,
                new EtlLoader[]{mockLoader, mockLoader}
        );

        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals(
                "Please stop data loading process!", caughtException.getMessage()
        );

        final DataLoadException cause = (DataLoadException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE,cause.getMessage());

        logger.debug("DataLoadException path tested");
    }
    @Test
    void shouldThrowTransformException() throws DataExtractException, ParseException, TransformException, DataLoadException {
        // Given
        final EtlExtractor stubExtractor = mock(EtlExtractor.class);
        final EtlLoader mockLoader = mock(EtlLoader.class);
//        final Class<DataExtractException> exceptionType =  DataExtractException.class;
        final ExtractedUser[] dummyUsers = {
                new ExtractedUser("0001","qa01"),
                new ExtractedUser("0002","qa02")
        };

        // Some Mokito magic!!
        doThrow(new TransformException(EXCEPTION_TEST_MESSAGE)).when(mockLoader).load(any());

        EtlController controller = new EtlController(
                stubExtractor,
                new EtlLoader[]{mockLoader, mockLoader}
        );

        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals(
                "Please stop transforming!", caughtException.getMessage()
        );

        final TransformException cause = (TransformException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE,cause.getMessage());

        logger.debug("TransformException path tested");
    }
}

