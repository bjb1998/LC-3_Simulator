package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HALTCommandTest {

    @Test
    void commandMethod() { //is there really much to test here?
        Computer comp = new Computer();
        HALTCommand hlt = new HALTCommand();
        hlt.commandMethod();
        assertEquals(50, Computer.getPC());
    }
}