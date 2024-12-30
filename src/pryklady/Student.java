package pryklady;

import anotacii.*;

@Tablycya(nazva = "studenty")
public class Student {
  @Klyuch
  @Stovpec(nazva = "id")
  private int id;

  @Stovpec(nazva = "imya")
  private String imya;

  @Stovpec(nazva = "poshta")
  private String poshta;

  @Stovpec(nazva = "vik")
  private int vik;

  // Конструктор і методи доступу
}