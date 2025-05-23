package fpij.ch9;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class Stocks100 {

    @Test
    void getStockPrices_returnGreaterThan100() {
        BigDecimal oneHundred = new BigDecimal("100");
        String tickers = Tickers
                .symbols
                .stream()
                .filter(symbol -> FinanceData.getPrice(symbol).compareTo(oneHundred) > 0)
                .sorted()
                .collect(Collectors.joining(","));

        System.out.println(tickers);
    }

}
