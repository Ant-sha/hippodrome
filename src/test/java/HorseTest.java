import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {


    @Test
    public void NullNameExeption(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null,-1,1));
    }

@Test
public void NullNameMessage() {
    try {
        new Horse(null, 1, 1);

    } catch (IllegalArgumentException e) {
        assertEquals("Name cannot be null.", e.getMessage());
    }
}
        @ParameterizedTest
        @ValueSource(strings = {"", "   ","\t\t","\n\n\n"})
        public void blankNameException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name,1,1));
               assertEquals("Name cannot be blank.",e.getMessage());
    }
@ParameterizedTest
@ValueSource(ints ={-1,-2,-3})
public void negativeSpeedException(int ints){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("somename",ints,1));
        assertEquals("Speed cannot be negative.",e.getMessage());
}


   @Test
    void getName() throws NoSuchFieldException, IllegalAccessException {
    Horse horse = new Horse("qwerty",1,1);
    assertEquals("qwerty", horse.getName());}

    @Test
    void getSpeed() {
        Horse horse = new Horse("qwerty",555,1);
        assertEquals(555,horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("qwerty", 1,232);
        assertEquals(232,horse.getDistance());
    }


    @Test
    void moveUsesGetRandom() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse ("qwerty",31,223).move();
mockedStatic.verify(()-> Horse.getRandomDouble(0.2,0.9));
        }

    }

    @ParameterizedTest
    @ValueSource (doubles= {0.1,0.2,0.5, 0.9, 1.0, 999.999, 0.0})
    void move(double random) {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("qwerty", 31,283);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
            horse.move();
            assertEquals(283+31*random,horse.getDistance());
        }
    }



    @Test
    void zeroDistanceByDefault(){
        Horse horse = new Horse("qwerty",1);
        assertEquals(0,horse.getDistance());

    }

}