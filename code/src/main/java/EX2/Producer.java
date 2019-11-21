package EX2;
/*
 *
 * @author Danilo Sambugaro created on 19/11/2019 inside the package - EX2
 *
 */

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

    private PriorityBlockingQueue<Card> buffer;
    private Integer qtProduced;
    private Semaphore mutex;
    private Semaphore consumer1;
    private Semaphore consumer2;

    public Producer(PriorityBlockingQueue<Card> buffer, Integer qtProduced, Semaphore mutex, Semaphore consumer1, Semaphore consumer2) {
        this.buffer = buffer;
        this.qtProduced = qtProduced;
        this.mutex = mutex;
        this.consumer1 = consumer1;
        this.consumer2 = consumer2;
    }


    @Override
    public void run() {
        Random r = new Random();

        while (true) {
            while (qtProduced < 10) {

                // Gera um número aleatório entre 1 e 13
                Integer symbol = r.nextInt((13 - 1) + 1) + 1;

                Card card = null;

                Suit randomSuit = Suit.getRandomSuit();
                if (symbol.equals(1)) {
                    card = new Card('a', randomSuit);
                } else if (symbol.equals(11)) {
                    card = new Card('j', randomSuit);
                } else if (symbol.equals(12)) {
                    card = new Card('q', randomSuit);
                } else if (symbol.equals(13)) {
                    card = new Card('k', randomSuit);
                } else {
                    card = new Card(symbol, randomSuit);
                }

                System.out.println("[ " + Thread.currentThread().getName() + " ] Producing " + card.toString());

                try {
                    mutex.acquire();
                    buffer.put(card);
                    qtProduced++;
                    mutex.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                }


                // Gera um número aleatório entre 1000 e 5000
                int sleepTime = r.nextInt((5000 - 1000) + 1) + 1000;
                try {
                    Thread.sleep(sleepTime); // Dorme por sleepTime milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            try {
                consumer1.acquire();
                consumer2.acquire();
                mutex.acquire();

                if (qtProduced == 10){
                    qtProduced = 0;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release();
                consumer1.release();
                consumer2.release();
            }


        }
    }
}
