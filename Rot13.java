
public class Rot13 {
    
    public static final String alphabet = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ";
    private static final char[] alphabetMayus = alphabet.toCharArray();
    private static final char[] alphabetMinus = alphabet.toLowerCase().toCharArray();
    
    public static String xifraRot13 (String input) {
        if (input == null || input.isBlank()) return "";
        String result = "";

        for (char charact: input.toCharArray()) {
            if (! alphabet.contains(""+Character.toUpperCase(charact))) {
                result += charact;
                continue; // ignora si no está en el abecedario
            }

            int moved;
            if (Character.isUpperCase(charact)) {
                moved = alphabet.indexOf(charact) + 13 % alphabet.length();
                result += alphabetMayus[moved];
            }
            else {
                moved = alphabet.toLowerCase().indexOf(charact) + 13 % alphabet.length();
                result += alphabetMinus[moved];
            }

        }


        return result;
    }

    public static String desxifraRot13(String input) {
        if (input == null || input.isBlank()) return "";
        String result = "";

        for (char charact: input.toCharArray()) {
            if (! alphabet.contains(""+Character.toUpperCase(charact))) {
                result += charact;
                continue; // ignora si no está en el abecedario
            }

            int moved;
            if (Character.isUpperCase(charact)) {
                moved = Math.abs(alphabet.indexOf(charact) - 13) % alphabet.length();
                result += alphabetMayus[moved];
            }
            else {
                moved = Math.abs(alphabet.toLowerCase().indexOf(charact) - 13) % alphabet.length();
                result += alphabetMinus[moved];
            }

        }


        return result;
    }

    public static void main (String[] args) {
        if(args.length < 1) {
            System.out.println("No has introducido un texto para procesar por agumentos: java Rot13 <texto>");
            return;
        }

        String input = args[0];
        System.out.println("Procesando input: "+ input);
        
        String cifrado = xifraRot13(input);
        System.out.println("Cifrado: "+ cifrado);

        String descifrado = desxifraRot13(cifrado);
        System.out.println("Descifrado: "+ descifrado);

    }
}