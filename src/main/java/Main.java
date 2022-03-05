import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String cipherText = Crypto.encrypt("test");
        LOG.info("cipherText: " + cipherText);
        String plainText = Crypto.decrypt(cipherText);
        LOG.info("plainText: " + plainText);
    }

}
