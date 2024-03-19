package auditornoya.three;
import java.util.*;


class ServiceWindow {
    boolean isBusy = false;
    List<CitizenType> supportedTypes;

    public ServiceWindow(CitizenType... supportedTypes) {
        this.supportedTypes = Arrays.asList(supportedTypes);
    }

    synchronized boolean tryServe(Citizen citizen) {
        if (!isBusy && supportedTypes.contains(citizen.getType())) {
            isBusy = true;
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                isBusy = false;
            }
            return true;
        }
        return false;
    }
}
