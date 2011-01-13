package mudu.admin

import mudu.InteractionEvent

class InteractionEventAdminController {

  def scaffold = InteractionEvent
  def index = {
    redirect(action: "list")
  }
}
