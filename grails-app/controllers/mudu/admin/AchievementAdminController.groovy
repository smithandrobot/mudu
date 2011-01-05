package mudu.admin

import mudu.Achievement

class AchievementAdminController {

  def scaffold = Achievement
  def index = {
    redirect(action: "list")
  }
}
