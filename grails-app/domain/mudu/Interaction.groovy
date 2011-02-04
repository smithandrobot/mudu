package mudu

class Interaction {

  static hasMany = InteractionEvent
  String name
  String description
  Integer score = 1


  static constraints = {
  }

  public String toString() {
    return name
  }
}
