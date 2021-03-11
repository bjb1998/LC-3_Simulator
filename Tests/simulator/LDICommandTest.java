package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LDICommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        LDICommand ldi = new LDICommand("1010011000000010"); //LDI R3, #2
        Computer.setPC(5);
        Computer.getMEM()[7].setValue(34);
        ldi.commandMethod();
        assertEquals(34, Computer.getREG()[3].getValue());

        ldi = new LDICommand("1010011111111110"); //LDI R3, #-2
        Computer.getMEM()[3].setValue2sComp(77);
        ldi.commandMethod();
        assertEquals(77, Computer.getREG()[3].getValue());
    }
}