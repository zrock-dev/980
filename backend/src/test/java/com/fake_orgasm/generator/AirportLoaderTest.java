package com.fake_orgasm.generator;
import com.fake_orgasm.generator.flight_history_generator.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AirportLoaderTest {
    private AirportLoader airportLoader;

    @BeforeEach
    public void setUp() {
        airportLoader = AirportLoader.getInstance();
    }


    @Test
    void airportLoaderGetAndSizeTest() {
        List<Airport> airports = airportLoader.getAirports();
        assertEquals(100, airports.size());

        Airport firstAirport = airports.get(0);
        assertEquals("John F. Kennedy International Airport", firstAirport.getAirportName());
        assertEquals("United States", firstAirport.getCountry());
        assertEquals("New York", firstAirport.getState());

        Airport lastAirport = airports.get(99);
        assertEquals("Zurich Airport", lastAirport.getAirportName());
        assertEquals("Switzerland", lastAirport.getCountry());
        assertEquals("Zurich", lastAirport.getState());
    }
    @Test
    void airportLoaderAirportListTest(){
        List<Airport> airports = airportLoader.getAirports();
        String expectedListString = "1). John F. Kennedy International Airport - United States\n" +
                "2). Heathrow Airport - United Kingdom\n" +
                "3). Narita International Airport - Japan\n" +
                "4). Incheon International Airport - South Korea\n" +
                "5). Kuala Lumpur International Airport - Malaysia\n" +
                "6). Auckland Airport - New Zealand\n" +
                "7). Leonardo da Vinci–Fiumicino Airport - Italy\n" +
                "8). Adolfo Suárez Madrid–Barajas Airport - Spain\n" +
                "9). Ninoy Aquino International Airport - Philippines\n" +
                "10). Jorge Chávez International Airport - Peru\n" +
                "11). Tocumen International Airport - Panama\n" +
                "12). King Abdulaziz International Airport - Saudi Arabia\n" +
                "13). Sabiha Gökçen International Airport - Turkey\n" +
                "14). El Alto International Airport - Bolivia\n" +
                "15). Aeroparque Jorge Newbery - Argentina\n" +
                "16). José Martí International Airport - Cuba\n" +
                "17). Comalapa International Airport - El Salvador\n" +
                "18). Jinnah International Airport - Pakistan\n" +
                "19). Hazrat Shahjalal International Airport - Bangladesh\n" +
                "20). Katowice Airport - Poland\n" +
                "21). Vaclav Havel Airport Prague - Czech Republic\n" +
                "22). Stockholm Arlanda Airport - Sweden\n" +
                "23). Dublin Airport - Ireland\n" +
                "24). Keflavik International Airport - Iceland\n" +
                "25). Zurich Airport - Switzerland\n" +
                "26). Cairo International Airport - Egypt\n" +
                "27). Jomo Kenyatta International Airport - Kenya\n" +
                "28). Murtala Muhammed International Airport - Nigeria\n" +
                "29). Kotoka International Airport - Ghana\n" +
                "30). Houari Boumediene Airport - Algeria\n" +
                "31). Mohammed V International Airport - Morocco\n" +
                "32). Beijing Capital International Airport - China\n" +
                "33). Dubai International Airport - United Arab Emirates\n" +
                "34). Charles de Gaulle Airport - France\n" +
                "35). Sydney Kingsford Smith Airport - Australia\n" +
                "36). Los Angeles International Airport - United States\n" +
                "37). Shanghai Pudong International Airport - China\n" +
                "38). Hong Kong International Airport - Hong Kong\n" +
                "39). Gimpo International Airport - South Korea\n" +
                "40). Zvartnots International Airport - Armenia\n" +
                "41). Juba International Airport - South Sudan\n" +
                "42). Antananarivo Ivato International Airport - Madagascar\n" +
                "43). El Dorado International Airport - Colombia\n" +
                "44). Murtala Muhammed International Airport - Nigeria\n" +
                "45). Quito International Airport - Ecuador\n" +
                "46). Alejandro Velasco Astete International Airport - Peru\n" +
                "47). Tribhuvan International Airport - Nepal\n" +
                "48). Lisbon Humberto Delgado Airport - Portugal\n" +
                "49). Vienna International Airport - Austria\n" +
                "50). King Fahd International Airport - Saudi Arabia\n" +
                "51). Boryspil International Airport - Ukraine\n" +
                "52). Pulkovo Airport - Russia\n" +
                "53). Hamad International Airport - Qatar\n" +
                "54). Budapest Ferenc Liszt International Airport - Hungary\n" +
                "55). Queen Alia International Airport - Jordan\n" +
                "56). Malta International Airport - Malta\n" +
                "57). Ljubljana Jože Pučnik Airport - Slovenia\n" +
                "58). Skopje Alexander the Great Airport - North Macedonia\n" +
                "59). Oslo Gardermoen Airport - Norway\n" +
                "60). Sofia Airport - Bulgaria\n" +
                "61). Cologne Bonn Airport - Germany\n" +
                "62). Riga International Airport - Latvia\n" +
                "63). Tirana International Airport - Albania\n" +
                "64). Copenhagen Airport - Denmark\n" +
                "65). Bratislava Airport - Slovakia\n" +
                "66). Larnaca International Airport - Cyprus\n" +
                "67). Helsinki Airport - Finland\n" +
                "68). Zagreb Airport - Croatia\n" +
                "69). Chisinau International Airport - Moldova\n" +
                "70). Bucharest Henri Coandă International Airport - Romania\n" +
                "71). Vilnius Airport - Lithuania\n" +
                "72). Sarajevo International Airport - Bosnia and Herzegovina\n" +
                "73). Brussels Airport - Belgium\n" +
                "74). Lyon–Saint-Exupéry Airport - France\n" +
                "75). Palanga International Airport - Lithuania\n" +
                "76). Vilnius Airport - Lithuania\n" +
                "77). Tallinn Airport - Estonia\n" +
                "78). Vilnius Airport - Lithuania\n" +
                "79). Ljubljana Jože Pučnik Airport - Slovenia\n" +
                "80). Skopje Alexander the Great Airport - North Macedonia\n" +
                "81). Oslo Gardermoen Airport - Norway\n" +
                "82). Sofia Airport - Bulgaria\n" +
                "83). Cologne Bonn Airport - Germany\n" +
                "84). Riga International Airport - Latvia\n" +
                "85). Tirana International Airport - Albania\n" +
                "86). Copenhagen Airport - Denmark\n" +
                "87). Bratislava Airport - Slovakia\n" +
                "88). Larnaca International Airport - Cyprus\n" +
                "89). Helsinki Airport - Finland\n" +
                "90). Zagreb Airport - Croatia\n" +
                "91). Chisinau International Airport - Moldova\n" +
                "92). Bucharest Henri Coandă International Airport - Romania\n" +
                "93). Vilnius Airport - Lithuania\n" +
                "94). Sarajevo International Airport - Bosnia and Herzegovina\n" +
                "95). Brussels Airport - Belgium\n" +
                "96). Lyon–Saint-Exupéry Airport - France\n" +
                "97). Palanga International Airport - Lithuania\n" +
                "98). Vilnius Airport - Lithuania\n" +
                "99). Tallinn Airport - Estonia\n" +
                "100). Zurich Airport - Switzerland\n";

        StringBuilder actualListStringBuilder = new StringBuilder();
        for (int i = 0; i < airports.size(); i++) {
            actualListStringBuilder.append((i + 1)).append("). ")
                    .append(airports.get(i).getAirportName()).append(" - ")
                    .append(airports.get(i).getCountry()).append("\n");
        }
        String actualListString = actualListStringBuilder.toString();

        assertEquals(expectedListString, actualListString);
    }
}
