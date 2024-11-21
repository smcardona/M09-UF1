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

  public int npass = 0;

  public String getPBKDF2AmbSalt(String pw, String salt) {
    int iterations = 10;
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
        hash = sha.digest(pw.concat(salt).getBytes());
    } catch (NoSuchAlgorithmException e) {
        System.err.println(e);
    }

    return toHEX(hash);
  }

  public String forcaBruta(String alg, String hash, String salt) {
    final char[] CHARSET = "abcdefABCDEF1234567890!".toCharArray();
    char[] password = new char[6];
    
    npass = 0;
    int length = CHARSET.length;

    for (int i0 = 0; i0 < length; i0++) {
      for (int i1 = 0; i1 < length; i1++) {
        for (int i2 = 0; i2 < length; i2++) {
          for (int i3 = 0; i3 < length; i3++) {
            for (int i4 = 0; i4 < length; i4++) {
              for (int i5 = 0; i5 < length; i5++) {
                String passwordStr = new String(password).trim();
                if (testPassword(passwordStr, salt, alg).equals(hash)) {
                  return passwordStr;
                }
                // modifica indice 5
                password[5] = CHARSET[i5];
              }
              // modifica indice 4
              password[4] = CHARSET[i4];
            }
            // modifica indice 3
            password[3] = CHARSET[i3];
          }
          // modifica indice 2
          password[2] = CHARSET[i2];
        }
        // modifica indice 1
        password[1] = CHARSET[i1];
      }
      // modifica indice 0
      password[0] = CHARSET[i0];
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