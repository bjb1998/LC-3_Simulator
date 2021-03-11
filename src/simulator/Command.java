package simulator;
public abstract class Command {
    String commandBin;
    abstract void commandMethod();
    int compToDecimal(String bin){
        if(bin.charAt(0) == '0'){
            return Integer.parseInt(bin, 2);
        }else{
            bin = bin.replaceAll("1", "x"); //swap the bits of zero and one
            bin = bin.replaceAll("0", "1");
            bin = bin.replaceAll("x", "0");
            int a = ~Integer.parseInt(bin, 2);
            return a; //Return NOT(A) plus one
        }
    }
}

class ADDCommand extends Command{
    int reg1, reg2, reg3, offset;
    boolean SR2 = true;
    public ADDCommand(String commandBin){
        reg1 = Integer.parseInt(commandBin.substring(4,7),2);
        reg2 = Integer.parseInt(commandBin.substring(8,10),2);
        if(commandBin.charAt(10) == '1') {
            SR2 = false;
            offset = compToDecimal(commandBin.substring(11,16));
        }else
            reg3 = Integer.parseInt(commandBin.substring(13,16), 2);
    }
    void commandMethod(){
        if(SR2){
            Computer.getREG()[reg1].setValue2sComp(compToDecimal(Computer.getREG()[reg2].toString())
                    + compToDecimal(Computer.getREG()[reg3].toString()));
        }else{
            Computer.getREG()[reg1].setValue2sComp(Computer.getREG()[reg2].getValue2sComp() + offset);
        }
        Computer.setLastREG(reg1);
    }
} //Done :)
class ANDCommand extends Command{
    int reg1, reg2, reg3, offset;
    boolean SR2 = true;
    public ANDCommand(String commandBin){
        reg1 = Integer.parseInt(commandBin.substring(4,7),2);
        reg2 = Integer.parseInt(commandBin.substring(7,10),2);
        if(commandBin.charAt(10) == '1') {
            SR2 = false;
            offset = compToDecimal(commandBin.substring(11,16));
        }else
            reg3 = Integer.parseInt(commandBin.substring(13,16), 2);
    }
    void commandMethod(){
        if(SR2){
            Computer.getREG()[reg1]
                    .setValue2sComp(Computer.getREG()[reg2].getValue() & Computer.getREG()[reg3].getValue2sComp());
        }else{
            Computer.getREG()[reg1].setValue2sComp(Computer.getREG()[reg2].getValue() & offset);
        }
        Computer.setLastREG(reg1);
    }
} //Done :)
class LDCommand extends Command{
    int reg, offset;
    public LDCommand(String commandBin){
         reg = Integer.parseInt(commandBin.substring(4,7),2);
         offset = compToDecimal(commandBin.substring(7,16));
    }
    void commandMethod(){
        Computer.getREG()[reg].setValue2sComp(Computer.getMEM()[Computer.getPC() + offset].getValue2sComp());
        Computer.setLastREG(reg);
    }
} //Done :)
class BRCommand extends Command{
    int offset;
    boolean n, z, p;
    int nVal, zVal, pVal;
    public BRCommand(String commandBin){
        this.offset = compToDecimal(commandBin.substring(7,16));
        Computer.getCC().setValue(0); //clear the previous CC
        nVal = Integer.parseInt(commandBin.charAt(4) + "");
        zVal = Integer.parseInt(commandBin.charAt(5) + "");
        pVal = Integer.parseInt(commandBin.charAt(6) + "");
        if(nVal == 1){n = true;} //Set the CC according to NZP
        if(zVal == 1){z = true;}
        if(pVal == 1){p = true;}
    }
    void commandMethod(){
        if((Computer.getREG()[Computer.getLastREG()].getValue() < 0 && n)
        || (Computer.getREG()[Computer.getLastREG()].getValue() == 0 && z)
        || (Computer.getREG()[Computer.getLastREG()].getValue() > 0 && p)){
            Computer.setPC(Computer.getPC() + offset);
        }
    }
} //Done :)
class OUTCommand extends Command{
    public char c; //For debugging purposes
    void commandMethod(){
        c = (char)(Computer.getREG()[0].getValue());
        System.out.print(c);
    }
} //Done :)
class HALTCommand extends Command{
    void commandMethod(){
        Computer.setPC(Computer.getMEM().length);
    }
} //Done :)
class NOTCommand extends Command{
    int reg1, reg2;
    public NOTCommand(String commandBin){
        reg1 = Integer.parseInt(commandBin.substring(4,7),2);
        reg2 = Integer.parseInt(commandBin.substring(7,10),2);
    }
    void commandMethod(){
        Computer.getREG()[reg1].setValue2sComp(~Computer.getREG()[reg2].getValue2sComp());
        Computer.setLastREG(reg1);
    }
} //Done :)
class LEACommand extends Command{
    int reg, offset;
    public LEACommand(String commandBin){
        reg = Integer.parseInt(commandBin.substring(4,7),2);
        offset = compToDecimal(commandBin.substring(7, 16));
    }
    void commandMethod(){
        Computer.getREG()[reg].setValue(Computer.getPC() + offset);
        Computer.setLastREG(reg);
    }
} //Done :)
class LDICommand extends Command{
    int reg, offset;
    public LDICommand(String commandBin){
        reg = Integer.parseInt(commandBin.substring(4,7),2);
        offset = compToDecimal(commandBin.substring(7, 16));
    }
    void commandMethod(){
        Computer.getREG()[reg].setValue(Computer.getMEM()[Computer.getPC() + offset].getValue());
        Computer.setLastREG(reg);
    }
} //Done :)
class STICommand extends Command{
    int reg, offset;
    public STICommand(String commandBin){
        reg = Integer.parseInt(commandBin.substring(4,7),2);
        offset = compToDecimal(commandBin.substring(7, 16));
    }
    void commandMethod(){
        Computer.getMEM()[Computer.getPC() + offset].setValue2sComp(Computer.getREG()[reg].getValue2sComp());
        Computer.setLastREG(reg);
    }
} //Done :)
class LDRCommand extends Command{
    int reg1, reg2, offset;
    public LDRCommand(String commandBin){
        reg1 = Integer.parseInt(commandBin.substring(4,7),2);
        reg2 = Integer.parseInt(commandBin.substring(8,10),2);
        offset = compToDecimal(commandBin.substring(10,16));
    }
    void commandMethod(){
        Computer.getREG()[reg1].setValue2sComp
                (Computer.getMEM()[Computer.getREG()[reg2].getValue() + offset].getValue2sComp());
        Computer.setLastREG(reg1);
    }
} //Done :)
class STRCommand extends Command{
    int reg1, reg2, offset;
    public STRCommand(String commandBin){
        reg1 = Integer.parseInt(commandBin.substring(4,7),2);
        reg2 = Integer.parseInt(commandBin.substring(8,10),2);
        offset = compToDecimal(commandBin.substring(10,16));
    }
    void commandMethod(){
        Computer.getMEM()[Computer.getREG()[reg2].getValue() + offset].setValue2sComp
                (Computer.getREG()[reg1].getValue2sComp());
        Computer.setLastREG(reg1);
    }
} //Done :)
