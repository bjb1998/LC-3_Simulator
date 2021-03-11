package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BRCommandTest {

    @Test
    void commandMethod() {
        Computer comp = new Computer();
        Computer.getREG()[0].setValue(5);
        Computer.setLastREG(0);
        Computer.setPC(5);
        BRCommand br = new BRCommand("0000111000001001"); //BR nzp #9
        br.commandMethod();
        assertEquals(14, Computer.getPC());

        Computer.setPC(5);
        Computer.setLastREG(0);
        br = new BRCommand("0000001000001001"); //BR p #9
        br.commandMethod();
        assertEquals(14, Computer.getPC());

        Computer.setPC(5);
        Computer.setLastREG(0);
        br = new BRCommand("0000110000001001"); //BR nz #9
        br.commandMethod();
        assertEquals(5, Computer.getPC());
    }

    @Test
    void testNZP(){
        Computer comp = new Computer();
        Computer.getREG()[1].setValue(0);
        ADDCommand add = new ADDCommand("0001001001000101"); //add R1 R1 #5
        add.commandMethod();
        Computer.setCC();
        assertEquals(1, Computer.getCC().getValue()); //Positive value of NZP

        add = new ADDCommand("0001001001111011"); //add R1 R1 #-5
        add.commandMethod();
        Computer.setCC();
        assertEquals(2, Computer.getCC().getValue()); //Zero value of NZP

        add.commandMethod();
        Computer.setCC();
        assertEquals(4, Computer.getCC().getValue()); //Negative value of NZP
    }
}