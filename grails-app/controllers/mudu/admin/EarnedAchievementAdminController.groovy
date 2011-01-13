package mudu.admin

import mudu.EarnedAchievement

class EarnedAchievementAdminController {

  def scaffold = EarnedAchievement
  def index = {
    redirect(action: "list")
  }
}
