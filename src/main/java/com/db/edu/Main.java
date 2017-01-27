package com.db.edu;

import com.db.edu.etl.EtlController;
import com.db.edu.etl.Loader.EtlLoader;
import com.db.edu.etl.Extractor.FileCvsExtractor;
import com.db.edu.etl.Loader.StubLoader1;
import com.db.edu.etl.Loader.StubLoader2;

public class Main {
    public static void main(String[] args) {
        
        new EtlController(
                new FileCvsExtractor(),
                new EtlLoader[] {new StubLoader1(), new StubLoader2()}
        ).fullEtlProcess();
    }
}
