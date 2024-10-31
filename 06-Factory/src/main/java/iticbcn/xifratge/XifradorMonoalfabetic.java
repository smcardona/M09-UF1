package iticbcn.xifratge;

import iticbcn.xifratge.algorisme.Monoalfabetic;

public class XifradorMonoalfabetic implements Xifrador{

  private Monoalfabetic xifrador = new Monoalfabetic();

  public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
    if (clau != null) throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");

    String result = xifrador.xifraMonoAlfa(msg);

    return new TextXifrat(result.getBytes());
  }
  public String desxifra (TextXifrat xifrat, String clau) throws ClauNoSuportada {
    if (clau != null) throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");

    String textXifrat = new String(xifrat.getBytes());

    return xifrador.desxifraMonoAlfa(textXifrat);
  }
}


