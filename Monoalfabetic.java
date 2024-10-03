import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
 
  private static String ALPHABET = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ";
  private static String MONO_ALPH = new String(permutaAlfabet(ALPHABET));

  public static String permutaAlfabet (String reference) {

    String[] divided = reference.split("");

    List<String> asCollection = Arrays.asList(divided);
    Collections.shuffle(asCollection);

    return String.join("", asCollection);
  }

  public static String xifraMonoAlfa (String input) {
    if (input == null || input.isBlank()) return "";
    StringBuilder result = new StringBuilder();

    for (char c: input.toCharArray()) {
      if (ALPHABET.indexOf(Character.toUpperCase(c)) == -1) {
        result.append(c);
        continue; // ignora si no está en el abecedario
      }

      char mono; 
      if (Character.isUpperCase(c)) {
        int index = ALPHABET.indexOf(c);
        mono = MONO_ALPH.charAt(index);
      }
      else {
        int index = ALPHABET.toLowerCase().indexOf(c);
        mono = Character.toLowerCase(MONO_ALPH.charAt(index));
      }

      result.append(mono);
    }

    return result.toString();
  }

  public static String desxifraMonoAlfa (String input) {
    if (input == null || input.isBlank()) return "";
    StringBuilder result = new StringBuilder();

    for (char c: input.toCharArray()) {
      if (ALPHABET.indexOf(Character.toUpperCase(c)) == -1) {
        result.append(c);
        continue; // ignora si no está en el abecedario
      }

      char mono; 
      if (Character.isUpperCase(c)) {
        int index = MONO_ALPH.indexOf(c);
        mono = ALPHABET.charAt(index);
      }
      else {
        int index = MONO_ALPH.toLowerCase().indexOf(c);
        mono = Character.toLowerCase(ALPHABET.charAt(index));
      }

      result.append(mono);
    }

    return result.toString();

  }

  public static void main (String[] args) {
    if(args.length < 1) {
        System.out.println("No has introducido un texto para procesar por agumentos: java Rot13 <texto>");
        return;
    }


    System.out.println("INICIALIZANDO ALFABETOS");
    System.out.println("REFE: "+ALPHABET);
    System.out.println("MONO: "+MONO_ALPH);
    System.out.println();

    String input = args[0];
    System.out.printf("%-15s: %s%n", "Input", input);

    String cifrado = xifraMonoAlfa(input);
    System.out.printf("%-15s: %s%n", "Cifrado", cifrado);
    
    String descifrado = desxifraMonoAlfa(cifrado);
    System.out.printf("%-15s: %s%n%n", "Descifrado", descifrado);

    System.out.printf(
      "El descifrado ha sido %s%n", (descifrado.equals(input) ? "correcto" : "incorrecto")
    );

  }

}
