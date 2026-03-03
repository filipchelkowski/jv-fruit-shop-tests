package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import java.util.List;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {

    private DataConverter dataConverter = new DataConverterImpl();
    private List<String> listOfTransactions = List.of("op,furit,qty",
            "b,apple,10");
    private List<String> invalidListOfTransactions = List.of("op.furit.qty",
            "b.apple.10");

    @Test
    void convertToTransactions_validData_ok() {
        List<FruitTransaction> result = dataConverter.convertToTransactions(listOfTransactions);
        int expected = listOfTransactions.size() - 1;
        int actual = result.size();
        assertNotNull(result);
        assertEquals(expected, actual);
    }

    @Test
    void convertToTransactions_parseFruitName_ok() {
        List<FruitTransaction> result = dataConverter.convertToTransactions(listOfTransactions);
        String expected = "apple";
        String actual = result.get(0).getFruit();
        assertEquals(expected, actual);
    }

    @Test
    void convertToTransactions_parseOperation_ok() {
        List<FruitTransaction> result = dataConverter.convertToTransactions(listOfTransactions);
        FruitTransaction.Operation expected = FruitTransaction.Operation.BALANCE;
        FruitTransaction.Operation actual = result.get(0).getOperation();
        assertEquals(expected, actual);
    }

    @Test
    void convertToTransactions_parseQuantity_ok() {
        List<FruitTransaction> result = dataConverter.convertToTransactions(listOfTransactions);
        int expected = 10;
        int actual = result.get(0).getQuantity();
        assertEquals(expected, actual);
    }

    @Test
    void convertToTransactions_invalidSeparator_throwException() {
        assertThrows(RuntimeException.class,
                () -> dataConverter.convertToTransactions(invalidListOfTransactions));

    }
}
