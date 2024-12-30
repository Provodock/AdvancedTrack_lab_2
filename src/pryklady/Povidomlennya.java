package pryklady;

import anotacii.*;

@Tablycya(nazva = "povidomlennya")
public class Povidomlennya {
  @Klyuch
  @Stovpec(nazva = "id")
  private int id;

  @Stovpec(nazva = "zmist")
  private String zmist;

  @Stovpec(nazva = "vidpravnyk")
  private String vidpravnyk;

  @Stovpec(nazva = "otrymuvach")
  private String otrymuvach;

  // Конструктор і методи доступу
}
