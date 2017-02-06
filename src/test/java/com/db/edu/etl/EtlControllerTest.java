package com.db.edu.etl;

import com.db.edu.etl.Exception.DataExtractException;
import com.db.edu.etl.Exception.DataLoadException;
import com.db.edu.etl.Exception.EtlException;
import com.db.edu.etl.Exception.ParseException;
import com.db.edu.etl.Exception.TransformException;
import com.db.edu.etl.Extractor.EtlExtractor;
import com.db.edu.etl.Loader.EtlLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EtlControllerTest {

    public static final Logger logger = LoggerFactory.getLogger(EtlControllerTest.class);
    public static final String EXCEPTION_TEST_MESSAGE = "Exception test message";

    private EtlExtractor stubExtractor;
    private EtlLoader mockLoader;
    private ExtractedUser[] dummyUsers;
    private EtlController controller;

    @BeforeAll
    static void BeforAllTest() {
        logger.info("@BeforAll works!!!");
    }

    @AfterAll
    static void AfterAllTest() {
        logger.info("@AfterAll works!!!");
    }

    @BeforeEach
    void prepare() {
        stubExtractor = mock(EtlExtractor.class);
        mockLoader = mock(EtlLoader.class);
        dummyUsers = new ExtractedUser[]{new ExtractedUser("0001", "qa01"), new ExtractedUser("0002", "qa02")};
        controller = new EtlController(stubExtractor, new EtlLoader[]{mockLoader, mockLoader});
    }

    @Test
    void shouldDoFullEtlWhenNoExceptions() throws DataExtractException, ParseException, TransformException, DataLoadException {
        // Given
//        Class<? extends Throwable> exceptionType = DataExtractException.class;

        when(stubExtractor.extract()).thenReturn(dummyUsers);
        // When
        controller.fullEtlProcess();
        // Then
        verify(mockLoader, times(2)).load(dummyUsers);
    }

    @Test
    void shouldThrowDataExtractException() throws DataExtractException, ParseException {
        // Given
//        makeStubBehaviour(DataExtractException.class, EXCEPTION_TEST_MESSAGE, stubExtractor::extract);

        when(stubExtractor.extract()).thenThrow(new DataExtractException(EXCEPTION_TEST_MESSAGE));
        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals("Please stop data extracting process!", caughtException.getMessage());

        final DataExtractException cause = (DataExtractException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE, cause.getMessage());
    }

    @Test
    void shouldThrowParseException() throws DataExtractException, ParseException {
        // Given
        when(stubExtractor.extract()).thenThrow(new ParseException(EXCEPTION_TEST_MESSAGE));
        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals("Please stop parsing!", caughtException.getMessage());

        final ParseException cause = (ParseException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE, cause.getMessage());
    }

    @Test
    void shouldThrowDataLoadException() throws DataExtractException, ParseException, TransformException, DataLoadException {
        // Given
        doThrow(new DataLoadException(EXCEPTION_TEST_MESSAGE)).when(mockLoader).load(any());
        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals("Please stop data loading process!", caughtException.getMessage());
        final DataLoadException cause = (DataLoadException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE, cause.getMessage());
    }

    @Test
    void shouldThrowTransformException() throws DataExtractException, ParseException, TransformException, DataLoadException {
        // Given
        doThrow(new TransformException(EXCEPTION_TEST_MESSAGE)).when(mockLoader).load(any());
        // When
        final Throwable caughtException = assertThrows(EtlException.class, controller::fullEtlProcess);
        assertEquals("Please stop transforming!", caughtException.getMessage());
        final TransformException cause = (TransformException) caughtException.getCause();
        assertEquals(EXCEPTION_TEST_MESSAGE, cause.getMessage());
    }

    private void makeStubBehaviour(Class<? extends Throwable> exception, String exeptionMessage, Executable executable) {
        try {
            when(executable).thenThrow(exception.getConstructor(String.class).newInstance(exeptionMessage));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

//    class BehaviourBuilder {
//        public BehaviourBuilder withException(Class<? extends Throwable> exceptionType, String exceptionMessage, Executable executable) {
//            try {
//                exceptionType.getConstructor(String.class).newInstance(exceptionMessage);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            return this;
//        }
//        public void build(Class<? extends Throwable> exceptionType, String exceptionMessage, Executable executable){
//            when(executable).thenThrow(this)
//        }
//    }
//}