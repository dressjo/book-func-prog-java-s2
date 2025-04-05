package fpij.ch5;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Scanner;
import java.util.function.Function;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Delegation {

  @Test
  public void computeStockWorth() {
    final CalculateNAV calculateNAV =
        new CalculateNAV(ticker -> new BigDecimal("6.01"));
    BigDecimal expected = new BigDecimal("6010.00");
    BigDecimal actual = calculateNAV.computeStockWorth("GOOG", 1000);
    BigDecimal delta = actual.subtract(expected);
    Assertions.assertEquals(0, delta.doubleValue(), 0.001);
  }

  @Test
  public void computeStockWorthReal() {
    final CalculateNAV calculateNav = new CalculateNAV(FinanceData::getPrice);
    System.out.println(String.format("100 shares of Apple worth: $%.2f",
        calculateNav.computeStockWorth("AAPL", 100)));
  }

}

class CalculateNAV {

  private Function<String, BigDecimal> priceFinder;

  public CalculateNAV(final Function<String, BigDecimal> aPriceFinder) {
    priceFinder = aPriceFinder;
  }

  public BigDecimal computeStockWorth(
      final String ticker, final int shares) {
    return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
  }

}

class FinanceData {
  public static BigDecimal getPrice(final String ticker) {
    try {
      final String URL =
          "https://eodhistoricaldata.com/api/eod/%s.US?%s&%s&%s";

      final var url = new URI(String.format(URL,
          ticker,
          "fmt=json",
          "filter=last_close",
          "api_token=OeAFFmMliFG5orCUuwAKQ8l4WWFQ67YX")).toURL();
      try(Scanner scanner = new Scanner(url.openStream())) {
        return new BigDecimal(scanner.nextLine());
      }
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}