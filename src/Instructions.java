import java.util.ArrayList;

public class Instructions {
    private static int getRegisterIndex(String r , int counter){
        switch(r){
            case "R0" : return 0;
            case "R1" : return 1;
            case "R2" : return 2;
            case "R3" : return 3;
            case "R4" : return 4;
            case "R5" : return 5;
            case "R6" : return 6;
            case "R7" : return 7;
            default: throw new AssemblySyntaxError("Invalid Register Name in instruction "+counter);
        }
    }

    private static int getImmediateOrRegister(String operand, int counter) {
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e) {
            int index = getRegisterIndex(operand, counter);
            return Main.registers[index];
        }
    }

    public static void mov(ArrayList<String> instruction, int counter){
        if (instruction.size() != 3) {
            throw new AssemblySyntaxError("MOV requires 2 operands at instruction " + counter);
        }

        int rx = getRegisterIndex(instruction.get(1), counter);
        int value = getImmediateOrRegister(instruction.get(2), counter);

        Main.registers[rx] = value;

        System.out.println("[Line " + (counter + 1) + "] MOV "
                + instruction.get(1) + ", " + instruction.get(2)
                + " | " + instruction.get(1) + " = " + Main.registers[rx]);
    }

    public static void add(ArrayList<String> instruction, int counter){
        if (instruction.size() != 4) {
            throw new AssemblySyntaxError("ADD requires 3 operands at instruction " + counter);
        }

        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        int rz = getImmediateOrRegister(instruction.get(3), counter);

        int result = ry+rz;

        Main.registers[rx] = result;

        Main.Z = false;
        Main.N = false;

        if (result == 0) {
            Main.Z = true;
        } else if (result < 0) {
            Main.N = true;
        }

        System.out.println("[Line " + (counter + 1) + "] ADD "
                + instruction.get(1) + ", " + instruction.get(2) + ", " + instruction.get(3)
                + " | " + instruction.get(1) + " = " + result
                + " (Z=" + (Main.Z ? 1 : 0) + ", N=" + (Main.N ? 1 : 0) + ")");
    }

    public static void sub(ArrayList<String> instruction, int counter){if (instruction.size() != 4) {
        throw new AssemblySyntaxError("SUB requires 3 operands at instruction " + counter);
    }

        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        int rz = getImmediateOrRegister(instruction.get(3), counter);

        int result = ry-rz;

        Main.registers[rx] = result;

        Main.Z = false;
        Main.N = false;

        if (result == 0) {
            Main.Z = true;
        } else if (result < 0) {
            Main.N = true;
        }

        System.out.println("[Line " + (counter + 1) + "] SUB "
                + instruction.get(1) + ", " + instruction.get(2) + ", " + instruction.get(3)
                + " | " + instruction.get(1) + " = " + result
                + " (Z=" + (Main.Z ? 1 : 0) + ", N=" + (Main.N ? 1 : 0) + ")");
    }

    public static void mul(ArrayList<String> instruction, int counter){
        if (instruction.size() != 4) {
            throw new AssemblySyntaxError("MUL requires 3 operands at instruction " + counter);
        }

        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        int rz = getImmediateOrRegister(instruction.get(3), counter);

        int result = ry*rz;

        Main.registers[rx] = result;

        Main.Z = false;
        Main.N = false;

        if (result == 0) {
            Main.Z = true;
        } else if (result < 0) {
            Main.N = true;
        }

        System.out.println("[Line " + (counter + 1) + "] MUL "
                + instruction.get(1) + ", " + instruction.get(2) + ", " + instruction.get(3)
                + " | " + instruction.get(1) + " = " + result
                + " (Z=" + (Main.Z ? 1 : 0) + ", N=" + (Main.N ? 1 : 0) + ")");
    }

    public static void div(ArrayList<String> instruction, int counter){
        if (instruction.size() != 4) {
            throw new AssemblySyntaxError("DIV requires 3 operands at instruction " + counter);
        }

        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        int rz = getImmediateOrRegister(instruction.get(3), counter);

        if (rz==0) {
            throw new AssemblySyntaxError("Division by zero at instruction " + counter);
        }

        int result = ry/rz;

        Main.registers[rx] = result;

        Main.Z = false;
        Main.N = false;

        if (result == 0) {
            Main.Z = true;
        } else if (result < 0) {
            Main.N = true;
        }

        System.out.println("[Line " + (counter + 1) + "] DIV "
                + instruction.get(1) + ", " + instruction.get(2) + ", " + instruction.get(3)
                + " | " + instruction.get(1) + " = " + result
                + " (Z=" + (Main.Z ? 1 : 0) + ", N=" + (Main.N ? 1 : 0) + ")");
    }

    public static void and(ArrayList<String> instruction, int counter){
        if (instruction.size() != 4) {
            throw new AssemblySyntaxError("AND requires 3 operands at instruction " + counter);
        }

        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        int rz = getImmediateOrRegister(instruction.get(3), counter);

        int result = ry&rz;

        Main.registers[rx] = result;

        System.out.println("[Line " + (counter + 1) + "] AND "
                + instruction.get(1) + ", " + instruction.get(2) + ", " + instruction.get(3)
                + " | " + instruction.get(1) + " = " + result);
    }

    public static void or(ArrayList<String> instruction, int counter){
        if (instruction.size() != 4) {
            throw new AssemblySyntaxError("OR requires 3 operands at instruction " + counter);
        }

        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        int rz = getImmediateOrRegister(instruction.get(3), counter);

        int result = ry|rz;

        Main.registers[rx] = result;

        System.out.println("[Line " + (counter + 1) + "] OR "
                + instruction.get(1) + ", " + instruction.get(2) + ", " + instruction.get(3)
                + " | " + instruction.get(1) + " = " + result);
    }

    public static void xor(ArrayList<String> instruction, int counter){
        if (instruction.size() != 4) {
            throw new AssemblySyntaxError("XOR requires 3 operands at instruction " + counter);
        }
        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        int rz = getImmediateOrRegister(instruction.get(3), counter);

        int result = ry^rz;
        Main.registers[rx] = result;

        System.out.println("[Line " + (counter + 1) + "] XOR " + instruction.get(1) + ", " + instruction.get(2) + ", " + instruction.get(3) + " | " + instruction.get(1) + " = " + result);
    }

    public static void not(ArrayList<String> instruction, int counter){
        if (instruction.size() != 3) {
            throw new AssemblySyntaxError("NOT requires 2 operands at instruction " + counter);
        }
        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);

        int result = ~ry;
        Main.registers[rx] = result;

        System.out.println("[Line " + (counter + 1) + "] NOT " + instruction.get(1) + ", " + instruction.get(2) + " | " + instruction.get(1) + " = " + result);
    }

    public static void shl(ArrayList<String> instruction, int counter){
        if (instruction.size() != 4) {
            throw new AssemblySyntaxError("SHL requires 3 operands at instruction " + counter);
        }
        int rx = getRegisterIndex(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        int n = Integer.parseInt(instruction.get(3));

        int result = ry << n;
        Main.registers[rx] = result;

        System.out.println("[Line " + (counter + 1) + "] SHL " + instruction.get(1) + ", " + instruction.get(2) + ", " + n + " | " + instruction.get(1) + " = " + result);
    }

    public static void shr(ArrayList<String> instruction, int counter){
        if (instruction.size() != 4) {
            throw new AssemblySyntaxError("SHR requires 3 operands at instruction " + counter);
        }
        int rx = getRegisterIndex(instruction.get(1).toUpperCase(), counter);
        int ry = getImmediateOrRegister(instruction.get(2).toUpperCase(), counter);
        int n = Integer.parseInt(instruction.get(3));
        Main.registers[rx] = ry >> n;
        System.out.println("[Line " + (counter + 1) + "] SHR " + instruction.get(1) + ", " + instruction.get(2) + " | " + instruction.get(1) + " = " + Main.registers[rx]);
    }

    public static void load(ArrayList<String> instruction, int counter){
        if (instruction.size() < 3)
            throw new AssemblySyntaxError("LOAD requires 2 operands at instruction " + counter);
        int rx = getRegisterIndex(instruction.get(1), counter);
        int address;
        try {
            address = Integer.parseInt(instruction.get(2));
        } catch (NumberFormatException e) {
            throw new AssemblySyntaxError("Invalid memory address '" + instruction.get(2) + "' at instruction " + counter);
        }
        if (address < 0 || address > 255)
            throw new AssemblySyntaxError("Invalid memory address " + address + " at instruction " + counter);
        Main.registers[rx] = Main.memory[address];
        System.out.println("[Line " + (counter + 1) + "] LOAD " + instruction.get(1) + ", " + address + " | " + instruction.get(1) + " = " + Main.registers[rx]);
    }

    public static void store(ArrayList<String> instruction, int counter){
        if (instruction.size() < 3)
            throw new AssemblySyntaxError("STORE requires 2 operands at instruction " + counter);
        int rx = getRegisterIndex(instruction.get(1), counter);
        int address;
        try {
            address = Integer.parseInt(instruction.get(2));
        } catch (NumberFormatException e) {
            throw new AssemblySyntaxError("Invalid memory address '" + instruction.get(2) + "' at instruction " + counter);
        }
        if (address < 0 || address > 255)
            throw new AssemblySyntaxError("Invalid memory address " + address + " at instruction " + counter);
        Main.memory[address] = Main.registers[rx];
        System.out.println("[Line " + (counter + 1) + "] STORE " + instruction.get(1) + ", " + address + " | Memory[" + address + "] = " + Main.memory[address]);
    }

    public static void cmp(ArrayList<String> instruction, int counter){
        if (instruction.size() < 3)
            throw new AssemblySyntaxError("CMP requires 2 operands at instruction " + counter);
        int rx = getImmediateOrRegister(instruction.get(1), counter);
        int ry = getImmediateOrRegister(instruction.get(2), counter);
        Main.Z = false;
        Main.N = false;
        if (rx==ry)
            Main.Z = true;
        else if (rx<ry)
            Main.N = true;
        System.out.println("[Line " + (counter + 1) + "] CMP " + instruction.get(1) + ", " + instruction.get(2) + " | Z=" + (Main.Z ? 1 : 0) + ", N=" + (Main.N ? 1 : 0));
    }

    public static void jmp(ArrayList<String> instruction, int counter){
        if (instruction.size() < 2)
            throw new AssemblySyntaxError("JMP requires a label operand at instruction " + counter);
        String labelName = instruction.get(1);
        if (!Main.labels.containsKey(labelName))
            throw new AssemblySyntaxError("Undefined label: " + labelName + " at instruction " + counter);
        Main.pc = Main.labels.get(labelName);
        System.out.println("[Line " + (counter + 1) + "] JMP " + labelName + " | Jump taken -> Line " + (Main.pc + 1));
    }

    public static void jz(ArrayList<String> instruction, int counter){
        if (instruction.size() < 2)
            throw new AssemblySyntaxError("JZ requires a label operand at instruction " + counter);
        String labelName = instruction.get(1);
        if (!Main.labels.containsKey(labelName))
            throw new AssemblySyntaxError("Undefined label: " + labelName + " at instruction " + counter);
        if (Main.Z) {
            Main.pc = Main.labels.get(labelName);
            System.out.println("[Line " + (counter + 1) + "] JZ " + labelName + " | Jump taken -> Line " + (Main.pc + 1));
        } else {
            System.out.println("[Line " + (counter + 1) + "] JZ " + labelName + " | Jump not taken");
        }
    }

    public static void jn(ArrayList<String> instruction, int counter){
        if (instruction.size() < 2)
            throw new AssemblySyntaxError("JN requires a label operand at instruction " + counter);
        String labelName = instruction.get(1);
        if (!Main.labels.containsKey(labelName))
            throw new AssemblySyntaxError("Undefined label: " + labelName + " at instruction " + counter);
        if (Main.N) {
            Main.pc = Main.labels.get(labelName);
            System.out.println("[Line " + (counter + 1) + "] JN " + labelName + " | Jump taken -> Line " + (Main.pc + 1));
        } else {
            System.out.println("[Line " + (counter + 1) + "] JN " + labelName + " | Jump not taken");
        }
    }

    public static void jnz(ArrayList<String> instruction, int counter){
        if (instruction.size() < 2)
            throw new AssemblySyntaxError("JNZ requires a label operand at instruction " + counter);
        String labelName = instruction.get(1);
        if (!Main.labels.containsKey(labelName))
            throw new AssemblySyntaxError("Undefined label: " + labelName + " at instruction " + counter);
        if (!Main.Z) {
            Main.pc = Main.labels.get(labelName);
            System.out.println("[Line " + (counter + 1) + "] JNZ " + labelName + " | Jump taken -> Line " + (Main.pc + 1));
        } else {
            System.out.println("[Line " + (counter + 1) + "] JNZ " + labelName + " | Jump not taken");
        }
    }

    public static void print(ArrayList<String> instruction, int counter){
        if (instruction.size() < 2)
            throw new AssemblySyntaxError("PRINT requires a register operand at instruction " + counter);
        int rx = getRegisterIndex(instruction.get(1), counter);
        System.out.println("[Line " + (counter + 1) + "] PRINT " + instruction.get(1) + " | Output: " + Main.registers[rx]);}

    public static void halt(ArrayList<String> instruction, int counter){
        System.out.println("[Line " + (counter + 1) + "] HALT");
        throw new HaltException();
    }


}
