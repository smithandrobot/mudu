package mudu.api

import grails.converters.JSON

class AchievementRestController extends RestController {

  def achievementService

  def index = {
    render achievementService.listAllAchievements() as JSON
  }

  def create = {

    try {
      def awarded = achievementService.awardAchievement(params.facebookId, params.achievementId)
      render success("Achievement \"" + awarded.achievement.name + "\" saved.")
    } catch (Error e) {
      render error(e.message)
    }

  }
}
