package com.fake_orgasm.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.AirportLoader;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AirportLoaderTest {
    private AirportLoader airportLoader;

    /**
     * This method initializes airport loader in all tests.
     */
    @BeforeEach
    public void setUp() {
        airportLoader = AirportLoader.getInstance();
    }

    @Test
    void airportLoaderGetAndSizeTest() {
        List<Airport> airports = airportLoader.getAirports();
        assertEquals(100, airports.size());

        Airport firstAirport = airports.get(0);
        assertEquals("John F. Kennedy International Airport", firstAirport.getName());
        assertEquals("United States", firstAirport.getCountry());
        assertEquals("New York", firstAirport.getState());

        Airport lastAirport = airports.get(99);
        assertEquals("Zurich Airport", lastAirport.getName());
        assertEquals("Switzerland", lastAirport.getCountry());
        assertEquals("Zurich", lastAirport.getState());
    }

    @Test
    void airportLoaderAirportListTest() {
        List<Airport> airports = airportLoader.getAirports();

        String expectedListString = generateExpectedListString();
        String actualListString = generateActualListString(airports);

        assertEquals(expectedListString, actualListString);
    }

    private String generateExpectedListString() {
        StringBuilder expectedListStringBuilder = new StringBuilder();
        expectedListStringBuilder
                .append("1). John F. Kennedy International Airport - United States\n")
                .append("2). Heathrow Airport - United Kingdom\n")
                .append("3). Narita International Airport - Japan\n")
                .append("4). Incheon International Airport - South Korea\n")
                .append("5). Kuala Lumpur International Airport - Malaysia\n")
                .append("6). Auckland Airport - New Zealand\n")
                .append("7). Leonardo da Vinci–Fiumicino Airport - Italy\n")
                .append("8). Adolfo Suárez Madrid–Barajas Airport - Spain\n")
                .append("9). Ninoy Aquino International Airport - Philippines\n")
                .append("10). Jorge Chávez International Airport - Peru\n")
                .append("11). Tocumen International Airport - Panama\n")
                .append("12). King Abdulaziz International Airport - Saudi Arabia\n")
                .append("13). Sabiha Gökçen International Airport - Turkey\n")
                .append("14). El Alto International Airport - Bolivia\n")
                .append("15). Aeroparque Jorge Newbery - Argentina\n")
                .append("16). José Martí International Airport - Cuba\n")
                .append("17). Comalapa International Airport - El Salvador\n")
                .append("18). Jinnah International Airport - Pakistan\n")
                .append("19). Hazrat Shahjalal International Airport - Bangladesh\n")
                .append("20). Katowice Airport - Poland\n")
                .append("21). Vaclav Havel Airport Prague - Czech Republic\n")
                .append("22). Stockholm Arlanda Airport - Sweden\n")
                .append("23). Dublin Airport - Ireland\n")
                .append("24). Keflavik International Airport - Iceland\n")
                .append("25). Zurich Airport - Switzerland\n")
                .append("26). Cairo International Airport - Egypt\n")
                .append("27). Jomo Kenyatta International Airport - Kenya\n")
                .append("28). Murtala Muhammed International Airport - Nigeria\n")
                .append("29). Kotoka International Airport - Ghana\n")
                .append("30). Houari Boumediene Airport - Algeria\n")
                .append("31). Mohammed V International Airport - Morocco\n")
                .append("32). Beijing Capital International Airport - China\n")
                .append("33). Dubai International Airport - United Arab Emirates\n")
                .append("34). Charles de Gaulle Airport - France\n")
                .append("35). Sydney Kingsford Smith Airport - Australia\n")
                .append("36). Los Angeles International Airport - United States\n")
                .append("37). Shanghai Pudong International Airport - China\n")
                .append("38). Hong Kong International Airport - Hong Kong\n")
                .append("39). Gimpo International Airport - South Korea\n")
                .append("40). Zvartnots International Airport - Armenia\n")
                .append("41). Juba International Airport - South Sudan\n")
                .append("42). Antananarivo Ivato International Airport - Madagascar\n")
                .append("43). El Dorado International Airport - Colombia\n")
                .append("44). Murtala Muhammed International Airport - Nigeria\n")
                .append("45). Quito International Airport - Ecuador\n")
                .append("46). Alejandro Velasco Astete International Airport - Peru\n")
                .append("47). Tribhuvan International Airport - Nepal\n")
                .append("48). Lisbon Humberto Delgado Airport - Portugal\n")
                .append("49). Vienna International Airport - Austria\n")
                .append("50). King Fahd International Airport - Saudi Arabia\n")
                .append("51). Boryspil International Airport - Ukraine\n")
                .append("52). Pulkovo Airport - Russia\n")
                .append("53). Hamad International Airport - Qatar\n")
                .append("54). Budapest Ferenc Liszt International Airport - Hungary\n")
                .append("55). Queen Alia International Airport - Jordan\n")
                .append("56). Malta International Airport - Malta\n")
                .append("57). Ljubljana Jože Pučnik Airport - Slovenia\n")
                .append("58). Skopje Alexander the Great Airport - North Macedonia\n")
                .append("59). Oslo Gardermoen Airport - Norway\n")
                .append("60). Sofia Airport - Bulgaria\n")
                .append("61). Cologne Bonn Airport - Germany\n")
                .append("62). Riga International Airport - Latvia\n")
                .append("63). Tirana International Airport - Albania\n")
                .append("64). Copenhagen Airport - Denmark\n")
                .append("65). Bratislava Airport - Slovakia\n")
                .append("66). Larnaca International Airport - Cyprus\n")
                .append("67). Helsinki Airport - Finland\n")
                .append("68). Zagreb Airport - Croatia\n")
                .append("69). Chisinau International Airport - Moldova\n")
                .append("70). Bucharest Henri Coandă International Airport - Romania\n")
                .append("71). Vilnius Airport - Lithuania\n")
                .append("72). Sarajevo International Airport - Bosnia and Herzegovina\n")
                .append("73). Brussels Airport - Belgium\n")
                .append("74). Lyon–Saint-Exupéry Airport - France\n")
                .append("75). Palanga International Airport - Lithuania\n")
                .append("76). Vilnius Airport - Lithuania\n")
                .append("77). Tallinn Airport - Estonia\n")
                .append("78). Vilnius Airport - Lithuania\n")
                .append("79). Ljubljana Jože Pučnik Airport - Slovenia\n")
                .append("80). Skopje Alexander the Great Airport - North Macedonia\n")
                .append("81). Oslo Gardermoen Airport - Norway\n")
                .append("82). Sofia Airport - Bulgaria\n")
                .append("83). Cologne Bonn Airport - Germany\n")
                .append("84). Riga International Airport - Latvia\n")
                .append("85). Tirana International Airport - Albania\n")
                .append("86). Copenhagen Airport - Denmark\n")
                .append("87). Bratislava Airport - Slovakia\n")
                .append("88). Larnaca International Airport - Cyprus\n")
                .append("89). Helsinki Airport - Finland\n")
                .append("90). Zagreb Airport - Croatia\n")
                .append("91). Chisinau International Airport - Moldova\n")
                .append("92). Bucharest Henri Coandă International Airport - Romania\n")
                .append("93). Vilnius Airport - Lithuania\n")
                .append("94). Sarajevo International Airport - Bosnia and Herzegovina\n")
                .append("95). Brussels Airport - Belgium\n")
                .append("96). Lyon–Saint-Exupéry Airport - France\n")
                .append("97). Palanga International Airport - Lithuania\n")
                .append("98). Vilnius Airport - Lithuania\n")
                .append("99). Tallinn Airport - Estonia\n")
                .append("100). Zurich Airport - Switzerland\n");

        return expectedListStringBuilder.toString();
    }

    private String generateActualListString(List<Airport> airports) {
        StringBuilder actualListStringBuilder = new StringBuilder();
        for (int i = 0; i < airports.size(); i++) {
            actualListStringBuilder
                    .append((i + 1))
                    .append("). ")
                    .append(airports.get(i).getName())
                    .append(" - ")
                    .append(airports.get(i).getCountry())
                    .append("\n");
        }
        return actualListStringBuilder.toString();
    }
}