import java.util.ArrayList;
import java.util.List;

public class ProcucerConsumerService
{
    private final List<Integer> randoms = new ArrayList<>();
    
    public void startThreads()
    {
        producer.start();
        consumer.start();
    }

    private final Thread producer = new Thread(() ->
    {
        synchronized (randoms) {

        }
    });

    private final Thread consumer = new Thread(() ->
    {
        synchronized (randoms) {

        }
    });
}
