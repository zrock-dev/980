package com.fake_orgasm.generator;

import com.fake_orgasm.generator.generator_backbone.GeneratorBackbone;
import org.junit.jupiter.api.Test;

class BackboneGeneratorTest {

    @Test
    void generateUsersBackboneTest() {
        GeneratorBackbone generatorBackbone = new GeneratorBackbone();
        generatorBackbone.generateUsers(1000);
    }
}
