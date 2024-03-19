package auditornoya.one;

public class PrintThreadId implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread ID: " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        PrintThreadId task1 = new PrintThreadId();
        PrintThreadId task2 = new PrintThreadId();
        PrintThreadId task3 = new PrintThreadId();

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
