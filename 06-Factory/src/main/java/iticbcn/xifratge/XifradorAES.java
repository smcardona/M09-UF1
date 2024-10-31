package iticbcn.xifratge;

import iticbcn.xifratge.algorisme.AES;

public class XifradorAES implements Xifrador {


  public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
    byte[] bytes;
    try {
      bytes = AES.xifraAES(msg, clau);
    } catch (Exception e) {
      throw new RuntimeException("Error en el xifratge AES :"+e.getMessage());
    }
    
    return new TextXifrat(bytes);
  }

  public String desxifra (TextXifrat xifrat, String clau) throws ClauNoSuportada {

    try {
      return AES.desxifraAES(xifrat.getBytes(), clau);
    }
    catch (Exception e) {
      throw new RuntimeException("Error en el desxifratge AES :"+e.getMessage());
    }
  }

}