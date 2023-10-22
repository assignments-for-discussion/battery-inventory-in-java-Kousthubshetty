package bunchbysoh;

public class Util {
    // This method calculates the SoH for the given present and rated capacity
    public static float calculateSoH(int presentCapacity, int ratedCapacity) {
        float calculatedSoH = 0;
        try {
            if (presentCapacity > ratedCapacity) {
                throw new PresentCapacityCannotExceedRatedCapacityException();
            } else {
                calculatedSoH = 100 * presentCapacity / ratedCapacity;
            }
        } catch (PresentCapacityCannotExceedRatedCapacityException e) {
            System.err.println(e.getMessage());
        }
        return calculatedSoH;
    }

    // This method classifies the battery based on the SoH percentage
    public static String classifyBatteryBySoH(float SoH) {
        String status = "";
        try {
            if (SoH >= 80 && SoH <= 100) {
                status = "Healthy";
            } else if (SoH >= 63 && SoH < 80) {
                status = "Exchange";
            } else if (SoH < 63 && SoH >= 0) {
                status = "Failed";
            } else {
                throw new InvalidBatteryPercentageException();
            }
        } catch (InvalidBatteryPercentageException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }
}

// Exception is throwed when SoH percentage is greater than 100
class InvalidBatteryPercentageException extends Exception {
    public InvalidBatteryPercentageException() {
        super("Battery Percent can't be more than 100%.");
    }
}

// Exception is throwed when present capacity is greater than rated capacity
class PresentCapacityCannotExceedRatedCapacityException extends Exception {
    PresentCapacityCannotExceedRatedCapacityException() {
        super("Present Capacity can't exceed the Rated Capacity.");
    }
}