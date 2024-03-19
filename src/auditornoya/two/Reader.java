package auditornoya.two;
import java.util.concurrent.CopyOnWriteArrayList;

public class Reader implements Runnable {
    private CopyOnWriteArrayList<Integer> listOfNumbers;

    public Reader(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(500);
                System.out.println("Reading list: " + listOfNumbers);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
