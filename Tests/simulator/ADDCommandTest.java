package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ADDCommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        Computer.getREG()[1].setValue(5);
        Computer.getREG()[3].setValue(7);
        ADDCommand cmd = new ADDCommand("0001001001000011"); //ADD R1, R1, R3
        cmd.commandMethod();
        assertEquals(12, Computer.getREG()[1].getValue());

        Computer.getREG()[2].setValue(55);
        cmd = new ADDCommand("0001010010100001"); //ADD R2 R2 #1
        cmd.commandMethod();
        assertEquals(56, Computer.getREG()[2].getValue());

        Computer.getREG()[1].setValue(55);
        cmd = new ADDCommand("0001001001111111"); //ADD R1 R1 #-1
        cmd.commandMethod();
        assertEquals(54, Computer.getREG()[1].getValue());
    }
}