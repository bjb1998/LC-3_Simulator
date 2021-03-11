package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LEACommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        LEACommand lea = new LEACommand("1110100000000101"); //LEA R4 #5
        Computer.setPC(9);
        lea.commandMethod();
        assertEquals(14, Computer.getREG()[4].getValue());

        lea = new LEACommand("1110100111110111"); //LEA R4 #-9
        Computer.setPC(18);
        lea.commandMethod();
        assertEquals(9, Computer.getREG()[4].getValue());
    }
}