import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyClass1 {

    //sample logger instance for MyClass1
    private static final Logger logger = LogManager.getLogger(MyClass1.class);

    public void doSomething() {
        try {
            // Hata üreten bir işlem örneği
            int result = 1 / 0;
        } catch (ArithmeticException e) {
            // Hata yakalandığında logla
            logger.error("ArithmeticException oluştu: ", e);
            // Log olayını merkezi kuyruğa ekle
            LogEventQueue.getInstance().addLogEvent("MyClass1: " + e.getMessage());
        }
    }
}
