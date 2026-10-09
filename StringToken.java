public class StringToken extends Token {

    public String value;

    public StringToken(int kind, String image) {
        super(kind, image);
        this.value = unescapeString(image.substring(1, image.length()-1));
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    private String unescapeString(String str) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '\\' && i + 1 < str.length()) {
                i++;
                char next = str.charAt(i);
                switch (next) {
                    case 'n':
                        sb.append('\n');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case '\"':
                        sb.append('\"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    default:
                        sb.append(next);
                        break;
                }
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
