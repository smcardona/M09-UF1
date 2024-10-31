package iticbcn.xifratge;

import iticbcn.xifratge.algorisme.Polialfabetic;

public class XifradorPolialfabetic implements Xifrador {


  public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
    long password;
    
    try {
      password = Long.parseLong(clau);
    } catch (NumberFormatException e) {
      throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
    }

    Polialfabetic.initRandom(password);
    String result = Polialfabetic.xifraPoliAlfa(msg);

    return new TextXifrat(result.getBytes());
  }
  public String desxifra (TextXifrat xifrat, String clau) throws ClauNoSuportada {
    long password;
    
    try {
      password = Long.parseLong(clau);
    } catch (NumberFormatException e) {
      throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
    }

    Polialfabetic.initRandom(password);
    String textXifrat = new String(xifrat.getBytes());
    String result = Polialfabetic.desxifraPoliAlfa(textXifrat);

    return result;
  }
}


