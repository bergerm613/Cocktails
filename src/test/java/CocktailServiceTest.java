import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class CocktailServiceTest
{

    @Test
    public void getCocktails() throws IOException
    {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CocktailService service = retrofit.create(CocktailService.class);

        //when
        CocktailFeed feed = service.getCocktails(
                "margarita").execute().body();

        //then
        assertNotNull(feed);
        List<CocktailFeed.Drinks> drinks = feed.drinks;

        assertFalse(drinks.isEmpty());

        CocktailFeed.Drinks drink = drinks.get(0);
        assertNotNull(drink.idDrink);
        assertNotNull(drink.strDrink);
        assertNotNull(drink.strAlcoholic);
        assertNotNull(drink.strGlass);
        assertNotNull(drink.strDrinkThumb);
    }
}