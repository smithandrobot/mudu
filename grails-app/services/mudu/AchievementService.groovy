package mudu

class AchievementService {

  static transactional = true

  def listAllAchievements() {

    def all = Achievement.list()

    //sanitize
    def achievements = []

    all.each { a ->

      achievements.push([
              id: a.id,
              name: a.name,
              description: a.description,
              timesAwarded: EarnedAchievement.countByAchievement(a)
      ])

    }

    return achievements
  }

  public EarnedAchievement awardAchievement(String playerFacebookId, String achievementId) {

    if (playerFacebookId == null || achievementId == null) {
      throw new Error("Params missing: facebook id: $playerFacebookId achievement id: $achievementId.")
    }

    def player = Player.findByFacebookId(playerFacebookId)
    def achievement = Achievement.get(Integer.parseInt(achievementId))

    if (player == null) {
      throw new Error("Player with the facebook id ${playerFacebookId} does not exist.")
    }

    if (achievement == null) {
      throw new Error("Achievement $achievementId not found.")
    }

    def existing = EarnedAchievement.findByPlayerAndAchievement(player, achievement)
    if (existing != null) {
      throw new Error("Achievement $achievement.name already exists for $player ")
    } else {
      def ea = new EarnedAchievement()
      ea.achievement = achievement
      ea.player = player
      ea.dateEarned = new Date()
      ea.save()
      return ea
    }
  }
}
