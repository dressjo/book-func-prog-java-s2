package fpij.ch5;

public class BondStrategy implements AssetStrategy {


  @Override
  public boolean check(Asset asset) {
    return asset.getType() == Asset.AssetType.BOND;
  }
}
