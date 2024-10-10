public class RotX {
  public static final String alphabet = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ";
  private static final char[] alphabetMayus = alphabet.toCharArray();
  private static final char[] alphabetMinus = alphabet.toLowerCase().toCharArray();


  public static String xifraRot (String input, int rot) {
    if (input == null || input.isBlank()) return "";
    StringBuilder result = new StringBuilder();

    for (char charact: input.toCharArray()) {
      if (! alphabet.contains(""+Character.toUpperCase(charact))) {
        result.append(charact);
        continue; // ignora si no está en el abecedario
      }

      int moved;
      if (Character.isUpperCase(charact)) {
        moved = (alphabet.indexOf(charact) + rot) % alphabet.length();
        result.append(alphabetMayus[moved]);
      }
      else {
        moved = (alphabet.toLowerCase().indexOf(charact) + rot) % alphabet.length();
        result.append(alphabetMinus[moved]);
      }

    }


    return result.toString();
  }

  public static String desxifraRot (String input, int rot) {
    rot = rot % alphabet.length();
    if (input == null || input.isBlank()) return "";
    StringBuilder result = new StringBuilder();

    for (char charact: input.toCharArray()) {
      if (! alphabet.contains(""+Character.toUpperCase(charact))) {
        result.append(charact);
        continue; // ignora si no está en el abecedario
      }

      int moved;
      int index;
      if (Character.isUpperCase(charact)) {
        index = alphabet.indexOf(charact);
        moved = Math.abs(index - rot + alphabet.length()) % alphabet.length();
        result.append(alphabetMayus[moved]);
      }
      else {
        index = alphabet.toLowerCase().indexOf(charact);
        moved = Math.abs(index - rot + alphabet.length()) % alphabet.length();
        result.append(alphabetMinus[moved]);
      }


    }


    return result.toString();
  }

  public static void main (String[] args) {
    if(args.length < 2) {
        System.out.println("No has introducido un texto para procesar por agumentos: java Rot13 <texto>");
        return;
    }

    final int rot = Integer.parseInt(args[1]);

    String input = args[0];
    String cifrado = xifraRot(input, rot);
    String descifrado = desxifraRot(cifrado, rot);
    
    System.out.println("Procesando input: "+ input);
    
    System.out.println("Cifrado: "+ cifrado);

    System.out.println("Descifrado: "+ descifrado);

    final int randomRot = (int) (Math.random() * alphabet.length());
    cifrado = xifraRot(input, randomRot);
    System.out.printf("Cifrado aleatorio rot%d: %s%n", randomRot, cifrado);

    System.out.println("Descifrado forzoso:");
    for (int i = 0; i <= alphabet.length(); i++) {
      descifrado = desxifraRot(cifrado, i);
      System.out.printf("%2d : - %s -%n", i, descifrado);
      if (descifrado.equals(input)) break;
    }


  }
}
