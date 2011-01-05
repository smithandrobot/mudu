package mudu

class Achievement {

  static hasMany = EarnedAchievement
  String name
  String Description
  Integer score

  static constraints = {
  }

  public String toString() {
    return name
  }
}
