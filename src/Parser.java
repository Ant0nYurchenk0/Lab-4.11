import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pair
{
    public static <T, U> Map.Entry<T, U> of(T first, U second) {
        return new AbstractMap.SimpleEntry<>(first, second);
    }
}

public class Parser {

  private State currentState;
  private String currentLexem;
  private List<Lexem> lexems;
  private Map<Map.Entry<State, CharacterType>, State> transitions;

  public Parser(){
    currentState = State.Whitespace;
    currentLexem = "";
    lexems = new ArrayList<Lexem>();
    transitions = new HashMap<>();

    transitions.put(Pair.of(State.Number, CharacterType.Number), State.Number); 
    transitions.put(Pair.of(State.Number, CharacterType.Letter), State.NonRecognizable); 
    transitions.put(Pair.of(State.Number, CharacterType.Punctuation), State.Punctuation); 
    transitions.put(Pair.of(State.Number, CharacterType.Whitespace), State.Whitespace); 
    transitions.put(Pair.of(State.Number, CharacterType.Operator), State.Operator); 
    transitions.put(Pair.of(State.Number, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.Number, CharacterType.NewLine), State.Whitespace); 
    transitions.put(Pair.of(State.Number, CharacterType.Dot), State.Number); 
    transitions.put(Pair.of(State.Number, CharacterType.DoubleQuotes), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.Number, CharacterType.SingleQuotes), State.SingleQuotedString); 
    transitions.put(Pair.of(State.Number, CharacterType.Unknown), State.NonRecognizable); 
    
