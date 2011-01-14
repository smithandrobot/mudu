package mudu

class Player {

  String facebookId
  String facebookToken
  String name
  String gender
  Date birthdate

  static constraints = {
    name(nullable: true)
    birthdate(nullable:true)
    gender(nullable:true)
    facebookId(unique:true)
    facebookToken(unique:true)
  }

  static hasMany = [achievements: EarnedAchievement, interactions: InteractionEvent]

  public String toString() {
    return "Facebook id " + facebookId
  }
}
