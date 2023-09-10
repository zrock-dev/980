package com.fake_orgasm.flight_generator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fake_orgasm.utils.RandomFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
