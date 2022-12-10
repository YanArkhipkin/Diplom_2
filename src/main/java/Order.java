import java.util.List;

public class Order {
    private List<String> ingredients;

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public static Order getCorrectOrder() {
        return new Order(List.of("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f"));
    }

    public static Order getOrderWithWrongHash() {
        return new Order(List.of("32,.1433154gfg","6fgdfdg22&1.,&?"));
    }

    public static Order getEmptyOrder() {
        return new Order(List.of());
    }

}

