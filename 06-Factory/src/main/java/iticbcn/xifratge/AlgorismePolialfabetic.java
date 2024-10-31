package iticbcn.xifratge;

/**
 * AlgorismePolialfabetic
 */
public class AlgorismePolialfabetic extends AlgorismeFactory {

  public Xifrador creaXifrador(){
    return new XifradorPolialfabetic();
  }
}