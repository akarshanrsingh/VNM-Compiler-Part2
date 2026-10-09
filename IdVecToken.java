public class IdVecToken extends Token {

    public String value;

    public IdVecToken(int kind, String image) {
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
