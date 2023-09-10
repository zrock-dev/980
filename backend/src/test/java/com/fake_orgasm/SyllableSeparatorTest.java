package com.fake_orgasm;

import com.fake_orgasm.users_management.libs.btree.SyllableSeparator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SyllableSeparatorTest {
    @Test
    void splitTest() {
        SyllableSeparator syllableSeparator = new SyllableSeparator();
        List<String> result = syllableSeparator.split("Jhonatan");
        List<String> expectedResult = List.of("Jho", "na", "tan");

        Assertions.assertEquals(expectedResult, result);

        result = syllableSeparator.split("Jhon");
        expectedResult = List.of("Jhon");

        Assertions.assertEquals(expectedResult, result);

        result = syllableSeparator.split("Jh");
        expectedResult = List.of("Jh");

        Assertions.assertEquals(expectedResult, result);

        result = syllableSeparator.split("Aaron");
        expectedResult = List.of("Aa", "ron");

        Assertions.assertEquals(expectedResult, result);

        result = syllableSeparator.split("esprella");
        expectedResult = List.of("esp", "rel", "la");

        Assertions.assertEquals(expectedResult, result);
    }
}
