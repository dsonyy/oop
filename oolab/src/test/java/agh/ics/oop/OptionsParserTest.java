package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsParserTest {
    @Test
    public void parseTest() {
        assertArrayEquals(OptionsParser.parse("f"), new MoveDirection[]{MoveDirection.FORWARD});
        assertArrayEquals(OptionsParser.parse("b"), new MoveDirection[]{MoveDirection.BACKWARD});
        assertArrayEquals(OptionsParser.parse("r"), new MoveDirection[]{MoveDirection.RIGHT});
        assertArrayEquals(OptionsParser.parse("l"), new MoveDirection[]{MoveDirection.LEFT});

        assertArrayEquals(OptionsParser.parse("\t  \t\n\n\tr\nbrxxxr\t\tl"), new MoveDirection[]{
                MoveDirection.RIGHT, MoveDirection.LEFT});
        assertArrayEquals(OptionsParser.parse("brblbnf"), new MoveDirection[]{});
        assertArrayEquals(OptionsParser.parse("F B R L"), new MoveDirection[]{});
    }
}
