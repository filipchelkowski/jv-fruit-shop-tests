package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import core.basesyntax.service.FileWriter;
import core.basesyntax.service.ReportGenerator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileWriterImplTest {
    private ReportGenerator reportGenerator = new ReportGeneratorImpl();
    private String fileName = "testReport.csv";
    private FileWriter fileWriter = new FileWriterImpl();

    @BeforeEach
    void setUp() {
        Storage.clear();
        Storage.put("apple", 10);
        Storage.put("banana", 5);
    }

    @Test
    void writeFile_Ok(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve(fileName);
        String report = reportGenerator.getReport();

        fileWriter.write(report, filePath.toString());

        String actual = Files.readString(filePath);
        assertEquals(report, actual);
    }

    @Test
    void write_InvalidPath_ThrowsException(@TempDir Path tempDir) {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> fileWriter.write("data", tempDir.toString()));

        assertTrue(exception.getMessage().contains("Can't write file"));
    }
}
