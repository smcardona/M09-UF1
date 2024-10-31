package iticbcn.xifratge;

/**
 * AlgorismeAES
 */
public class AlgorismeAES extends AlgorismeFactory {

  public Xifrador creaXifrador(){
    return new XifradorAES();
  }
  
}