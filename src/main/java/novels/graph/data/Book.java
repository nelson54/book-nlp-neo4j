package novels.graph.data;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.util.List;

@NodeEntity
public class Book {

    String title;
    String author;

    LocalDateTime published;

    @Relationship
    List<Character> characters;

}
