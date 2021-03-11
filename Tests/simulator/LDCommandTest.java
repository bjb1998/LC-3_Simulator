package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LDCommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        Computer.setPC(5);
        Computer.getMEM()[0].setValue(55);
        Computer.getMEM()[10].setValue(16);
        LDCommand ld = new LDCommand("0010000111111011"); //LD R0 #-5
        ld.commandMethod();
        assertEquals(55, Computer.getREG()[0].getValue());

        ld = new LDCommand("0010001000000101"); //LD R1 #5
        ld.commandMethod();
        assertEquals(16, Computer.getREG()[1].getValue());
    }
}