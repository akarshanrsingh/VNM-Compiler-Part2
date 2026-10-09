public class NumberToken extends Token {

    public Integer value;

    public NumberToken(int kind, String image) {

        super(kind, image);
        this.value = Integer.valueOf(image);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
