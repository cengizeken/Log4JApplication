import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyClass2 {
    //sample logger instance for MyClass2
    private static final Logger logger = LogManager.getLogger(MyClass2.class);

    public void doAnotherThing() {
        try {
            // Başka işlemler
            throw new NullPointerException("NullPointerException oluştu");
        } catch (NullPointerException e) {
            // Hata yakalandığında logla
            logger.error("NullPointerException oluştu: ", e);
            // Log olayını merkezi kuyruğa ekle
            LogEventQueue.getInstance().addLogEvent("MyClass2: " + e.getMessage());
        }
    }
}
