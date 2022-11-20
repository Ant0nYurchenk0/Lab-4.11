public class CharManager{
  private static String numbers = "0123456789";
  private static String letters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_";
  private static String punctuation = "[]{},`\\:;()";
  private static String operators = "+-=/*&|%!";
  private static String comment = "#";
  private static String newLine = "\n";
  private static String dot = ".";
  private static String doubleQuote = "\"";
  private static String singleQuote = "'";
  private static String whiteSpaces = " \b\t\n\f\r";

  public static CharacterType GetType(Character character){
    if (numbers.contains(character.toString()))
      return CharacterType.Number;
    if (letters.contains(character.toString()))
      return CharacterType.Letter;
    if (punctuation.contains(character.toString()))
      return CharacterType.Punctuation;
    if (operators.contains(character.toString()))
      return CharacterType.Operator;
    if (comment.contains(character.toString()))
      return CharacterType.Comment;
    if (newLine.contains(character.toString()))
      return CharacterType.NewLine;
    if (dot.contains(character.toString()))
      return CharacterType.Dot;
    if (doubleQuote.contains(character.toString()))
      return CharacterType.DoubleQuotes;
    if (singleQuote.contains(character.toString()))
      return CharacterType.SingleQuotes;
    if (whiteSpaces.contains(character.toString()))
      return CharacterType.Whitespace;
    return CharacterType.Unknown;
  }
}