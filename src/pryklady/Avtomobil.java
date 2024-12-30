package pryklady;

import anotacii.*;

@Tablycya(nazva = "avtomobili")
public class Avtomobil {
  @Klyuch
  @Stovpec(nazva = "id")
  private int id;

  @Stovpec(nazva = "marka")
  private String marka;

  @Stovpec(nazva = "cina")
  private double cina;

  public Avtomobil(int id, String marka, double cina) {
    this.id = id;
    this.marka = marka;
    this.cina = cina;
  }

  // Гетери і сетери
}