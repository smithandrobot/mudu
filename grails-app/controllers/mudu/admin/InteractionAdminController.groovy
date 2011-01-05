package mudu.admin

import mudu.Interaction

class InteractionAdminController {

    def scaffold = Interaction
    def index = {
      redirect(action: "list")
    }
}
