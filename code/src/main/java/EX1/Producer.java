package EX1;
/*
 *
 * @author Danilo Sambugaro created on 18/11/2019 inside the package - EX1
 *
 */

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Producer implements Runnable {

    private  boolean linked = false;

    private ArrayBlockingQueue<Integer> buffer;
    private LinkedBlockingDeque<Integer> bufferLinked;

    public Producer(ArrayBlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    public Producer(LinkedBlockingDeque<Integer> buffer) {
        this.linked = true;
        this.bufferLinked = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {

                Random r = new Random();

                if (linked) {
                    bufferLinked.put(r.nextInt());
                } else {
                    buffer.put(r.nextInt());
                }
                System.out.println("[ " + Thread.currentThread().getName() + " ] Producing... ");

                // Gera um número aleatório entre 1000 e 5000
                int sleepTime = r.nextInt((5000 - 1000) + 1) + 1000;
                Thread.sleep(sleepTime); // Dorme por sleepTime milisegundos

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
