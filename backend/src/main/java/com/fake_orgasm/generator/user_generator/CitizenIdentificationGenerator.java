package com.fake_orgasm.generator.user_generator;

public class CitizenIdentificationGenerator {
    private long citizenIdentificationCounter;

    protected CitizenIdentificationGenerator(){
        citizenIdentificationCounter = 0;
    }

    protected long make(){
        citizenIdentificationCounter++;
        return citizenIdentificationCounter;
    }
}
