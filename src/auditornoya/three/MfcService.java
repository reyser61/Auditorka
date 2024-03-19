package auditornoya.three;
import java.util.*;

public class MfcService {
    public static void main(String[] args) throws InterruptedException {
        int totalCount = 100;
        List<Citizen> citizens = new ArrayList<>();

        for (int i = 0; i < totalCount; i++) {
            citizens.add(new Citizen(CitizenType.YOUNG));
            citizens.add(new Citizen(CitizenType.ELDERLY));
            citizens.add(new Citizen(CitizenType.BUSINESS));
        }

        for (Citizen citizen : citizens) {
            citizen.start();
        }

        for (Citizen citizen : citizens) {
            citizen.join();
        }

        for (CitizenType type : CitizenType.values()) {
            int deniedCount = Citizen.getDeniedCount(type);
            System.out.printf("%s: %.2f%% клиентов ушли%n", type, (deniedCount / (double) totalCount) * 100);
        }
    }
}