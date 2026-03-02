package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.FileReader;
import java.util.List;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private String testFile = "testFile.csv";
    private String invalidTestFile = "testFileInvalid.csv";
    private FileReader fileReader = new FileReaderImpl();
    private DataConverter dataConverter = new DataConverterImpl();
    private List<String> listOfTransactions = fileReader.read(testFile);
    private List<String> invalidListOfTransactions = fileReader.read(invalidTestFile);

    @Test
    void createsListOfTransactions_Ok() {
        List<FruitTransaction> result = dataConverter.convertToTransactions(listOfTransactions);
        assertNotNull(result);
    }

    @Test
    void pointSeparator_NotOk() {
        assertThrows(RuntimeException.class,
                () -> dataConverter.convertToTransactions(invalidListOfTransactions));

    }
}
