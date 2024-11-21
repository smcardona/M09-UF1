import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Duration;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
  private static final String ALGO_SHA = "SHA-512";
  private static final String ALGO_PBKDF2 = "PBKDF2WithHmacSHA1";
  private static final String ALGO_PBKDF2_NAME = "PBKDF2";

  private final char[] CHARSET = "abcdefABCDEF1234567890!".toCharArray();

  public int npass = 0;

  public String getPBKDF2AmbSalt(String pw, String salt) {
    int iterations = 65000;
    int length = 128;
    PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, length);
    byte[] hash = null;
    try {
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGO_PBKDF2);
        hash = skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        System.err.println(e);
    }

    return toHEX(hash);
  }

  public String getSHA512AmbSalt(String pw, String salt) {
    MessageDigest sha = null;
    byte[] hash = null;
    try {
        sha = MessageDigest.getInstance(ALGO_SHA);
        sha.update(salt.getBytes());
        hash = sha.digest(pw.getBytes());
    } catch (NoSuchAlgorithmException e) {
        System.err.println(e);
    }

    return toHEX(hash);
  }

  public String forcaBruta(String alg, String hash, String salt) {

    npass = 0;
    int maxLength = 6;

    // Prueba las combinaciones por longitudes fijas, decrecientes
    for (int size = maxLength; size >= 1; size--) {
      char[] password = new char[size];

      String pass = fixedLengthPasswordTest(password, salt, hash, alg);
      if (pass != null) return pass;
  }

    return null;
  }

  public String testPassword(String password, String salt, String alg) {
    npass++;
    switch (alg) {
      case ALGO_SHA: return getSHA512AmbSalt(password, salt); 
    
      case ALGO_PBKDF2_NAME: return getPBKDF2AmbSalt(password, salt); 

      default: throw new RuntimeException("Algoritmo no soportado");
    }
  }

  private String fixedLengthPasswordTest(char[] password, String salt, String hash, String alg) {
    
    int charsetLength = CHARSET.length;
    
    int size = password.length;

    // Crear bucles anidados dinámicamente según la longitud del password
    int[] indices = new int[size];

    while (true) {

      // Construir la contraseña actual dependiendo de los valores de los indices
      for (int i = 0; i < size; i++) {
        password[i] = CHARSET[indices[i]];
      }

      // Prueba la contraseña
      String passwordStr = new String(password).trim();
      if (testPassword(passwordStr, salt, alg).equals(hash)) {
        return passwordStr;
      }

      
      // Incrementar los índices de derecha a izquierda si el indice es el ultimo de la cadena 
      int position = size - 1;
      while (position >= 0) {
        indices[position]++;
        if (indices[position] < charsetLength) {
          break;
        }
        indices[position] = 0;
        position--;
      }

      // Si el índice más a la izquierda desbordó, termina
      if (position < 0) {
        break;
      }
    }

    return null;
  }

  public String getInterval(long t1, long t2) {
    Duration duration = Duration.ofMillis(t2 - t1);
    long days = duration.toDaysPart();
    long hours = duration.toHoursPart();
    long minutes = duration.toMinutesPart();
    long seconds = duration.toSecondsPart();
    long millis = duration.toMillisPart();
    return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds, millis);
  }

  public static String toHEX (byte[] bytes) {
    if (bytes == null) return null;
    HexFormat hex = HexFormat.of();
    return hex.formatHex(bytes);
  }

  public static void main(String[] args) throws Exception {
    String salt = "qpoweiruañslkdfjz";
    String pw = "aaabF!";
    Hashes h = new Hashes();
    String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
    String pwTrobat = null;
    String[] algorismes = {"SHA-512", "PBKDF2"};

    for(int i=0; i< aHashes.length; i++){
      System.out.printf("===========================\n");
      System.out.printf("Algorisme: %s\n", algorismes[i]);
      System.out.printf("Hash: %s\n",aHashes[i]);
      System.out.printf("---------------------------\n");
      System.out.printf("-- Inici de força bruta ---\n");

      long t1 = System.currentTimeMillis();
      pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
      long t2 = System.currentTimeMillis();

      System.out.printf("Pass : %s\n", pwTrobat);
      System.out.printf("Provats: %d\n", h.npass);
      System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
      System.out.printf("---------------------------\n\n");
    }
  }
}