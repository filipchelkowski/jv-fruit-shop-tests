package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ShopService;
import core.basesyntax.strategy.BalanceOperation;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.strategy.PurchaseOperation;
import core.basesyntax.strategy.ReturnOperation;
import core.basesyntax.strategy.SupplyOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopServiceImplTest {
    private FruitTransaction fruitTransaction = new FruitTransaction();
    private FruitTransaction invalidFruitTransaction = new FruitTransaction();
    private Map<String, Integer> storage;
    private List<FruitTransaction> transactionList;
    private Map<FruitTransaction.Operation, OperationHandler> operationHandlers;
    private OperationStrategy operationStrategy;
    private ShopService shopService;

    @BeforeEach
    void setUp() {
        storage = Storage.getFruitStorage();
        Storage.clear();

        transactionList = new ArrayList<>();
        operationHandlers = new HashMap<>();
        fruitTransaction = new FruitTransaction();
        invalidFruitTransaction = new FruitTransaction();

        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());

        operationStrategy = new OperationStrategyImpl(operationHandlers);
        shopService = new ShopServiceImpl(operationStrategy);
    }

    @Test
    void balanceOperation_Ok() {
        fruitTransaction.setFruit("apple");
        fruitTransaction.setOperation("b");
        fruitTransaction.setQuantity(10);
        transactionList.add(fruitTransaction);

        assertNotNull(storage);
    }

    @Test
    void balanceOperation_NotOk() {
        fruitTransaction.setFruit("apple");
        fruitTransaction.setOperation("b");
        fruitTransaction.setQuantity(-10);
        transactionList.add(fruitTransaction);

        assertThrows(RuntimeException.class, () -> shopService.process(transactionList));
    }

    @Test
    void supplyOperation_Ok() {
        fruitTransaction.setFruit("apple");
        fruitTransaction.setOperation("s");
        fruitTransaction.setQuantity(10);
        transactionList.add(fruitTransaction);

        assertNotNull(storage);
    }

    @Test
    void supplyOperation_NotOk() {
        fruitTransaction.setFruit("apple");
        fruitTransaction.setOperation("s");
        fruitTransaction.setQuantity(-10);
        transactionList.add(fruitTransaction);

        assertThrows(RuntimeException.class, () -> shopService.process(transactionList));
    }

    @Test
    void purchaseOperation_Ok() {
        fruitTransaction.setFruit("apple");
        fruitTransaction.setOperation("b");
        fruitTransaction.setQuantity(10);
        transactionList.add(fruitTransaction);
        invalidFruitTransaction.setFruit("apple");
        invalidFruitTransaction.setOperation("p");
        invalidFruitTransaction.setQuantity(10);
        transactionList.add(invalidFruitTransaction);

        assertNotNull(storage);
    }

    @Test
    void purchaseOperation_NotOk() {
        invalidFruitTransaction.setFruit("apple");
        invalidFruitTransaction.setOperation("p");
        invalidFruitTransaction.setQuantity(10);
        transactionList.add(invalidFruitTransaction);

        assertThrows(RuntimeException.class, () -> shopService.process(transactionList));
    }

    @Test
    void returnOperation_Ok() {
        fruitTransaction.setFruit("apple");
        fruitTransaction.setOperation("r");
        fruitTransaction.setQuantity(10);
        transactionList.add(fruitTransaction);

        assertNotNull(storage);
    }

    @Test
    void returnOperation_NotOk() {
        fruitTransaction.setFruit("apple");
        fruitTransaction.setOperation("r");
        fruitTransaction.setQuantity(-10);
        transactionList.add(fruitTransaction);

        assertThrows(RuntimeException.class, () -> shopService.process(transactionList));
    }
}
