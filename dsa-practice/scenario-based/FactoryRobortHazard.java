public class FactoryRobortHazard {
  public static void main(String[] args) {
    java.util.Scanner sc = new java.util.Scanner(System.in);
    try {
      double armPrecision = sc.nextDouble();
      int workerDensity = sc.nextInt();
      sc.nextLine();
      String machineryState = sc.nextLine();

      RobotHazardAuditor auditor = new RobotHazardAuditor();
      double risk = auditor.CalculateHazardRisk(armPrecision, workerDensity, machineryState);
      System.out.printf("Robot Hazard Risk Score: %.1f%n", risk);
    } catch (RobotSafetyException e) {
      System.out.println(e.getMessage());
    } catch (java.util.NoSuchElementException e) {
      System.out.println("Error: Invalid input");
    } finally {
      sc.close();
    }
  }
}

class RobotHazardAuditor {
  public double CalculateHazardRisk(double armPrecision, int workerDensity, String machineryState)
      throws RobotSafetyException {
    if (armPrecision < 0.0 || armPrecision > 1.0) {
      throw new RobotSafetyException("Error: Arm precision must be 0.0-1.0");
    }
    if (workerDensity < 1 || workerDensity > 20) {
      throw new RobotSafetyException("Error: Worker density must be 1-20");
    }

    double machineRiskFactor;
    switch (machineryState) {
      case "Worn":
        machineRiskFactor = 1.3;
        break;
      case "Faulty":
        machineRiskFactor = 2.0;
        break;
      case "Critical":
        machineRiskFactor = 3.0;
        break;
      default:
        throw new RobotSafetyException("Error: Unsupported machinery state");
    }

    double hazardRisk = ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor);
    return hazardRisk;
  }
}

class RobotSafetyException extends Exception {
  public RobotSafetyException(String message) {
    super(message);
  }
}
