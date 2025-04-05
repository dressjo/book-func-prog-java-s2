package fpij.ch5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fpij.ch5.Asset.AssetType;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class SeparationOfConcern {

  @Test
  public void oldSchoolStrategy() {
    final var assets = getAssets();

    int totalAssetValues = AssetUtil.totalAssetValues(assets, (asset -> new BondStrategy().check(asset)));
    assertEquals(3000, totalAssetValues);

    totalAssetValues = AssetUtil.totalAssetValues(assets, (asset -> new StockStrategy().check(asset)));
    assertEquals(3000, totalAssetValues);
  }

  @Test
  public void totalAssetValues() {
    final var assets = getAssets();

    Predicate<Asset> allSelector = (asset) -> true;

    final int totalAssetValues = AssetUtil.totalAssetValues(assets, allSelector);

    assertEquals(10000, totalAssetValues);
  }

  @Test
  public void totalBondValues() {
    final var assets = getAssets();

    Predicate<Asset> bondSelector = (asset) -> asset.getType() == Asset.AssetType.BOND;
    final int totalAssetValues = AssetUtil.totalAssetValues(assets, bondSelector);

    assertEquals(3000, totalAssetValues);
  }

  @Test
  public void totalStockValues() {
    final var assets = getAssets();

    Predicate<Asset> stockSelector = (asset) -> asset.getType() == Asset.AssetType.STOCK;
    final int totalAssetValues = AssetUtil.totalAssetValues(assets, stockSelector);

    assertEquals(7000, totalAssetValues);
  }

  private static List<Asset> getAssets() {
    final var assets = Arrays.asList(
        new Asset(Asset.AssetType.BOND, 1000),
        new Asset(Asset.AssetType.BOND, 2000),
        new Asset(Asset.AssetType.STOCK, 3000),
        new Asset(Asset.AssetType.STOCK, 4000)
    );
    return assets;
  }

}

class Asset {

  public enum AssetType {BOND, STOCK}

  private final AssetType type;
  private final int value;

  public Asset(final AssetType assetType, final int assetValue) {
    type = assetType;
    value = assetValue;
  }

  public AssetType getType() {
    return type;
  }

  public int getValue() {
    return value;
  }
}

class AssetUtil {

  public static int totalAssetValues(final List<Asset> assets) {
    return assets
        .stream()
        .mapToInt(Asset::getValue)
        .sum();
  }

  public static int totalStockValues(final List<Asset> assets) {
    return assets.stream()
        .mapToInt(asset ->
            asset.getType() == AssetType.STOCK ? asset.getValue() : 0)
        .sum();
  }

  public static int totalAssetValues(final List<Asset> assets, Predicate<Asset> assetSelector) {
    return assets.stream()
        .filter(assetSelector)
        .mapToInt(Asset::getValue)
        .sum();
  }

}
