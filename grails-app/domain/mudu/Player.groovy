package mudu

class Player {

  String facebookId
  String facebookToken
  String name
  String gender
  String location
  String email
  Date birthdate
  Date dateCreated = new Date()

  static constraints = {
    name(nullable: true)
    birthdate(nullable:true)
    gender(nullable:true)
    location(nullable:true)
    facebookId(unique:true)
    facebookToken(unique:true)
    email(nullable:true)
  }

  static mapping = {
    interactions cache:true
  }

  static hasMany = [achievements: EarnedAchievement,
                    interactions: InteractionEvent,
                    networks:PlayerNetwork,
                    friends: Player]

  public String toString() {
    return "Facebook id " + facebookId
  }
}
