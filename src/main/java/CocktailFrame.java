import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CocktailFrame extends JFrame
{
    private CocktailService service;
    private CocktailController controller;

    private final JLabel pictureLabel;
    private final JPanel inputPanel;
    private final JPanel descriptionPanel;
    private final JLabel titleLabel;
    private final JTextField inputField;
    private final JButton searchButton;
    private final JButton clearButton;
    private final JLabel nameLabel;
    private final JLabel instructionsLabel;
    private final JLabel ingredientsLabel;

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
        instructionsLabel = new JLabel("", SwingConstants.CENTER);
        ingredientsLabel = new JLabel("", SwingConstants.CENTER);
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
        descriptionPanel.setLayout(new GridLayout(3,1));
        descriptionPanel.add(nameLabel);
        descriptionPanel.add(ingredientsLabel);
        descriptionPanel.add(instructionsLabel);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2));
        centerPanel.add(pictureLabel);
        centerPanel.add(descriptionPanel);

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);



        service = new CocktailServiceFactory().getInstance();

        controller = new CocktailController(service, nameLabel, ingredientsLabel, instructionsLabel, pictureLabel);
    }

    private void clear()
    {
        inputField.setText("");
        nameLabel.setText("");
        ingredientsLabel.setText("");
        instructionsLabel.setText("");
        pictureLabel.setIcon(new ImageIcon());
    }

    private void searchCocktail()
    {
        controller.getCocktails(inputField.getText());
    }

    public static void main(String[] args)
    {
        new CocktailFrame().setVisible(true);
    }
}
