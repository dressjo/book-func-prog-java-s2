package fpij.ch6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionTests {

  @Test
  public void verboseExceptionTest() {
    final RodCutter rodCutter = new RodCutter();
    rodCutter.setPrices(new int[] {1, 2, 3, 4});
    try {
      rodCutter.maxProfit(0);
      Assertions.fail("Expected exception for zero length");
    } catch(RodCutterException ex) {
      Assertions.assertTrue(true);
    }
  }

  @Test
  public void lessVerboseExceptionTest() {
    final RodCutter rodCutter = new RodCutter();
    rodCutter.setPrices(new int[] {1, 2, 3, 4});
    final RodCutterException rodCutterException = Assertions.assertThrows(RodCutterException.class,
        () -> rodCutter.maxProfit(0));

    Assertions.assertEquals("Max profit must be greater than zero", rodCutterException.getMessage());
  }

}

class RodCutterException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public RodCutterException(String message) {
    super(message);
  }
}

class RodCutter {
  private int[] prices;

  public void setPrices(final int[] prices) {
    this.prices = prices;
  }

  public int maxProfit(final int length) {
    if(length == 0) {
      throw new RodCutterException("Max profit must be greater than zero");
    }
    return 0;
  }
}