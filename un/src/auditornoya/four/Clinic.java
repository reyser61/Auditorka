package auditornoya.four;

import java.util.concurrent.ThreadLocalRandom;

public class Clinic {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 20; i++) {
            Patient patient = new Patient("Пациент " + i);
            Patient.addPatientToQueue(patient);
            new Thread(patient).start();
            Thread.sleep(ThreadLocalRandom.current().nextInt(300, 601));
        }

        Thread.sleep(10000);
        System.out.println("Максимальная длина очереди: " + Patient.getMaxQueueLength());
    }
}
