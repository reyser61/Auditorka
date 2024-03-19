package auditornoya.two;
import java.util.concurrent.CopyOnWriteArrayList;

public class Writer implements Runnable {
    private CopyOnWriteArrayList<Integer> listOfNumbers;

    public Writer(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        int numberToAdd = 1;
        try {
            while (true) {
                Thread.sleep(1000);
                listOfNumbers.add(numberToAdd++);
                System.out.println("Added: " + (numberToAdd - 1));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}