package iticbcn.xifratge;

public interface Xifrador {
  
  public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;

  public String desxifra(TextXifrat msg, String clau) throws ClauNoSuportada;

}