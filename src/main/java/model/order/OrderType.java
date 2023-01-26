package model.order;

public enum OrderType {
    MARKET,
    LIMIT,
    STOP,
    STOP_LIMIT,
    LIMIT_IF_TOUCHED;

    @Override
    public String toString() {
        StringBuilder name = new StringBuilder(this.name().toLowerCase());
        name.setCharAt(0, Character.toUpperCase(name.charAt(0)));
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '_') {
                name.setCharAt(i, ' ');
            }
        }
        return name.toString();
    }
}
