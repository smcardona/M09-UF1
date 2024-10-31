package iticbcn.xifratge.algorisme;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

  private static final long clauSecreta = 25522552L;
  private static Random rnd = initRandom(clauSecreta);
  private static final char[] ALPHABET = new char[] {
    'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
    'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M',
    'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
    'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
  };

  public static Random initRandom(long password) {
    rnd = new Random(password);
    return rnd;
  }

  public static char[] permutaAlfabet (char[] reference) {
    Character[] characters = UtilChar.charsToCharacters(reference);
    List<Character> copy = Arrays.asList(characters);
    Collections.shuffle(copy, rnd);

    return UtilChar.listToChars(copy);
  }

  public static String xifraPoliAlfa (String input) {
    // Inicializar random
    StringBuilder result = new StringBuilder();
    for (char letra: input.toCharArray()){
      char[] PERMUTED = permutaAlfabet(ALPHABET);
      char translated = translateWith(letra, ALPHABET, PERMUTED);
      result.append(translated);
    }
    
    return result.toString();
  }

  public static String desxifraPoliAlfa (String input) {
    StringBuilder result = new StringBuilder();

    for (char letra: input.toCharArray()){
      char[] PERMUTED = permutaAlfabet(ALPHABET);
      char translated = translateWith(letra, PERMUTED, ALPHABET);
      result.append(translated);
    }
    
    return result.toString();
  }


  
public static void main(String[] args) {
  String msgs [] ={"Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
  String msgsXifrats [] = new String [msgs.length];
  System.out.println ("Xifratge: \n--------") ; 
  for (int i = 0; i < msgs.length; i++) { 
    initRandom (clauSecreta); 
    msgsXifrats [i] = xifraPoliAlfa (msgs [i]);
    System.out.printf("%-34s -> %s\n", msgs [i], msgsXifrats [i]);
  }
  System.out.println("Desxifratge: \n-----------" );
  for (int i = 0; i < msgs.length; i++) {
    initRandom (clauSecreta);
    String msg = desxifraPoliAlfa(msgsXifrats [i]);
    System.out.printf("%-34s -> %s\n", msgsXifrats [i], msg);
  }

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
