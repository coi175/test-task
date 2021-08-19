import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProducerConsumerService
{
    private static final Logger log = Logger.getLogger(ProducerConsumerService.class);
    private final List<Integer> randoms = new ArrayList<>();
    private int randomNumberRange;
    private int randomNumberCount;
    private int threadsSleepTime;

    public ProducerConsumerService(final int randomNumberRange, final int randomNumberCount, final int threadsSleepTime) {
        this.randomNumberRange = randomNumberRange;
        this.randomNumberCount = randomNumberCount;
        this.threadsSleepTime = threadsSleepTime;
    }

    public void startThreads()
    {
        producer.start();
        consumer.start();
    }

    private final Thread producer = new Thread(() ->
    {
        while (true)
        {
            synchronized (randoms) {
                log.info("Producer started writing");
                Integer numsCount = new Random().nextInt(randomNumberCount);
                for (int i = 0; i < numsCount; i++)
                {
                    Integer number = new Random().nextInt(randomNumberRange);
                    randoms.add(number);
                    System.out.print(number + " ");
                }
                log.info("Producer finished writing");
                System.out.println("\n Wrote by producer");
            }

            try
            {
                Thread.sleep(threadsSleepTime);
            }
            catch (InterruptedException e)
            {
                log.error("Error with producer sleep");
            }
        }
    });

    private final Thread consumer = new Thread(() ->
    {
        while (true)
        {
            synchronized (randoms) {
                log.info("Consumer started reading");
                randoms.forEach(x -> System.out.print(x + " "));
                log.info("Consumer finished reading");
                randoms.clear();
                log.info("Consumer cleared randoms");
                System.out.println("\n Read by consumer");
            }

            try
            {
                Thread.sleep(threadsSleepTime);
            }
            catch (InterruptedException e) {
                log.error("Error with consumer sleep");
            }
        }
    });
}
