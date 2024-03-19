package auditornoya.four;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

class Patient implements Runnable {
    private static final Object mrtMonitor = new Object();
    private static volatile Patient currentMrtPatient = null;
    private static final AtomicInteger maxLength = new AtomicInteger(0);
    private static final ConcurrentLinkedQueue<Patient> queue = new ConcurrentLinkedQueue<>();
    private final String name;

    public Patient(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (mrtMonitor) {
            while (currentMrtPatient != null) {
                try {
                    mrtMonitor.wait(); // Ждем, пока МРТ освободится
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            currentMrtPatient = this; // Занимаем МРТ
        }

        // Имитация осмотра терапевтом
        simulateWork("Терапевт осматривает " + name);

        // Переход к МРТ
        simulateWork("МРТ обследует " + name);

        synchronized (mrtMonitor) {
            currentMrtPatient = null; // Освобождаем МРТ
            mrtMonitor.notify(); // Уведомляем ожидающих МРТ
        }
    }

    private void simulateWork(String action) {
        System.out.println(action);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 301)); // Случайная задержка
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void addPatientToQueue(Patient patient) {
        queue.add(patient);
        maxLength.updateAndGet(x -> Math.max(x, queue.size())); // Обновляем максимальную длину очереди
    }

    public static int getMaxQueueLength() {
        return maxLength.get();
    }
}