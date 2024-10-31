package iticbcn.xifratge;

import iticbcn.xifratge.algorisme.RotX;

public class XifradorRotX implements Xifrador {

  public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
    int rot;
    try {
      rot = Integer.parseInt(clau);

      if (rot < 0 || rot > 40 ) throw new Exception(); // La atrapa el catch de debajo
    } catch (Exception e) {
      throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
    }
    String result = RotX.xifraRot(clau, rot);

    return new TextXifrat(result.getBytes());
  }
  public String desxifra (TextXifrat xifrat, String clau) throws ClauNoSuportada {
    int rot;
    try {
      rot = Integer.parseInt(clau);

      if (rot < 0 || rot > 40 ) throw new Exception(); // La atrapa el catch de debajo
    } catch (Exception e) {
      throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
    }

    String textXifrat = new String(xifrat.getBytes());

    return RotX.desxifraRot(textXifrat, rot);
  }
}
