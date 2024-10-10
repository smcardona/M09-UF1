import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

  private static final long ULTRA_SECRET_PASSWORD = 25522552L;
  private static Random rnd = initRandom();
  private static final char[] ALPHABET = new char[] {
    'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
    'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M',
    'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
    'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
  };

  private static Random initRandom() {
    rnd = new Random(ULTRA_SECRET_PASSWORD);
    return rnd;
  }

  public static char[] permutaAlfabet (char[] reference) {
    Character[] characters = UtilChar.charsToCharacters(reference);
    List<Character> copy = Arrays.asList(characters);
    Collections.shuffle(copy, rnd);

    return UtilChar.listToChars(copy);
  }

  public static String xifraMonoAlfa (String input) {
    // Inicializar random
    initRandom();
    StringBuilder result = new StringBuilder();
    for (char letra: input.toCharArray()){
      char[] PERMUTED = permutaAlfabet(ALPHABET);
      char translated = translateWith(letra, ALPHABET, PERMUTED);
      result.append(translated);
    }
    
    return result.toString();
  }

  public static String desxifraMonoAlfa (String input) {
    initRandom();
    StringBuilder result = new StringBuilder();

    for (char letra: input.toCharArray()){
      char[] PERMUTED = permutaAlfabet(ALPHABET);
      char translated = translateWith(letra, PERMUTED, ALPHABET);
      result.append(translated);
    }
    
    return result.toString();
  }


  public static void main (String[] args) {
    if(args.length < 1) {
        System.out.println("No has introducido un texto para procesar por agumentos: java Polialfabetic <texto>");
        return;
    }
    String alphabet = new String(ALPHABET);

    System.out.println("INICIALIZANDO ALFABETOS");
    System.out.println("REFE: "+ alphabet);
    System.out.println();

    String input = args[0];
    System.out.printf("%-15s: %s%n", "Input", input);

    String cifrado = xifraMonoAlfa(input);
    System.out.printf("%-15s: %s%n", "Cifrado", cifrado);
    
    String descifrado = desxifraMonoAlfa(cifrado);
    System.out.printf("%-15s: %s%n%n", "Descifrado", descifrado);

    System.out.printf(
      "El descifrado ha sido %s%n%n", (descifrado.equals(input) ? "correcto" : "incorrecto")
    );

    System.out.println("PRUEBAS EXTRAS");
    System.out.println("Prueba de indice\n");
    input = alphabet;
    System.out.printf("%-15s: %s%n", "Input", input);

    cifrado = xifraMonoAlfa(input);
    System.out.printf("%-15s: %s%n", "Cifrado", cifrado);
    
    descifrado = desxifraMonoAlfa(cifrado);
    System.out.printf("%-15s: %s%n%n", "Descifrado", descifrado);

    System.out.printf(
      "El descifrado ha sido %s%n%n", (descifrado.equals(input) ? "correcto" : "incorrecto")
    );

    System.out.println("Prueba de polialfabetos\n");
    input = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
    System.out.printf("%-15s: %s%n", "Input", input);

    cifrado = xifraMonoAlfa(input);
    System.out.printf("%-15s: %s%n", "Cifrado", cifrado);
    
    descifrado = desxifraMonoAlfa(cifrado);
    System.out.printf("%-15s: %s%n%n", "Descifrado", descifrado);
    System.out.printf(
      "El descifrado ha sido %s%n%n", (descifrado.equals(input) ? "correcto" : "incorrecto")
    );

    boolean allTheSame = true;
    for (int i = 1; i <cifrado.length(); i++) {
      allTheSame = allTheSame && (cifrado.charAt(i) == cifrado.charAt(i-1));
      if (!allTheSame) break;
    }

    if (allTheSame)
      System.out.println("WARNING: Es muy imposible que todos en todos los alfabetos Z sea la misma");
    else 
      System.out.println("Sistema polialfabetico correcto :D");
  }

  public static char translateWith (char letter, char[] base, char[] translation) {
    
    int index = UtilChar.indexOfCharFrom(Character.toUpperCase(letter), base); // Base siempre es UpperCase en este código

    if (index == -1) {
      return letter;
    }

    if (Character.isUpperCase(letter)) 
      return Character.toUpperCase(translation[index]); // Optiene la equivalencia de la traduccion
    else
      return Character.toLowerCase(translation[index]);
  
  }

}


