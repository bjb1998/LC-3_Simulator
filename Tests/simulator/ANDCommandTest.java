package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ANDCommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        ANDCommand and = new ANDCommand("0101000000100000"); //AND R0 R0 #0 (Clear R0))
        Computer.getREG()[0].setValue(78);
        and.commandMethod();
        assertEquals(0, Computer.getREG()[0].getValue());

        Computer.getREG()[0].setValue(12);
        and = new ANDCommand("0101000000100111"); //AND R0 R0 #7
        and.commandMethod();
        assertEquals(4, Computer.getREG()[0].getValue());

        Computer.getREG()[0].setValue(12);
        and = new ANDCommand("0101000000111001"); //AND R0 R0 #-7
        and.commandMethod();
        assertEquals(8, Computer.getREG()[0].getValue());

        Computer.getREG()[5].setValue(7);
        Computer.getREG()[0].setValue(12);
        and = new ANDCommand("0101000000011101"); //AND R0 R0 R5
        and.commandMethod();
        assertEquals(4, Computer.getREG()[0].getValue());
    }
}