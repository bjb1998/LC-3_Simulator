package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LDRCommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        Computer.getREG()[3].setValue(5);
        LDRCommand ldr = new LDRCommand("0110010011000010"); //LDR R2 R3 #2
        Computer.getMEM()[7].setValue(55);
        ldr.commandMethod();
        assertEquals(55, Computer.getREG()[2].getValue2sComp());

        Computer.getMEM()[2].setValue(78);
        ldr = new LDRCommand("0110010011111101"); //LDR R2 R3 #-3
        ldr.commandMethod();
        assertEquals(78, Computer.getREG()[2].getValue2sComp());
    }
}