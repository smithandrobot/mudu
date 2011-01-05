package mudu

class EarnedAchievement {

  Date dateEarned
  static constraints = {
  }

  static belongsTo = [player: Player, achievement: Achievement]
}