    transitions.put(Pair.of(State.Comment, CharacterType.Number), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.Letter), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.Punctuation), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.Whitespace), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.Operator), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.NewLine), State.Whitespace); 
    transitions.put(Pair.of(State.Comment, CharacterType.Dot), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.DoubleQuotes), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.SingleQuotes), State.Comment); 
    transitions.put(Pair.of(State.Comment, CharacterType.Unknown), State.Comment); 
    
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.Number), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.Letter), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.Punctuation), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.Whitespace), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.Operator), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.NewLine), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.Dot), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.DoubleQuotes), State.Whitespace); 
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.SingleQuotes), State.DoubleQuotedString);
    transitions.put(Pair.of(State.DoubleQuotedString, CharacterType.Unknown), State.DoubleQuotedString); 
    
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.Number), State.SingleQuotedString); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.Letter), State.SingleQuotedString); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.Punctuation), State.SingleQuotedString); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.Whitespace), State.SingleQuotedString); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.Operator), State.SingleQuotedString); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.NewLine), State.Whitespace); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.Dot), State.SingleQuotedString); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.DoubleQuotes), State.Whitespace); 
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.SingleQuotes), State.SingleQuotedString);
    transitions.put(Pair.of(State.SingleQuotedString, CharacterType.Unknown), State.SingleQuotedString); 
    
    transitions.put(Pair.of(State.Operator, CharacterType.Number), State.Number); 
    transitions.put(Pair.of(State.Operator, CharacterType.Letter), State.Identificator); 
    transitions.put(Pair.of(State.Operator, CharacterType.Punctuation), State.Punctuation); 
    transitions.put(Pair.of(State.Operator, CharacterType.Whitespace), State.Whitespace); 
    transitions.put(Pair.of(State.Operator, CharacterType.Operator), State.Operator); 
    transitions.put(Pair.of(State.Operator, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.Operator, CharacterType.NewLine), State.Whitespace); 
    transitions.put(Pair.of(State.Operator, CharacterType.Dot), State.Punctuation); 
    transitions.put(Pair.of(State.Operator, CharacterType.DoubleQuotes), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.Operator, CharacterType.SingleQuotes), State.SingleQuotedString);
    transitions.put(Pair.of(State.Operator, CharacterType.Unknown), State.NonRecognizable); 
    
    transitions.put(Pair.of(State.Identificator, CharacterType.Number), State.Identificator); 
    transitions.put(Pair.of(State.Identificator, CharacterType.Letter), State.Identificator); 
    transitions.put(Pair.of(State.Identificator, CharacterType.Punctuation), State.Punctuation); 
    transitions.put(Pair.of(State.Identificator, CharacterType.Whitespace), State.Whitespace); 
    transitions.put(Pair.of(State.Identificator, CharacterType.Operator), State.Operator); 
    transitions.put(Pair.of(State.Identificator, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.Identificator, CharacterType.NewLine), State.Whitespace); 
    transitions.put(Pair.of(State.Identificator, CharacterType.Dot), State.Punctuation); 
    transitions.put(Pair.of(State.Identificator, CharacterType.DoubleQuotes), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.Identificator, CharacterType.SingleQuotes), State.SingleQuotedString);
    transitions.put(Pair.of(State.Identificator, CharacterType.Unknown), State.NonRecognizable); 
    
    transitions.put(Pair.of(State.Punctuation, CharacterType.Number), State.Number); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.Letter), State.Identificator); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.Punctuation), State.Punctuation); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.Whitespace), State.Whitespace); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.Operator), State.Operator); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.NewLine), State.Whitespace); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.Dot), State.Punctuation); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.DoubleQuotes), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.Punctuation, CharacterType.SingleQuotes), State.SingleQuotedString);
    transitions.put(Pair.of(State.Punctuation, CharacterType.Unknown), State.NonRecognizable); 
    
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.Number), State.NonRecognizable); 
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.Letter), State.NonRecognizable); 
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.Punctuation), State.Punctuation); 
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.Whitespace), State.Whitespace); 
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.Operator), State.Operator);
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.NewLine), State.Whitespace); 
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.Dot), State.Punctuation); 
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.DoubleQuotes), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.SingleQuotes), State.SingleQuotedString);
    transitions.put(Pair.of(State.NonRecognizable, CharacterType.Unknown), State.NonRecognizable); 
    
    transitions.put(Pair.of(State.Whitespace, CharacterType.Number), State.Number); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.Letter), State.Identificator); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.Punctuation), State.Punctuation); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.Whitespace), State.Whitespace); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.Operator), State.Operator); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.Comment), State.Comment); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.NewLine), State.Whitespace); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.Dot), State.Punctuation); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.DoubleQuotes), State.DoubleQuotedString); 
    transitions.put(Pair.of(State.Whitespace, CharacterType.SingleQuotes), State.SingleQuotedString);
    transitions.put(Pair.of(State.Whitespace, CharacterType.Unknown), State.NonRecognizable); 
  }
  
  public List<Lexem> Parse(String all){

    for (Character character : all.toCharArray()) {
      var nextState = transitions.get(Pair.of(currentState, CharManager.GetType(character)));
      if (nextState != currentState){
        pushLexem();
      }
      currentLexem += character;  
      currentState = nextState;  
    }
    pushLexem();

    return lexems;
  }

  private void pushLexem() {
    switch(currentState) {
      case Number:
        pushNumber();
        break;
      case Comment:
        pushComment();
        break;
      case DoubleQuotedString:
        pushString();
        break;
      case SingleQuotedString:
        pushString();
        break;
      case Operator:
        pushOperator(); 
        break;
      case Identificator:
        pushIdentificator();
        break;
      case Punctuation:
        pushPunctuation();
        break;
      case NonRecognizable:
        pushNonRec();
        break;
      case Whitespace:
        currentLexem = "";
        break;
      default:
        break;
    }
  }

  private void pushNonRec() {
    var lexeme = new Lexem(){{
      Notation = currentLexem;
      LexemType = LexemType.NonRecognizable;
    }};
    lexems.add(lexeme);
    currentLexem = "";
  }

  private void pushPunctuation() {
    var punctList = currentLexem.split("");
    for (String punctuation : punctList) {
      var lexeme = new Lexem(){{
        Notation = punctuation;
        LexemType = LexemType.Punctuation;
      }};
      lexems.add(lexeme);
    }    
    currentLexem = "";
  }

  private void pushIdentificator() {
    var type = LexemType.Identificator;
    if (Arrays.asList(Constants.Keywords).contains(currentLexem))
      type = LexemType.Keyword;
    var lexeme = new Lexem();
    lexeme.Notation = currentLexem;
    lexeme.LexemType = type;
    lexems.add(lexeme);
    currentLexem = ""; 
  }

  private void pushOperator() {
    var type = LexemType.NonRecognizable;
    if (Arrays.asList(Constants.Operators).contains(currentLexem))
      type = LexemType.Operator;
    var lexeme = new Lexem();
    lexeme.Notation = currentLexem;
    lexeme.LexemType = type;
    lexems.add(lexeme);
    currentLexem = "";      
  }

  private void pushString() {
    var lexeme = new Lexem(){{
      var endSymbol = "'";
      if (currentState == State.DoubleQuotedString)
        endSymbol="\"";
      Notation = currentLexem + endSymbol;
      LexemType = LexemType.String;
    }};
    lexems.add(lexeme);
    currentLexem = "";
  }

  private void pushComment() {
    var lexeme = new Lexem(){{
      Notation = currentLexem;
      LexemType = LexemType.Comment;
    }};
    lexems.add(lexeme);
    currentLexem = "";
  }

  private void pushNumber() {
    var lexeme = new Lexem(){{
      Notation = currentLexem;
      LexemType = LexemType.Number;
    }};
    lexems.add(lexeme);
    currentLexem = "";
  }
  
}
