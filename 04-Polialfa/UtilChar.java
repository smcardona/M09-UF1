import java.util.List;

public class UtilChar {
  
  public static int indexOfCharFrom(char c, char[] list) {
    for (int i = 0; i< list.length; i++) {
      if (list[i] == c) return i; 
    }

    return -1;
  }

  public static Character[] charsToCharacters(char[] chars) {
    Character[] characters = new Character[chars.length];
    
    for (int i = 0; i < chars.length; i++) {
      characters[i] = chars[i];
    }
    return characters;
  }

  public static char[] listToChars(List<Character> list) {
    char[] chars = new char[list.size()];

    for (int i = 0; i < chars.length; i++) {
      chars[i] = list.get(i);
    }
    return chars;
  }
}
