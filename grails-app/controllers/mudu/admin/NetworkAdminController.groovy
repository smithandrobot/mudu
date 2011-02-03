package mudu.admin

import mudu.Network

class NetworkAdminController {
  def scaffold = Network
  def index = {
    redirect(action: "list")
  }
}
