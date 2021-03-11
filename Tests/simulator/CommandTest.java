package simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    Command Commander = new Command(){
        @Override
        void commandMethod(){}
    };

    @Test
    void compToDecimal() {
        assertEquals(-81, Commander.compToDecimal("10101111"));
        assertEquals(-161, Commander.compToDecimal("101011111"));
        assertEquals(1, Commander.compToDecimal("01"));
        assertEquals(54, Commander.compToDecimal("000110110"));
        assertEquals(-1, Commander.compToDecimal("11"));
    }
}