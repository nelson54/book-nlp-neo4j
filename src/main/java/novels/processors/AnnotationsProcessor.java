package novels.processors;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;
import novels.Book;
import novels.Token;
import novels.entities.Paragraph;

import java.util.ArrayList;
import java.util.List;


public class AnnotationsProcessor {

    public List<Paragraph> run(Book book, Annotation document, List<Token> tokens) {

        List<CoreMap> paragraphsMaps = document.get(CoreAnnotations.ParagraphsAnnotation.class);

        List<Paragraph> paragraphs = new ArrayList<>();
        int paragraphIndex = 0;
        Paragraph paragraph = new Paragraph(paragraphIndex, book);
        for(Token word : book.tokens) {
            paragraph.addToken(word);
            if(word.whitespaceAfter.contains("NN")) {
                if(paragraph.getCharacters().size() > 0) {
                    paragraphs.add(paragraph);
                }
                paragraph = new Paragraph(++paragraphIndex, book);
            }
        }

        return paragraphs;
    }
}
