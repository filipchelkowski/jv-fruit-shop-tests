package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private ReportGenerator reportGenerator = new ReportGeneratorImpl();

    @BeforeEach
    void setUp() {
        Storage.clear();
        Storage.put("apple", 10);
        Storage.put("banana", 5);
    }

    @Test
    void generateReport_Ok() {
        String report = reportGenerator.getReport();

        assertNotNull(report);
    }
}
