package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.service.FileReader;
import java.util.List;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private String testFile = "testFile.csv";
    private String invalidTestFile = "invalidTestFile.csv";
    private FileReader fileReader = new FileReaderImpl();

    @Test
    void createsListOfTransactions_Ok() {
        List<String> result = fileReader.read(testFile);
        assertNotNull(result);

    }

    @Test
    void throwsException_Ok() {
        assertThrows(RuntimeException.class, () -> fileReader.read(invalidTestFile));
    }
}
