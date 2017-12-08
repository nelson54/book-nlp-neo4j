package novels.entities;

import edu.stanford.nlp.util.Characters;
import novels.Book;
import novels.BookCharacter;
import novels.Token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Paragraph {
    Book book;
    Set<Integer> characters;
    int paragraphIndex;
    List<Token> tokens;

    public Paragraph(int paragraphIndex, Book book) {
        this.book = book;
        this.paragraphIndex = paragraphIndex;
        characters = new HashSet<>();
        tokens = new ArrayList<>();
    }

    public void addToken(Token token){
        this.tokens.add(token);

        addCharacterIfExists(token);
        addCharacterRefsIfExists(token);
    }

    public List<BookCharacter> getCharacters() {
        return this.characters
                .stream()
                .map((id)-> book.characters[id])
                .collect(Collectors.toList());
    }

    private void addCharacterIfExists(Token token) {
        if(book.tokenToCharacter.containsKey(token.tokenId)) {
            Antecedent antecedent =  book.tokenToCharacter.get(token.tokenId);
            characters.add(antecedent.getCharacterId());
        }

    }

    private void addCharacterRefsIfExists(Token token) {
        if(book.animateEntities.containsKey(token.tokenId)) {
            Antecedent antecedent = book.animateEntities.get(token.tokenId);
            Integer characterId = antecedent.getCharacterId();

            if(characterId >=0) {
                this.characters.add(characterId);
            }
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        this.tokens
                .stream()
                .forEachOrdered((token) -> {
                    sb.append(token.word);

                    if(token.whitespaceAfter.contains("S")) {
                        sb.append(" ");
                    }
                });

        return sb.toString();
    }
}
