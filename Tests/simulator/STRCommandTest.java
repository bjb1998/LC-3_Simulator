package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class STRCommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        Computer.getREG()[1].setValue2sComp(-5);
        Computer.getREG()[2].setValue(3);
        STRCommand str = new STRCommand("0111001010000011"); //STR R1 R2 #3
        str.commandMethod();
        assertEquals(-5, Computer.getMEM()[6].getValue2sComp());

        str = new STRCommand("0111001010111110"); //STR R1 R2 #-2
        str.commandMethod();
        assertEquals(-5, Computer.getMEM()[1].getValue2sComp());

    }
}