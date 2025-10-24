import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CryptoTest {

    @Test
    @DisplayName("Should decrypt to original plaintext when encrypting simple text")
    public void shouldDecryptToOriginalPlaintextWhenEncryptingSimpleText() {
        // given
        String plainText = "test";

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Should produce non-empty ciphertext when encrypting plaintext")
    public void shouldProduceNonEmptyCiphertextWhenEncryptingPlaintext() {
        // given
        String plainText = "hello world";

        // when
        String cipherText = Crypto.encrypt(plainText);

        // then
        assertNotNull(cipherText);
        assertFalse(cipherText.isEmpty());
        assertNotEquals(plainText, cipherText);
    }

    @Test
    @DisplayName("Should produce different ciphertexts when encrypting same text multiple times due to dynamic IV")
    public void shouldProduceDifferentCiphertextsWhenEncryptingSameTextMultipleTimesGivenDynamicIV() {
        // given
        String plainText = "test message";

        // when
        String cipherText1 = Crypto.encrypt(plainText);
        String cipherText2 = Crypto.encrypt(plainText);

        // then
        assertNotEquals(cipherText1, cipherText2, "Ciphertexts should differ due to random IV");
        assertEquals(plainText, Crypto.decrypt(cipherText1));
        assertEquals(plainText, Crypto.decrypt(cipherText2));
    }

    @Test
    @DisplayName("Should decrypt to empty string when encrypting empty string")
    public void shouldDecryptToEmptyStringWhenEncryptingEmptyString() {
        // given
        String plainText = "";

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Should decrypt to original text when encrypting string with special characters")
    public void shouldDecryptToOriginalTextWhenEncryptingStringWithSpecialCharacters() {
        // given
        String plainText = "!@#$%^&*()_+-=[]{}|;':\",./<>?";

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Should decrypt to original text when encrypting string with Unicode characters")
    public void shouldDecryptToOriginalTextWhenEncryptingStringWithUnicodeCharacters() {
        // given
        String plainText = "Hello 世界 🌍 Привет";

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Should decrypt to original text when encrypting long string")
    public void shouldDecryptToOriginalTextWhenEncryptingLongString() {
        // given
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("This is a long test string. ");
        }
        String plainText = sb.toString();

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Should decrypt to original text when encrypting string with newlines and tabs")
    public void shouldDecryptToOriginalTextWhenEncryptingStringWithWhitespaceCharacters() {
        // given
        String plainText = "Line 1\nLine 2\tTabbed\rCarriage Return";

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Should decrypt to original text when encrypting numeric string")
    public void shouldDecryptToOriginalTextWhenEncryptingNumericString() {
        // given
        String plainText = "1234567890";

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Should return empty string when decrypting invalid ciphertext")
    public void shouldReturnEmptyStringWhenDecryptingInvalidCiphertext() {
        // given
        String invalidCipherText = "invalid_base64_string";

        // when
        String decryptedText = Crypto.decrypt(invalidCipherText);

        // then
        assertEquals("", decryptedText);
    }

    @Test
    @DisplayName("Should return empty string when decrypting empty ciphertext")
    public void shouldReturnEmptyStringWhenDecryptingEmptyCiphertext() {
        // given
        String emptyCipherText = "";

        // when
        String decryptedText = Crypto.decrypt(emptyCipherText);

        // then
        assertEquals("", decryptedText);
    }

    @Test
    @DisplayName("Should produce Base64 encoded ciphertext when encrypting plaintext")
    public void shouldProduceBase64EncodedCiphertextWhenEncryptingPlaintext() {
        // given
        String plainText = "test";

        // when
        String cipherText = Crypto.encrypt(plainText);

        // then
        assertTrue(cipherText.matches("^[A-Za-z0-9+/=]+$"),
                  "Ciphertext should be valid Base64");
    }

    @Test
    @DisplayName("Should consistently decrypt to same plaintext when decrypting same ciphertext multiple times")
    public void shouldConsistentlyDecryptToSamePlaintextWhenDecryptingSameCiphertextMultipleTimes() {
        // given
        String plainText = "consistency test";
        String cipherText = Crypto.encrypt(plainText);

        // when
        String decrypted1 = Crypto.decrypt(cipherText);
        String decrypted2 = Crypto.decrypt(cipherText);
        String decrypted3 = Crypto.decrypt(cipherText);

        // then
        assertEquals(decrypted1, decrypted2);
        assertEquals(decrypted2, decrypted3);
        assertEquals(plainText, decrypted1);
    }

    @Test
    @DisplayName("Should decrypt to original character when encrypting single character")
    public void shouldDecryptToOriginalCharacterWhenEncryptingSingleCharacter() {
        // given
        String plainText = "A";

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }

    @Test
    @DisplayName("Should decrypt to original JSON when encrypting JSON string")
    public void shouldDecryptToOriginalJsonWhenEncryptingJsonString() {
        // given
        String plainText = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

        // when
        String cipherText = Crypto.encrypt(plainText);
        String decryptedText = Crypto.decrypt(cipherText);

        // then
        assertEquals(plainText, decryptedText);
    }
}