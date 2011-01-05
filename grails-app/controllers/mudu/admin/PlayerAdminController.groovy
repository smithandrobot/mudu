package mudu.admin

import mudu.Player

class PlayerAdminController {
  def scaffold = Player

  def index = {
    redirect(action: "list")
  }


}