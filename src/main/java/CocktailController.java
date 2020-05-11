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
    private JLabel instructions;
    private JLabel picture;
    private CocktailService service;
    private CocktailFeed.Drinks drink;
    private Random rand = new Random();

    public CocktailController(CocktailService service, JLabel name, JLabel instructions, JLabel picture)
    {
        this.service = service;
        this.name = name;
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
                    instructions.setText("<html><p>" + drink.strInstructions + "</p></html>");

                    URL url = new URL(drink.strDrinkThumb);
                    Image image = ImageIO.read(url);
                    picture.setIcon(new ImageIcon(image));
                } catch (Exception e)
                {
                    name.setText("We can't seem to find that drink. Please try again.");
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
