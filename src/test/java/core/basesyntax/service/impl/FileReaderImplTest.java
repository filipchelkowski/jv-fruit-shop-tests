package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.service.FileReader;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private String testFile = "testFile.csv";
    private String invalidTestFile = "invalidTestFile.csv";
    private FileReader fileReader = new FileReaderImpl();

    @Test
    void read_createsListOfTransactions_ok() {
        List<String> result = fileReader.read(testFile);
        int expected = 3;
        int actual = result.size();
        assertNotNull(result);
        assertEquals(expected, actual);
    }

    @Test
    void read_parseLines_ok() {
        List<String> result = fileReader.read(testFile);
        String expected = "b,banana,20";
        String actual = result.get(1);
        assertEquals(expected, actual);
    }

    @Test
    void read_incorrectPath_throwsException_Ok() {
        assertThrows(RuntimeException.class, () -> fileReader.read(invalidTestFile));
    }
}
