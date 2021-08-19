public class Main {
    public static void main(String[] args) {
        ProducerConsumerService producerConsumerService = new ProducerConsumerService(100, 100, 2000);
        producerConsumerService.startThreads();
    }
}
