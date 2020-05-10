import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CocktailFrame extends JFrame
{
    private CocktailService service;
    private CocktailFeed.Drinks drinks;

    private final JLabel pictureLabel;
    private final JPanel inputPanel;
    private final JPanel descriptionPanel;
    private final JLabel titleLabel;
    private final JTextField inputField;
    private final JButton searchButton;
    private final JButton clearButton;
    private final JLabel nameLabel;
    private final JLabel instructionsLabel;

    private final JPanel topPanel;
    private final JPanel centerPanel;

    public CocktailFrame()
    {
        setSize(800,600);
        setTitle("Cocktail Finder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        titleLabel = new JLabel("Are you ready to find your new favorite drink?", SwingConstants.CENTER);
        inputField = new JTextField();
        searchButton = new JButton("SEARCH");
        searchButton.addActionListener(actionEvent -> searchCocktail());
        clearButton = new JButton("CLEAR");
        clearButton.addActionListener(actionEvent -> clear());

        nameLabel = new JLabel("", SwingConstants.CENTER);
        instructionsLabel = new JLabel();
        pictureLabel = new JLabel();

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,1));
        inputPanel.add(inputField);
        inputPanel.add(searchButton);
        inputPanel.add(clearButton);

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,2));
        topPanel.add(titleLabel);
        topPanel.add(inputPanel);

        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(2,1));
        descriptionPanel.add(nameLabel);
        descriptionPanel.add(instructionsLabel);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2));
        centerPanel.add(pictureLabel);
        centerPanel.add(descriptionPanel);


        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(CocktailService.class);
    }

    private void clear()
    {
        inputField.setText("");
        nameLabel.setText("");
        instructionsLabel.setText("");
        pictureLabel.setIcon(new ImageIcon());
    }

    private void searchCocktail()
    {
        service.getCocktails(inputField.getText()).enqueue(new Callback<CocktailFeed>()
        {
            @Override
            public void onResponse(Call<CocktailFeed> call, Response<CocktailFeed> response)
            {
                try
                {
                    drinks = response.body().drinks.get(0);
                    nameLabel.setText(drinks.strDrink);
                    instructionsLabel.setText("<html><p>" + drinks.strInstructions + "</p></html>");

                    URL url = new URL(drinks.strDrinkThumb);
                    Image image = ImageIO.read(url);
                    pictureLabel.setIcon(new ImageIcon(image));
                } catch (Exception e)
                {
                    nameLabel.setText("Something went wrong. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<CocktailFeed> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }

    public static void main(String[] args)
    {
        new CocktailFrame().setVisible(true);
    }
}
