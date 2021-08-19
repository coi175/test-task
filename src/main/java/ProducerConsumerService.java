import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProducerConsumerService
{
    private final List<Integer> randoms = new ArrayList<>();
    private static final int RANDOM_NUMBER_RANGE = 100;
    private static final int RANDOM_NUMBERS_COUNT = 100;
    private static final int THREADS_SLEEP_TIME = 1000;
    public void startThreads()
    {
        producer.start();
        consumer.start();
    }

    private final Thread producer = new Thread(() ->
    {
        while (true)
        {
            Integer numsCount = new Random().nextInt(RANDOM_NUMBERS_COUNT);
            synchronized (randoms) {
                for (int i = 0; i < numsCount; i++)
                {
                    Integer number = new Random().nextInt(RANDOM_NUMBER_RANGE);
                    randoms.add(number);
                    System.out.print(number + " ");
                }
                System.out.println("\n Wrote by producer");
            }

            try
            {
                Thread.sleep(THREADS_SLEEP_TIME);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    });

    private final Thread consumer = new Thread(() ->
    {
        while (true)
        {
            synchronized (randoms) {
                randoms.forEach(x -> System.out.print(x + " "));
                randoms.clear();
                System.out.println("\n Read by consumer");
            }

            try
            {
                Thread.sleep(THREADS_SLEEP_TIME);
            }
            catch (InterruptedException e) {

            }
        }
    });
}
