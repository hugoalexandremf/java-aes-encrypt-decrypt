import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String cipherText = Crypto.encrypt("test");
        logger.info("cipherText: " + cipherText);
        String plainText = Crypto.decrypt(cipherText);
        logger.info("plainText: " + plainText);
    }

}
