import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static int[] registers = new int[8];
    static int pc = 0;
    static boolean Z = false;
    static boolean N = false;
    static int[] memory = new int[256];
    private static ArrayList<ArrayList<String>> instructions = new ArrayList<>();
    static HashMap<String, Integer> labels = new HashMap<>();

    public static void readTheProgram() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("program.txt"))) {
            String line;
            int counter = 0;

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line.length() == 0) continue;
                String word = "";
                ArrayList<String> instruction = new ArrayList<>();

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ';') {
                        if (!word.equals("")) instruction.add(word.toUpperCase());
                        break;
                    }
                    if (line.charAt(i) == ':')
                        if (word.equals(""))
                            throw new AssemblySyntaxError("Invalid label name at instruction number " + counter);
                        else {
                            labels.put(word.toUpperCase(), counter);
                            word = "";
                            //line = line.substring(i);
                        }

                    else if (line.charAt(i) == ' ')
                        if (instruction.isEmpty() && !word.equals("")) {
                            instruction.add(word.toUpperCase());
                            word = "";
                        } else continue;
                    else if (line.charAt(i) == ',') {
                        if (!instruction.isEmpty() && !word.equals("")) {
                            instruction.add(word.toUpperCase());
                            word = "";
                        } else
                            throw new AssemblySyntaxError("Comma misplacement in instruction number " + counter);
                    } else {
                        word += line.charAt(i);
                        if (i == line.length() - 1 && !word.equals("")) instruction.add(word);
                    }


                }
                if (!instruction.isEmpty()) {
                    instructions.add(instruction);
                    counter++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: program.txt");
        }
    }

    public static void excuteProgram() throws IOException {
        readTheProgram();
        try {
            while(pc < instructions.size()) {
                excuteInstruction(instructions.get(pc), ++pc);
            }
            throw new AssemblySyntaxError("Missing Halt instruction");
        } catch (HaltException e) {
            // normal termination via HALT instruction
        }
    }

    public static void main(String[] args) {
        try {
            excuteProgram();
        } catch (AssemblySyntaxError e) {
            System.err.println("Assembly Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        }
    }


    public static void excuteInstruction(ArrayList<String> instruction, int counter) {
        switch (instruction.get(0)) {
            case "MOV":
                Instructions.mov(instruction, counter);
                break;
            case "ADD":
                Instructions.add(instruction, counter);
                break;
            case "SUB":
                Instructions.sub(instruction, counter);
                break;
            case "MUL":
                Instructions.mul(instruction, counter);
                break;
            case "DIV":
                Instructions.div(instruction, counter);
                break;
            case "AND":
                Instructions.and(instruction, counter);
                break;
            case "OR":
                Instructions.or(instruction, counter);
                break;
            case "XOR":
                Instructions.xor(instruction, counter);
                break;
            case "NOT":
                Instructions.not(instruction, counter);
                break;
            case "SHL":
                Instructions.shl(instruction, counter);
                break;
            case "SHR":
                Instructions.shr(instruction, counter);
                break;
            case "LOAD":
                Instructions.load(instruction, counter);
                break;
            case "STORE":
                Instructions.store(instruction, counter);
                break;
            case "CMP":
                Instructions.cmp(instruction, counter);
                break;
            case "JMP":
                Instructions.jmp(instruction, counter);
                break;
            case "JZ":
                Instructions.jz(instruction, counter);
                break;
            case "JN":
                Instructions.jn(instruction, counter);
                break;
            case "JNZ":
                Instructions.jnz(instruction, counter);
                break;
            case "PRINT":
                Instructions.print(instruction, counter);
                break;
            case "HALT":
                Instructions.halt(instruction, counter);
                break;
            default:
                throw new AssemblySyntaxError("Unknown op code exception in instuction number " + counter);
        }
    }


}





















