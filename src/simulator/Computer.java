package simulator;
/**
 * Computer class comprises of memory, registers, cc and
 * can execute the instructions based on PC and IR 
 * @author mmuppa
 *
 */
public class Computer {

	private final static int MAX_MEMORY = 50;
	private final static int MAX_REGISTERS = 8;
	private static int lastREG;
	private static BitString[] mRegisters;
	private static BitString[] mMemory;
	private static BitString mPC;
	private BitString mIR;
	private static BitString mCC;

	/**
	 * Initializes all the memory to 0, registers to 0 to 7
	 * PC, IR to 16 bit 0s and CC to 000 
	 * Represents the initial state 
	 */
	public Computer() {
		mPC = new BitString();
		mPC.setValue(0);
		mIR = new BitString();
		mIR.setValue(0);
		mCC = new BitString();
		mCC.setBits(new char[] { '0', '0', '0' });
		mRegisters = new BitString[MAX_REGISTERS];
		for (int i = 0; i < MAX_REGISTERS; i++) {
			mRegisters[i] = new BitString();
			mRegisters[i].setValue(i);
		}

		mMemory = new BitString[MAX_MEMORY];
		for (int i = 0; i < MAX_MEMORY; i++) {
			mMemory[i] = new BitString();
			mMemory[i].setValue(0);
		}
	}

	public static int getPC() {
		return mPC.getValue();
	}

	public static void setPC(int n){
		mPC.setValue(n);
	}

	public static BitString getCC(){
		return mCC;
	}

	public static int getLastREG() {
		return lastREG;
	}

	public static void setLastREG(int lastREG) {
		Computer.lastREG = lastREG;
	}

	public static BitString[] getMEM() {
		return mMemory;
	}

	public static BitString[] getREG() {
		return mRegisters;
	}

	/**
	 * Loads a 16 bit word into memory at the given address. 
	 * @param address memory address
	 * @param word data or instruction or address to be loaded into memory
	 */
	public void loadWord(int address, BitString word) {
		if (address < 0 || address >= MAX_MEMORY) {
			throw new IllegalArgumentException("Invalid address");
		}
		mMemory[address] = word;
	}

	//only public static for debugging via JUnit, otherwise this should be private
	public static void setCC(){
		Computer.getCC().setValue(0); //Clear previous CC
		int n = Computer.getREG()[getLastREG()].getValue2sComp();
		if(n < 0){ Computer.getCC().setValue(Computer.getCC().getValue() + 4);} //Set the CC according to NZP
		if(n == 0){ Computer.getCC().setValue(Computer.getCC().getValue() + 2);}
		if(n > 0){ Computer.getCC().setValue(Computer.getCC().getValue() + 1);}
	} //All changes regarding the CC are handled via this method

	/**
	 * This method will execute all the instructions starting at address 0 
	 * till HALT instruction is encountered. 
	 */
	public void execute() {
		BitString opCodeStr;
		int opCode;

		while (mPC.getValue() < MAX_MEMORY) {
			// Fetch the instruction
			mIR = mMemory[mPC.getValue()];
			//System.out.println(mIR.toString());
			mPC.addOne();
			// Decode the instruction's first 4 bits 
			// to figure out the opcode
			opCodeStr = mIR.substring(0, 4);
			opCode = opCodeStr.getValue();
			switch(opCode){
				case 0: //BR COMMAND
					BRCommand br = new BRCommand(mIR.toString());
					br.commandMethod();
					break;
				case 1: //ADD COMMAND
					ADDCommand add = new ADDCommand(mIR.toString());
					add.commandMethod();
					break;
				case 2: //LD COMMAND
					LDCommand ld = new LDCommand(mIR.toString());
					ld.commandMethod();
					break;
				case 3: //AND COMMAND
					ANDCommand and = new ANDCommand(mIR.toString());
					and.commandMethod();
					break;
				case 6: //LDR COMMAND
					LDRCommand ldr = new LDRCommand(mIR.toString());
					ldr.commandMethod();
					break;
				case 7: //STR COMMAND
					STRCommand str = new STRCommand(mIR.toString());
					str.commandMethod();
					break;
				case 9: //NOT COMMAND
					NOTCommand not = new NOTCommand(mIR.toString());
					not.commandMethod();
					break;
				case 10: //LDI COMMAND
					LDICommand ldi = new LDICommand(mIR.toString());
					ldi.commandMethod();
					break;
				case 11: //STI COMMAND
					STICommand sti = new STICommand(mIR.toString());
					sti.commandMethod();
					break;
				case 14: //LEA COMMAND
					LEACommand lea = new LEACommand(mIR.toString());
					lea.commandMethod();
					break;
				case 15: //OUT + HALT COMMAND
					if(mIR.toString().equals("1111000000100001")){ //OUT COMMAND
						OUTCommand out = new OUTCommand();
						out.commandMethod();
					}else if(mIR.toString().equals("1111000000100101")){ //HALT COMMAND
						HALTCommand halt = new HALTCommand();
						halt.commandMethod();
					}
					break;
			}
			setCC();
		}
	}

	/**
	 * Displays the computer's state
	 */
	public void display() {
		System.out.print("\nPC ");
		mPC.display(true);
		System.out.print("   ");

		System.out.print("IR ");
		mPC.display(true);
		System.out.print("   ");

		System.out.print("CC ");
		mCC.display(true);
		System.out.println("   ");

		for (int i = 0; i < MAX_REGISTERS; i++) {
			System.out.printf("R%d ", i);
			mRegisters[i].display(true);
			if (i % 3 == 2) {
				System.out.println();
			} else {
				System.out.print("   ");
			}
		}
		System.out.println();

		for (int i = 0; i < MAX_MEMORY; i++) {
			System.out.printf("%3d ", i);
			mMemory[i].display(true);
			if (i % 3 == 2) {
				System.out.println();
			} else {
				System.out.print("   ");
			}
		}
		System.out.println();

	}
}
