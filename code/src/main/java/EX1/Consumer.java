package EX1;
/*
 *
 * @author Danilo Sambugaro created on 18/11/2019 inside the package - EX1
 *
 */

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Consumer implements Runnable {

    private boolean linked = false;
    private ArrayBlockingQueue<Integer> buffer;
    private LinkedBlockingDeque<Integer> bufferLinked;

    public Consumer(ArrayBlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    public Consumer(LinkedBlockingDeque<Integer> bufferLinked) {
        this.linked = true;
        this.bufferLinked = bufferLinked;
    }

    @Override
    public void run() {
        while (true) {
            try {

                Integer taked = null;

                if (linked) {
                    taked = bufferLinked.take();
                } else {
                    taked = buffer.take();
                }

                System.out.println("[ " + Thread.currentThread().getName() + " ] Consuming " + taked);

                Random r = new Random();
                // Gera um número aleatório entre 1000 e 5000
                int sleepTime = r.nextInt((5000 - 1000) + 1) + 1000;
                Thread.sleep(sleepTime); // Dorme por sleepTime milisegundos

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
