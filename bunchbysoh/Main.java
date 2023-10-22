package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();

    // All present capacities are classified based on the SoH percentage.
    for (int presentCapacity : presentCapacities) {
      float SoH = Util.calculateSoH(presentCapacity, 120);
      String classifiedBasedOnSoH = Util.classifyBatteryBySoH(SoH);

      switch (classifiedBasedOnSoH) {
        case "Healthy":
          counts.healthy++;
          break;
        case "Exchange":
          counts.exchange++;
          break;
        case "Failed":
          counts.failed++;
          break;
      }
    }

    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = { 113, 116, 80, 95, 92, 70 };
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert (counts.healthy == 2);
    assert (counts.exchange == 3);
    assert (counts.failed == 1);
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}