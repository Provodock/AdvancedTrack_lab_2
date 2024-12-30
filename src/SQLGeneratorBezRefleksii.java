import java.util.Map;

public class SQLGeneratorBezRefleksii {
  public static String stvorytyTablycyu(String nazvaTablyci, Map<String, String> stovpci) {
    String vyznachennyaStovpciv = stovpci.entrySet().stream()
            .map(entry -> entry.getKey() + " " + entry.getValue())
            .reduce((s1, s2) -> s1 + ", " + s2)
            .orElseThrow(() -> new IllegalArgumentException("Мапа стовпців не може бути порожньою"));
    return String.format("CREATE TABLE %s (%s);", nazvaTablyci, vyznachennyaStovpciv);
  }

  public static String vstavyty(String nazvaTablyci, Map<String, Object> znachennya) {
    String stovpci = String.join(", ", znachennya.keySet());
    String znachPlaceHolder = znachennya.values().stream()
            .map(value -> value instanceof String ? "'" + value + "'" : String.valueOf(value))
            .reduce((v1, v2) -> v1 + ", " + v2)
            .orElse("");
    return String.format("INSERT INTO %s (%s) VALUES (%s);", nazvaTablyci, stovpci, znachPlaceHolder);
  }

  public static String vybraty(String nazvaTablyci, String... stovpci) {
    String spysokStovpciv = String.join(", ", stovpci);
    return String.format("SELECT %s FROM %s;", spysokStovpciv, nazvaTablyci);
  }

  public static String onovyty(String nazvaTablyci, Map<String, Object> znachennya, String umova) {
    String vstanovyty = znachennya.entrySet().stream()
            .map(entry -> {
              String value = entry.getValue() instanceof String ?
                      "'" + entry.getValue() + "'" : String.valueOf(entry.getValue());
              return entry.getKey() + " = " + value;
            })
            .reduce((s1, s2) -> s1 + ", " + s2)
            .orElse("");
    return String.format("UPDATE %s SET %s WHERE %s;", nazvaTablyci, vstanovyty, umova);
  }

  public static String vydalyty(String nazvaTablyci, String umova) {
    return String.format("DELETE FROM %s WHERE %s;", nazvaTablyci, umova);
  }
}