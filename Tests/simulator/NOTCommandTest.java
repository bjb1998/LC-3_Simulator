package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NOTCommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        Computer.getREG()[5].setValue(48);
        NOTCommand not = new NOTCommand("1001101101111111");//NOT R5, R5
        not.commandMethod();
        assertEquals(-49, Computer.getREG()[5].getValue2sComp());

        Computer.getREG()[3].setValue2sComp(-64);
        not = new NOTCommand("1001010011111111"); //NOT R2, R3
        not.commandMethod();
        assertEquals(63, Computer.getREG()[2].getValue2sComp());
    }
}