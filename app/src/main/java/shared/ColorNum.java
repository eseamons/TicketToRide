package shared;

public enum ColorNum {
    RED, GREEN, BLUE, YELLOW, BLACK;

    public static ColorNum convertInttoEnum(int value) {
        ColorNum returnNum = null;
        for(ColorNum color_enum : ColorNum.values()) {
            if(color_enum.ordinal() == value) {
                returnNum = color_enum;
            }
        }
        return returnNum;
    }
}
