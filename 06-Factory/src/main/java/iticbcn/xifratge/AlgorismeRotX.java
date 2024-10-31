package iticbcn.xifratge;

/**
 * AlgorismeRotX
 */
public class AlgorismeRotX extends AlgorismeFactory {

  public Xifrador creaXifrador(){
    return new XifradorRotX();
  }
}