import java.util.ArrayList;

public class Instructions {
    private static int gitRegisterIndex(String r , int countr){
        switch(r){
            case "R0" : return 0;
            case "R1" : return 1;
            case "R2" : return 2;
            case "R3" : return 3;
            case "R4" : return 4;
            case "R5" : return 5;
            case "R6" : return 6;
            case "R7" : return 7;
            default: throw new AssemblySyntaxError("Invalid Register Name in instruction "+countr);
        }
    }

    public static void mov(ArrayList<String> instruction, int counter){}

    public static void add(ArrayList<String> instruction, int counter){}

    public static void sub(ArrayList<String> instruction, int counter){}

    public static void mul(ArrayList<String> instruction, int counter){}

    public static void div(ArrayList<String> instruction, int counter){}

    public static void and(ArrayList<String> instruction, int counter){}

    public static void or(ArrayList<String> instruction, int counter){}

    public static void xor(ArrayList<String> instruction, int counter){}

    public static void not(ArrayList<String> instruction, int counter){}

    public static void shl(ArrayList<String> instruction, int counter){}

    public static void shr(ArrayList<String> instruction, int counter){}

    public static void load(ArrayList<String> instruction, int counter){}

    public static void store(ArrayList<String> instruction, int counter){}

    public static void cmp(ArrayList<String> instruction, int counter){}

    public static void jmp(ArrayList<String> instruction, int counter){}

    public static void jz(ArrayList<String> instruction, int counter){}

    public static void jn(ArrayList<String> instruction, int counter){}

    public static void jnz(ArrayList<String> instruction, int counter){}

    public static void print(ArrayList<String> instruction, int counter){}

    public static void halt(ArrayList<String> instruction, int counter){
        System.exit(0);
    }


}
