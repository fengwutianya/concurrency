package blockingQueueProductorConsumer;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xuan on 17-3-22.
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, File root) {
        this.fileQueue = fileQueue;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles();
        if (entries != null) {
            for (File entry: entries) {
                if (entry.isDirectory())
                    crawl(entry);
                else
                    fileQueue.put(entry);
            }
        }
    }
}
