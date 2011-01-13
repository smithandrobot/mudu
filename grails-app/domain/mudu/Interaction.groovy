package mudu

class Interaction {

  static hasMany = InteractionEvent
  String name
  String description
  Integer score = 0


  static constraints = {
  }

  public String toString() {
    return name
  }
}
