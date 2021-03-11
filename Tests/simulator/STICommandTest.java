package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class STICommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        Computer.getREG()[2].setValue2sComp(-7);
        Computer.setPC(10);
        STICommand sti = new STICommand("1011010000000101"); //STI R2, #5
        sti.commandMethod();
        assertEquals(-7, Computer.getMEM()[15].getValue2sComp());

        sti = new STICommand("1011010111111011"); //STI R2, #-5
        sti.commandMethod();
        assertEquals(-7, Computer.getMEM()[5].getValue2sComp());

    }
}