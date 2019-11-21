package EX2;
/*
 * Implemente o problema do produtor/consumidor para uma estrutura com os seguintes campos: número, simbolo, naipe,
 * que representa uma carta de baralho. A implementação deve possibilitar que após 10 cartas produzidas por
 * dois produtores, outros dois consumidores pegarão somente as maiores cartas. Os produtores somente devem produzir
 * mais cartas após os consumidores removerem 3 cartas cada um. As cartas remanescentes podem continuar na estrutura.
 * Use a ordenação do baralho da menor para maior: A, 2, ..., 10, Q, J, K.
 *
 * @author Danilo Sambugaro created on 18/11/2019 inside the package - EX2
 *
 */

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1);
        Semaphore consumer1 = new Semaphore(0);
        Semaphore consumer2 = new Semaphore(0);

        Integer qtProduced = 0;

        PriorityBlockingQueue<Card> buffer = new PriorityBlockingQueue<>(50);

        Producer producer1 = new Producer(buffer, qtProduced, mutex, consumer1, consumer2);
        Producer producer2 = new Producer(buffer, qtProduced, mutex, consumer1, consumer2);

        Consumer consumer_1 = new Consumer(buffer, consumer1);
        Consumer consumer_2 = new Consumer(buffer, consumer1);

        new Thread(producer1, "producer 1").start();
        new Thread(producer2, "producer 2").start();
        new Thread(consumer_1, "consumer 1").start();
        new Thread(consumer_2, "consumer 2").start();

    }
}
