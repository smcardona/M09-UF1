package iticbcn.xifratge.algorisme;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

  private static final char[] ALPHABET = new char[] {
    'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
    'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M',
    'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
    'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
  };
  private final char[] MONO_ALPH;

  public Monoalfabetic() {
    this.MONO_ALPH = permutaAlfabet(ALPHABET);
  }

  public static char[] permutaAlfabet (char[] reference) {
    Character[] characters = UtilChar.charsToCharacters(reference);
    List<Character> copy = Arrays.asList(characters);
    Collections.shuffle(copy);

    return UtilChar.listToChars(copy);
  }

  public String xifraMonoAlfa (String input) {
    return translateWith(input, ALPHABET, MONO_ALPH);
  }

  public String desxifraMonoAlfa (String input) {
    return translateWith(input, MONO_ALPH, ALPHABET);
  }


  public static void main (String[] args) {
    
    String[] tests = {
      "Hola Mundo",
      "Esto es una prueba",
      "Cifrado Monoalfabetico",
      "Java Programming",
      "12345!@#$%"
    };

    Monoalfabetic xifrador = new Monoalfabetic();

    for (String test : tests) {
      System.out.println("INICIALIZANDO ALFABETOS");
      System.out.println("REFE: "+ new String(ALPHABET));
      System.out.println("MONO: "+ new String(xifrador.MONO_ALPH));
      System.out.println();

      System.out.printf("%-15s: %s%n", "Input", test);

      String cifrado = xifrador.xifraMonoAlfa(test);
      System.out.printf("%-15s: %s%n", "Cifrado", cifrado);
      
      String descifrado = xifrador.desxifraMonoAlfa(cifrado);
      System.out.printf("%-15s: %s%n%n", "Descifrado", descifrado);

      System.out.printf(
        "El descifrado ha sido %s%n", (descifrado.equals(test) ? "correcto" : "incorrecto")
      );
    }
    
  }

  public static String translateWith (String input, char[] base, char[] translation) {
    if (input == null || input.isBlank()) return "";
    StringBuilder result = new StringBuilder();
  
    for (char c: input.toCharArray()) {
      if (UtilChar.indexOfCharFrom(Character.toUpperCase(c), base) == -1) {
        result.append(c);
        continue; // ignora si no está en el abecedario
      }
      int index = UtilChar.indexOfCharFrom(Character.toUpperCase(c), base);
      if (Character.isUpperCase(c)) {
        result.append(Character.toUpperCase(translation[index])); // Optiene la equivalencia de la traduccion
      }
      else {
        result.append(Character.toLowerCase(translation[index]));
      }
    }
  
    return result.toString();
  }

}


