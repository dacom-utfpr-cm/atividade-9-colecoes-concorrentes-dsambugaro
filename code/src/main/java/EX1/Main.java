package EX1;
/*
 * Implemente duas versões do problema do produtor/consumidor com M produtores e N consumidores usando
 * ArrayBlockingQueue e LinkedBlockingQueue. Compare o desempenho das duas implementações.
 *
 * @author Danilo Sambugaro created on 18/11/2019 inside the package - EX1
 *
 */

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {
        int capacity = 5;
        ArrayBlockingQueue<Integer> bufferArray = new ArrayBlockingQueue<>(capacity);
        LinkedBlockingDeque<Integer> bufferLinked = new LinkedBlockingDeque<>(capacity);

        Producer producer = new Producer(bufferArray);
        Consumer consumer = new Consumer(bufferArray);

        Producer producerLinked = new Producer(bufferLinked);
        Consumer consumerLinked = new Consumer(bufferLinked);

        Random r = new Random();
        int qtProducersConsumers = r.nextInt((5 - 2) + 1) + 2; // Gera um número aleatório entre 2 e 5

        // Cria qtProducersConsumers threads de produtores e consumidores e as inicia
        for (int i = 0; i < qtProducersConsumers; i++) {
            Thread producerThread = new Thread(producer, "ProducerThread " + i);
            Thread consumerThread = new Thread(consumer, "ConsumerThread " + i);

            producerThread.start();
            consumerThread.start();

            Thread producerLinkedThread = new Thread(producerLinked, "ProducerLinkedThread " + i);
            Thread consumerLinkedThread = new Thread(consumerLinked, "ConsumerLinkedThread " + i);
            producerLinkedThread.start();
            consumerLinkedThread.start();

        }

    }
}
