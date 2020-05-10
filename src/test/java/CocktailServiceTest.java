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
        System.out.println(drink.strDrink);
        System.out.println(drink.strDrinkThumb);

        System.out.println(drink.strMeasure1);
        System.out.println(drink.strIngredient1);
        System.out.println(drink.strMeasure2);
        System.out.println(drink.strIngredient2);
        System.out.println(drink.strMeasure3);
        System.out.println(drink.strIngredient3);
        System.out.println(drink.strMeasure4);
        System.out.println(drink.strIngredient4);

        System.out.println(drink.strInstructions);

        /*
        Whats the best way to get the ings and measurements?

        String curr;
        for(int ix = 1; ix < 15; ix++)
        {
            curr = "strIngredient" + ix;
            System.out.println(drink.curr);
        }
        */
    }
}