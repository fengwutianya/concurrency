package blockingQueueProductorConsumer;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by xuan on 17-3-22.
 */
public class Index {
    private static final int BOUND = 10;
    private static final int CONSUMER_NUM = 10;

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingDeque<>(BOUND);

        for (File file: roots) {
            new Thread(new FileCrawler(queue, file)).start();
        }

        for (int i = 0; i < CONSUMER_NUM; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
