package mudu.api

import mudu.Achievement
import mudu.EarnedAchievement
import mudu.Player
import grails.converters.JSON



class AchievementRestController extends RestController {

  def index = {
    def all = Achievement.list()

    //sanitize
    def achievements = []

    all.each { a ->

      achievements.push([
          id:a.id,
          name:a.name,
          description:a.description,
          timesAwarded:EarnedAchievement.findByAchievement(a).count()
      ])

    }
    render achievements as JSON
  }

  def create = {
    if (params.facebookId != null && params.achievementId != null) {

      def player = Player.findByFacebookId(params.facebookId)
      def achievement = Achievement.get(params.achievementId)

      if (player == null) {
        player = new Player(facebookId: params.facebookId)
        player.save()
      }

      if (achievement == null) {
        render error("Achievement not found.")
        return
      }

      try {
        def awarded = player.awardAchievement(achievement)
      } catch (Error e) {
        render error(e.message)
        return
      }

      render success("Achievement \"" + achievement.name + "\" saved.")
      return

    } else {
      render error("Parameters missing")
      return
    }

  }
}
