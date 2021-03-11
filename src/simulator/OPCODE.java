package simulator;

public interface OPCODE {
    /**@return enum the various opcodes for the LC-3*/
    enum Opcode{
        ADD("ADD", "0001"),
        AND("AND", "0101"),
        LD("LD", "0010"),
        BR("BR", "0000"),
        OUT("OUT", "1111000000100001"),
        HALT("HALT", "1111000000100101"),
        //EXTRA CREDIT BELOW ONLY
        LEA("LEA", "1110"),
        LDI("LDI", "1010"),
        STI("STI", "1011"),
        LDR("LDR", "0110"),
        STR("STR", "0111");

        private final String code;
        private final String binaryOp;

        Opcode(String code, String binaryOp){
            this.code = code;
            this.binaryOp = binaryOp;
        }

        public String getCode(){ return code; }
        public String getBinaryOp(){ return binaryOp; }
    }

}
