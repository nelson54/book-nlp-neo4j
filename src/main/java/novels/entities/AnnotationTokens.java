package novels.entities;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import novels.Token;

import java.util.List;

public class AnnotationTokens {
    Annotation annotation;
    List<Token> tokens;

    public AnnotationTokens(Annotation annotation, List<Token> tokens) {
        this.annotation = annotation;
        this.tokens = tokens;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
