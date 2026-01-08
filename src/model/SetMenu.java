package model;

import java.io.Serializable;

public class SetMenu implements Serializable {

    private String code;
    private String name;
    private double price;
    private String ingredients;

    public SetMenu() {
    }

    public SetMenu(String code, String name, double price, String ingredients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        String[] items = this.ingredients.split("#");
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append("+ ").append(item.trim()).append("\n");
        }
        return String.format(
                "Code      :%s\n"
                + "Name      :%s\n"
                + "Price     :%,.0f Vnd\n"
                + "Ingredients:\n"
                + "%s"
                + "--------------------------------------------------",
                this.code,
                this.name,
                this.price,
                sb.toString()
        );
    }
}
