package EX2;
/*
 *
 * @author Danilo Sambugaro created on 19/11/2019 inside the package - EX2
 *
 */

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

    private PriorityBlockingQueue<Card> buffer;
    private Semaphore mySignal;

    public Consumer(PriorityBlockingQueue<Card> buffer, Semaphore mySignal) {
        this.buffer = buffer;
        this.mySignal = mySignal;
    }

    @Override
    public void run() {
        while (true) {

            try {
                for (int i = 0; i < 3; i++) {
                    Card take = buffer.take();
                    System.out.println("[ "+Thread.currentThread().getName()+" ] Consuming " + take.toString());
                }
                mySignal.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
