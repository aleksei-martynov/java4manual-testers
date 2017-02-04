package com.db.edu.etl;

import com.db.edu.etl.Extractor.EtlExtractor;
import com.db.edu.etl.Loader.EtlLoader;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class EtlControllerTest {

    @Test
//    @BeforeAll
    void DummyConstructorInitializationTest(){
        EtlExtractor dummyExtractor = mock(EtlExtractor.class);
        EtlLoader dummyLoader = mock(EtlLoader.class);

        EtlController controller = new EtlController(
                dummyExtractor,
                new EtlLoader[]{dummyLoader, dummyLoader}
        );

        controller.fullEtlProcess();
    }


}

