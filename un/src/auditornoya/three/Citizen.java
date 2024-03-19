package auditornoya.three;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
class Citizen extends Thread {
    private static final ServiceWindow[] windows = {
            new ServiceWindow(CitizenType.YOUNG, CitizenType.ELDERLY, CitizenType.BUSINESS),
            new ServiceWindow(CitizenType.ELDERLY),
            new ServiceWindow(CitizenType.BUSINESS)
    };
    private static final AtomicInteger[] deniedCounts = new AtomicInteger[]{
            new AtomicInteger(0),
            new AtomicInteger(0),
            new AtomicInteger(0)
    };
    private CitizenType type;

    public Citizen(CitizenType type) {
        this.type = type;
    }

    public CitizenType getType() {
        return type;
    }

    @Override
    public void run() {
        ServiceWindow window = windows[new Random().nextInt(windows.length)];
        if (!window.tryServe(this)) {
            deniedCounts[type.ordinal()].incrementAndGet();
        }
    }

    public static int getDeniedCount(CitizenType type) {
        return deniedCounts[type.ordinal()].get();
    }
}