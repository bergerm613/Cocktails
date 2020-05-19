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
                "green").execute().body();

        //then
        assertNotNull(feed);
        List<CocktailFeed.Drinks> drinks = feed.drinks;

        assertFalse(drinks.isEmpty());

        CocktailFeed.Drinks drink = drinks.get(2);
        System.out.println(drink.strDrink);
        System.out.println(drink.strDrinkThumb);


        String[] ing = new String[12];
        ing[0] = drink.strMeasure1;
        ing[1] = drink.strIngredient1;
        ing[2] = drink.strMeasure2;
        ing[3] = drink.strIngredient2;
        ing[4] = drink.strMeasure3;
        ing[5] = drink.strIngredient3;
        ing[6] = drink.strMeasure4;
        ing[7] = drink.strIngredient4;
        ing[8] = drink.strMeasure5;
        ing[9] = drink.strIngredient5;
        ing[10] = drink.strMeasure6;
        ing[11] = drink.strIngredient6;

        String answer = "";
        for (int ix = 0; ix < 11; ix++)
        {
            if (ing[ix] != null)
            {
                answer += ing[ix];
            }
            if (ix % 2 == 1)
            {
                answer += "\n";
            }
        }

        System.out.println(answer);
    }

}