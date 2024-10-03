import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
 
  private static String ALPHABET = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ";
  private static String MONO_ALPH = permutaAlfabet(ALPHABET);

  public static String permutaAlfabet (String reference) {

    String[] divided = reference.split("");

    List<String> asCollection = Arrays.asList(divided);
    Collections.shuffle(asCollection);

    return String.join("", asCollection);
  }

  public static String xifraMonoAlfa (String input) {
    return translateWith(input, ALPHABET, MONO_ALPH);
  }

  public static String desxifraMonoAlfa (String input) {
    return translateWith(input, MONO_ALPH, ALPHABET);
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

  public static String translateWith (String input, String base, String translation) {
    if (input == null || input.isBlank()) return "";
    StringBuilder result = new StringBuilder();
  
    for (char c: input.toCharArray()) {
      if (base.indexOf(Character.toUpperCase(c)) == -1) {
        result.append(c);
        continue; // ignora si no está en el abecedario
      }
  
      char mono; 
      if (Character.isUpperCase(c)) {
        int index = base.toUpperCase().indexOf(c); // Tiene referencia de la base
        mono = Character.toUpperCase(translation.charAt(index)); // Optiene la equivalencia de la traduccion
      }
      else {
        int index = base.toLowerCase().indexOf(c); // Hace lo mismo pero teniendo en cuenta como minusculas
        mono = Character.toLowerCase(translation.charAt(index));
      }
  
      result.append(mono);
    }
  
    return result.toString();
  }

}


