import anotacii.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SQLGeneratorZRefleksieyu {
  public static String generuvatyCreateZapyt(Class<?> klas) {
    Tablycya tablycya = otrymaty_anotaciyu_tablyci(klas);
    String nazvaTablyci = tablycya.nazva();
    String stovpci = Arrays.stream(klas.getDeclaredFields())
            .filter(field -> field.isAnnotationPresent(Stovpec.class))
            .map(field -> {
              Stovpec stovpec = field.getAnnotation(Stovpec.class);
              return stovpec.nazva() + " " + otrymaty_typ_SQL(field.getType());
            })
            .collect(Collectors.joining(", "));
    return String.format("CREATE TABLE %s (%s);", nazvaTablyci, stovpci);
  }

  public static String generuvatyInsertZapyt(Object obj) {
    Class<?> klas = obj.getClass();
    Tablycya tablycya = otrymaty_anotaciyu_tablyci(klas);
    String nazvaTablyci = tablycya.nazva();

    Field[] polya = klas.getDeclaredFields();
    String stovpci = Arrays.stream(polya)
            .filter(field -> field.isAnnotationPresent(Stovpec.class))
            .map(field -> field.getAnnotation(Stovpec.class).nazva())
            .collect(Collectors.joining(", "));

    String znachennya = Arrays.stream(polya)
            .filter(field -> field.isAnnotationPresent(Stovpec.class))
            .map(field -> {
              try {
                field.setAccessible(true);
                Object value = field.get(obj);
                return value instanceof String ? "'" + value + "'" : String.valueOf(value);
              } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
              }
            })
            .collect(Collectors.joining(", "));

    return String.format("INSERT INTO %s (%s) VALUES (%s);", nazvaTablyci, stovpci, znachennya);
  }

  private static String otrymaty_typ_SQL(Class<?> typPolya) {
    if (typPolya == String.class) return "VARCHAR(255)";
    if (typPolya == int.class || typPolya == Integer.class) return "INT";
    if (typPolya == double.class || typPolya == Double.class) return "DOUBLE";
    return "TEXT";
  }

  private static Tablycya otrymaty_anotaciyu_tablyci(Class<?> klas) {
    if (!klas.isAnnotationPresent(Tablycya.class)) {
      throw new IllegalArgumentException("Клас повинен мати анотацію @Tablycya");
    }
    return klas.getAnnotation(Tablycya.class);
  }

  // Додаткові методи для SELECT, UPDATE, DELETE...
}