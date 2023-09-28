package dev.jorgemelendez.booksgraphql.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {

  FIVE_STARS("⭐️⭐️⭐️⭐️⭐️"),
  FOUR_STARS("⭐️⭐️⭐️⭐️"),
  THREE_STARS("⭐️⭐️⭐️"),
  TWO_STARS("⭐️⭐️"),
  ONE_STAR("⭐️");

  private String star;

  Rating(String star) {
    this.star = star;
  }

  @JsonValue
  public String getStar(){
    return star;
  }

  public static Rating rating(int stars) {
    return  switch (stars) {
      case 1-> ONE_STAR;
      case 2-> TWO_STARS;
      case 3-> THREE_STARS;
      case 4-> FOUR_STARS;
      case 5-> FIVE_STARS;
      default -> throw new IllegalStateException("Cannot convert value to stars: " + stars);
    };
  }
}
