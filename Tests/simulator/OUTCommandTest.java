package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OUTCommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        OUTCommand cmd = new OUTCommand();

        Computer.getREG()[0].setValue(72);
        cmd.commandMethod();
        assertEquals('H', cmd.c);

        Computer.getREG()[0].setValue(34);
        cmd.commandMethod();
        assertEquals('\"', cmd.c);

        Computer.getREG()[0].setValue(95);
        cmd.commandMethod();
        assertEquals('_', cmd.c);
    }
}