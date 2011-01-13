package mudu

class EarnedAchievement {

  Date dateEarned = new Date()
  static constraints = {
  }

  static belongsTo = [player: Player, achievement: Achievement]
}
