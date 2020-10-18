package pages;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public abstract class BasePage {

    @SneakyThrows
    public String getVariable(String fieldName) {
        Class aClass = getClass();
        Field[] fields = aClass.getFields();
        String variable = null;

        for (Field field : fields) {
            if (field.getName().contains(fieldName))
                variable = field.get(fieldName).toString();
        }
        return variable;
    }
}
