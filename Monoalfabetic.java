import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
 
  private static String ALPHABET = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ";
  private static String MONO_ALPH = new String(permutaAlfabet(ALPHABET.toCharArray()));

  public static char[] permutaAlfabet (char[] reference) {

    Character[] asChars = new Character[reference.length];
    // transforma a Character
    for (int i = 0; i < reference.length; i++) {
      asChars[i] = reference[i];
    }

    List<Character> asCollection = Arrays.asList(asChars);
    Collections.shuffle(asCollection);

    // transforma a char
    char[] shuffledArray = new char[asCollection.size()];
    for (int i = 0; i < asCollection.size(); i++) {
      shuffledArray[i] = asCollection.get(i);
    }
    return shuffledArray;

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


    System.out.println("INICIALIZANDO ALPHABETOS");
    System.out.println("REFE: "+ALPHABET);
    System.out.println("MONO: "+MONO_ALPH);
    System.out.println();

    String input = args[0];
    System.out.println("Procesando input: "+ input);

    String cifrado = xifraMonoAlfa(input);
    System.out.println("Cifrado: "+ cifrado);
    
    String descifrado = desxifraMonoAlfa(cifrado);
    System.out.println("Descifrado: "+ descifrado);

  }

}
