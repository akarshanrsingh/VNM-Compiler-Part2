public class IdMatToken extends Token {

    public String value;

    public IdMatToken(int kind, String image) {
        super(kind, image);
        this.value = image;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
