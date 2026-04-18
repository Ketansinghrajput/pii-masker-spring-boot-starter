package masker;


import io.github.ketansingh.piimasker.masker.PiiMaskingUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PiiMaskingUtilTest {

    @Test
    void testFullPIIMasking() {
        String input = "My Aadhaar is 123456789012, Card 4111222233334444 and phone 9876543210";
        String output = PiiMaskingUtil.maskAll(input);

        assertTrue(output.contains("XXXX-XXXX-9012"), "Aadhaar should be masked");
        assertTrue(output.contains("XXXX-XXXX-XXXX-4444"), "Card should be masked");
        assertTrue(output.contains("XXXXXX3210"), "Phone should be masked");

        assertFalse(output.contains("123456789012"));
        assertFalse(output.contains("4111222233334444"));
    }

    @Test
    void testEmailMasking() {
        String input = "Contact sensei@gmail.com";
        String output = PiiMaskingUtil.maskAll(input);

        assertTrue(output.contains("s****@gmail.com"));
        assertFalse(output.contains("sensei@"));
    }

    @Test
    void testRecursionSafety() {
        // Circuit Breaker test
        String alreadyMasked = "Email: s****@gmail.com, Aadhaar: XXXX-XXXX-9012";
        String output = PiiMaskingUtil.maskAll(alreadyMasked);

        assertEquals(alreadyMasked, output);
    }
}