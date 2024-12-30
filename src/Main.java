import pryklady.*;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    Avtomobil avto = new Avtomobil(1, "Toyota", 25000.00);

    // Тестування з рефлексією
    System.out.println(SQLGeneratorZRefleksieyu.generuvatyCreateZapyt(Avtomobil.class));
    System.out.println(SQLGeneratorZRefleksieyu.generuvatyInsertZapyt(avto));

    // Тестування без рефлексії
    System.out.println(SQLGeneratorBezRefleksii.stvorytyTablycyu(
            "avtomobili",
            Map.of(
                    "id", "INT",
                    "marka", "VARCHAR(255)",
                    "cina", "DOUBLE"
            )
    ));

    System.out.println(SQLGeneratorBezRefleksii.vstavyty(
            "avtomobili",
            Map.of(
                    "id", 1,
                    "marka", "Toyota",
                    "cina", 25000.00
            )
    ));
  }
}