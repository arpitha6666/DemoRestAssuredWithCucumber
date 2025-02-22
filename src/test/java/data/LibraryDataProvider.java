package data;

import org.testng.annotations.DataProvider;

import java.util.Random;

public class LibraryDataProvider {

    Random rand = new Random();

    @DataProvider
    public Object[][] addBookDataProvider(){
        return new Object[][]{
                {"rtre",rand.nextInt(1000)},
                {"ghgh",rand.nextInt(1000)},
                {"uyhgf",rand.nextInt(1000)}
        };

    }
}
