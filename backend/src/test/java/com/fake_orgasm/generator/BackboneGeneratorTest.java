package com.fake_orgasm.generator;

import com.fake_orgasm.generator.generator_backbone.GeneratorBackbone;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BackboneGeneratorTest {

    @Test
    void verifyNameIsComplete() {
        GeneratorBackbone generatorBackbone = new GeneratorBackbone();
        generatorBackbone.generateUsers(1000);
    }

}
