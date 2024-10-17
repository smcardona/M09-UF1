import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
  public static final String ALGORISME_XIFRAT = "AES";
  public static final String ALGORISME_HASH = "SHA-256";
  public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

  private static final int MIDA_IV = 16;
  private static final String CLAU = "VulguisQueSecretaClauLa<-i ñ i ç";

  public static byte[] xifraAES(String msg, String clau) throws Exception {
    //Obtenir els bytes de l’String
    byte[] toEncrypt = msg.getBytes();
    
    // Genera IvParameterSpec
    byte[] iv = new byte[MIDA_IV];
    initIV(iv);
    IvParameterSpec ivParam = new IvParameterSpec(iv);

    // Genera hash
    SecretKeySpec clauXifrada = convertSecretKey(clau);

    // Encripta
    Cipher xifrador = Cipher.getInstance(FORMAT_AES);
    xifrador.init(Cipher.ENCRYPT_MODE, clauXifrada, ivParam);
    byte[] msgXifrat = xifrador.doFinal(toEncrypt);

    // Combinar IV i part xifrada.
    byte[] resultat = new byte[MIDA_IV + msgXifrat.length];
    System.arraycopy(iv, 0, resultat, 0, MIDA_IV);
    System.arraycopy(msgXifrat, 0, resultat, MIDA_IV, msgXifrat.length);

    // return iv+msgxifrat
    return resultat;
  }


  public static String desxifraAES (byte[] xifrat , String clau) throws Exception {
    // Extreure l'IV.
    byte[] iv = new byte[MIDA_IV];
    System.arraycopy(xifrat, 0, iv, 0, MIDA_IV);
    IvParameterSpec ivParam = new IvParameterSpec(iv);

    // Extreure la part xifrada.
    int tamanyXifrat = xifrat.length - MIDA_IV;
    byte[] msgXifrat = new byte[tamanyXifrat];
    System.arraycopy(xifrat, MIDA_IV, msgXifrat, 0, tamanyXifrat);
    
    // Fer hash de la clau
    SecretKeySpec clauXifrada = convertSecretKey(clau);

    // Desxifrar.
    Cipher xifrador = Cipher.getInstance(FORMAT_AES);
    xifrador.init(Cipher.DECRYPT_MODE, clauXifrada, ivParam);
    byte[] resultat = xifrador.doFinal(msgXifrat);
    
    // return String desxifrat
    return new String(resultat);
  }

  private static void initIV(byte[] iv) {
    new SecureRandom().nextBytes(iv);
  }

  public static SecretKeySpec convertSecretKey(String clau) throws Exception {
    byte[] clauBytes = new byte[MIDA_IV];
    MessageDigest hash = MessageDigest.getInstance(ALGORISME_HASH);
    hash.update(clau.getBytes());
    System.arraycopy(hash.digest(), 0, clauBytes, 0, clauBytes.length);
    return new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);
  }

  public static void main(String[] args) {
    String msgs[] = {
      "Lorem ipsum dicet",
      "Hola Andrés cómo está tu cuñado",
      "Àgora ïlla Ôtto"
    };

    for (int i = 0; i < msgs.length; i++) {
      String msg = msgs[i];
      byte[] bXifrats = null;
      String desxifrat = "";
      try {
        bXifrats = xifraAES(msg, CLAU);
        desxifrat = desxifraAES (bXifrats, CLAU);
      } catch (Exception e) {
        System.err.println("Error de xifrat: " + e.getLocalizedMessage ());
      
      }
      System.out.println("--------------------" );
      System.out.println("Msg: " + msg);
      System.out.println("Enc: " + new String(bXifrats));
      System.out.println("DEC: " + desxifrat);
    }
  }
}