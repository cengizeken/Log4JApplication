import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogEventQueue {

    private static final LogEventQueue instance = new LogEventQueue();
    private final BlockingQueue<String> logQueue;

    private LogEventQueue() {
        logQueue = new LinkedBlockingQueue<>();
    }

    public static LogEventQueue getInstance() {
        return instance;
    }
    //Add log event
    public void addLogEvent(String logEvent) {
        logQueue.offer(logEvent);
    }

    public String poll() {
        return logQueue.poll();
    }
}
