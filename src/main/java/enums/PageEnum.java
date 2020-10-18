package enums;

import java.util.Arrays;

public enum PageEnum {

    MAIN("MainPage"),
    LOGIN("LoginPage"),
    RESULT("ResultPage"),
    CART("CartPage");

    private String name;

    PageEnum(String name) {
        this.name = name;
    }

    public String getPage() {
        return name;
    }

    public static PageEnum getPageWithName(final String pageName) {
        return Arrays.stream(PageEnum.values()).filter(e -> e.getPage().equals(pageName)).findFirst()
                .orElse(null);
    }

}
