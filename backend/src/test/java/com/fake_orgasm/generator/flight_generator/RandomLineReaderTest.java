package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.generator.utils.RandomFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RandomLineReaderTest {

    private RandomFileReader randomFileReader;

    @BeforeEach
    public void setup() {
        String airportCountriesFilePath = "src/main/resources/generation/country_pool.txt";
        randomFileReader = new RandomFileReader(airportCountriesFilePath, 100);
    }

    @Test
    public void fileLoadedWithThreads() throws InterruptedException {
        randomFileReader.start();
        randomFileReader.join();
        assertNotNull(randomFileReader);
    }

    @Test
    public void getNotNullLineRandomly() throws InterruptedException {
        randomFileReader.start();
        randomFileReader.join();
        String randomLineGenerated = randomFileReader.getRandomLine();
        assertNotNull(randomLineGenerated);
    }

}