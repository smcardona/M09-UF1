

package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

class ClauPublica {
  
  private static final String ALGORISME_XIFRATGE = "RSA";
  private static final int AMPLADA_CLAU = 2048;
  
  public KeyPair generaParellClausRSA() throws Exception {
    KeyPairGenerator generador = KeyPairGenerator.getInstance(ALGORISME_XIFRATGE);
    generador.initialize(AMPLADA_CLAU);
    return generador.generateKeyPair();
  }

  public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
    Cipher xifrador = Cipher.getInstance(ALGORISME_XIFRATGE);
    xifrador.init(Cipher.ENCRYPT_MODE, clauPublica);
    
    byte[] missatgeXifrat = xifrador.doFinal(msg.getBytes());
    return missatgeXifrat;
  }

  public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
    Cipher xifrador = Cipher.getInstance(ALGORISME_XIFRATGE);
    xifrador.init(Cipher.DECRYPT_MODE, clauPrivada);

    byte[] missatgeDesxifrat = xifrador.doFinal(msgXifrat);
    return new String(missatgeDesxifrat);
  }
  
}