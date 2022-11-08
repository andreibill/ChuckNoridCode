import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void decode(){
        System.out.println("Input encoded string:");
        String message = scanner.nextLine();
        if(!checkEncoded(message)){
            System.out.println("Encoded string is not valid.");
            return;
        }else{
            String binaryCode = chuckNorisToBinary(message);
            String finalMessage = "";
            for(int i = 0; i < binaryCode.length(); i=i+7){
                finalMessage += binaryToChar(binaryCode.substring(i,i+7));
            }
            System.out.println("Decoded string:");
            System.out.println(finalMessage);
        }
    }
    static boolean checkEncoded(String message){
        int counter = 0;
        String[] messageSplit = message.split(" ");
        if(messageSplit.length % 2 == 1)
            return false;
        for(int i = 0; i < messageSplit.length; i++){
            if(i % 2 == 0)
                if(messageSplit[i].length() < 1 || messageSplit[i].length() > 2)
                    return false;
            for(int j = 0; j < messageSplit[i].length(); j++) {
                if (messageSplit[i].charAt(j) != '0')
                    return false;
                if(i % 2 == 1)
                    counter++;
            }
        }
        if(counter % 7 != 0)
            return false;
        return true;
    }

    static String chuckNorisToBinary(String message){
        String[] messageSplit = message.split(" ");
        String binary="";
        for(int i = 0; i < messageSplit.length; i = i + 2) {
            if (messageSplit[i].equals("0")) {
                for (int j = 0; j < messageSplit[i + 1].length(); j++)
                    binary += '1';
            } else {
                for (int j = 0; j < messageSplit[i + 1].length(); j++)
                    binary += '0';
            }
        }
        return binary;
    }

    static Character binaryToChar(String code){
        double power = 0;
        int decimal = 0;
        for(int i = code.length()-1; i >= 0; i--){
            decimal += ((int)code.charAt(i)-48) * Math.pow(2, power);
            power++;
        }
        char character = (char) decimal;
        return character;
    }

    public static void encode(){
        System.out.println("Input string:");
        String message = scanner.nextLine();
        StringBuilder codeBinary = new StringBuilder();
        for(int i = 0; i < message.length(); i++){
            codeBinary.append(charToBinary(message.charAt(i)));
        }
        binaryToChuckNoris(codeBinary.toString());
    }

    public static String charToBinary(char e){
        int ascii = (int) e;
        String binary = "";
        for(int i = 0; i < 7; i++){
            binary = ascii % 2 + binary;
            ascii /=2;
        }
        return binary;
    }

    static void binaryToChuckNoris(String code){
        char curentValue = code.charAt(0);
        int valueCounter = 1;
        System.out.println("Encoded string:");
        for(int i = 1; i < code.length(); i++){
            if(code.charAt(i) == curentValue)
                valueCounter++;
            else{
                if(curentValue == '1')
                    System.out.print("0 ");
                else
                    System.out.print("00 ");
                for(int j = 1; j <= valueCounter; j++)
                    System.out.print("0");
                System.out.print(" ");
                valueCounter = 1;
                curentValue = code.charAt(i);
            }
        }
        if(curentValue == '1')
            System.out.print("0 ");
        else
            System.out.print("00 ");
        for(int j = 1; j <= valueCounter; j++)
            System.out.print("0");
        System.out.println();
    }

    public static void start(){
        System.out.println("Please input operation (encode/decode/exit):");
        String selection = scanner.nextLine();
        while(!selection.equals("exit")){
            switch (selection){
                case "encode":
                    encode();
                    break;
                case "decode":
                    decode();
                    break;
                default:
                    System.out.println("There is no '" + selection +"' operation");
            }
            System.out.println("Please input operation (encode/decode/exit):");
            selection = scanner.nextLine();
        }
        System.out.println("Bye!");
    }

    public static void main(String[] args) {
        start();
    }
}