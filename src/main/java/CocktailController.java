import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class CocktailController
{
    private JLabel name;
    private JLabel ingredients;
    private JLabel instructions;
    private JLabel picture;
    private CocktailService service;
    private CocktailFeed.Drinks drink;
    private Random rand = new Random();

    public CocktailController(CocktailService service, JLabel name, JLabel ingredients, JLabel instructions, JLabel picture)
    {
        this.service = service;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.picture = picture;
    }

    public void getCocktails(String input)
    {
        service.getCocktails(input).enqueue(new Callback<CocktailFeed>()
        {
            @Override
            public void onResponse(Call<CocktailFeed> call, Response<CocktailFeed> response)
            {
                try
                {
                    drink = response.body().drinks.get(rand.nextInt(response.body().drinks.size()));
                    name.setText(drink.strDrink);

                    String[] ing = new String[18];
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
                    ing[12] = drink.strMeasure7;
                    ing[13] = drink.strIngredient7;
                    ing[14] = drink.strMeasure8;
                    ing[15] = drink.strIngredient8;
                    ing[16] = drink.strMeasure9;
                    ing[17] = drink.strIngredient9;

                    String answer = "";
                    for (int ix = 0; ix < 17; ix++)
                    {
                        if (ing[ix] != null)
                        {
                            answer += ing[ix];
                        }
                        if (ix % 2 == 1)
                        {
                            answer += "<br>";
                        }
                    }
                    ingredients.setText("<html>" + answer + "</html>");

                    instructions.setText("<html><p>" + drink.strInstructions + "</p></html>");

                    URL url = new URL(drink.strDrinkThumb);
                    Image image = ImageIO.read(url);
                    picture.setIcon(new ImageIcon(image));
                } catch (Exception e)
                {
                    name.setText("We can't seem to find that drink. Please try again.");
                    ingredients.setText("");
                    instructions.setText("");
                    picture.setIcon(new ImageIcon());
                }
            }

            @Override
            public void onFailure(Call<CocktailFeed> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }
}
