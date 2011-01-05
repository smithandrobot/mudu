package mudu

class Player {

  String facebookId
  String name

  static constraints = {
    name(nullable: true)
  }

  static hasMany = [achievements: EarnedAchievement]

  public Boolean awardAchievement(Achievement achievement) {
    def existing = EarnedAchievement.findByPlayerAndAchievement(this, achievement)
    if (existing != null) {
      throw new Error("Achievement \"" + achievement.name + "\" already exists for " + this)
    } else {
      def ea = new EarnedAchievement()
      ea.achievement = achievement
      ea.player = this
      ea.dateEarned = new Date()
      ea.save()
      return true
    }
  }

  public String toString() {
    return "Facebook id " + facebookId
  }
}
