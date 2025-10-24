import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CryptoTest {

    @Test
    @DisplayName("Encrypt and decrypt simple text")
    public void testEncryptDecrypt() {
        String plainText = "test";
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Encrypt produces non-empty ciphertext")
    public void testEncryptProducesNonEmptyCiphertext() {
        String plainText = "hello world";
        String cipherText = Crypto.encrypt(plainText);

        assertNotNull(cipherText);
        assertFalse(cipherText.isEmpty());
        assertNotEquals(plainText, cipherText);
    }

    @Test
    @DisplayName("Multiple encryptions of same text produce different ciphertexts due to dynamic IV")
    public void testDynamicIV() {
        String plainText = "test message";
        String cipherText1 = Crypto.encrypt(plainText);
        String cipherText2 = Crypto.encrypt(plainText);

        assertNotEquals(cipherText1, cipherText2, "Ciphertexts should differ due to random IV");

        // But both should decrypt to the same plaintext
        assertEquals(plainText, Crypto.decrypt(cipherText1));
        assertEquals(plainText, Crypto.decrypt(cipherText2));
    }

    @Test
    @DisplayName("Encrypt and decrypt empty string")
    public void testEncryptDecryptEmptyString() {
        String plainText = "";
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Encrypt and decrypt string with special characters")
    public void testEncryptDecryptSpecialCharacters() {
        String plainText = "!@#$%^&*()_+-=[]{}|;':\",./<>?";
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Encrypt and decrypt string with Unicode characters")
    public void testEncryptDecryptUnicode() {
        String plainText = "Hello 世界 🌍 Привет";
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Encrypt and decrypt long string")
    public void testEncryptDecryptLongString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("This is a long test string. ");
        }
        String plainText = sb.toString();

        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Encrypt and decrypt string with newlines and tabs")
    public void testEncryptDecryptWhitespace() {
        String plainText = "Line 1\nLine 2\tTabbed\rCarriage Return";
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Encrypt and decrypt numbers")
    public void testEncryptDecryptNumbers() {
        String plainText = "1234567890";
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Decrypt with invalid ciphertext returns empty string")
    public void testDecryptInvalidCiphertext() {
        String invalidCipherText = "invalid_base64_string";
        String decryptedText = Crypto.decrypt(invalidCipherText);

        assertEquals("", decryptedText);
    }

    @Test
    @DisplayName("Decrypt with empty string returns empty string")
    public void testDecryptEmptyCiphertext() {
        String decryptedText = Crypto.decrypt("");

        assertEquals("", decryptedText);
    }

    @Test
    @DisplayName("Ciphertext is Base64 encoded")
    public void testCiphertextIsBase64() {
        String plainText = "test";
        String cipherText = Crypto.encrypt(plainText);

        // Base64 should only contain alphanumeric characters, +, /, and =
        assertTrue(cipherText.matches("^[A-Za-z0-9+/=]+$"),
                  "Ciphertext should be valid Base64");
    }

    @Test
    @DisplayName("Same ciphertext decrypts to same plaintext consistently")
    public void testDecryptConsistency() {
        String plainText = "consistency test";
        String cipherText = Crypto.encrypt(plainText);

        String decrypted1 = Crypto.decrypt(cipherText);
        String decrypted2 = Crypto.decrypt(cipherText);
        String decrypted3 = Crypto.decrypt(cipherText);

        assertEquals(decrypted1, decrypted2);
        assertEquals(decrypted2, decrypted3);
        assertEquals(plainText, decrypted1);
    }

    @Test
    @DisplayName("Encrypt and decrypt single character")
    public void testEncryptDecryptSingleCharacter() {
        String plainText = "A";
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Encrypt and decrypt JSON string")
    public void testEncryptDecryptJson() {
        String plainText = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        assertEquals(plainText, decryptedText);
    }
}