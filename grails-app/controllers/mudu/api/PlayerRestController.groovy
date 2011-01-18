package mudu.api

import mudu.Player

class PlayerRestController extends RestController {

  def playerService
  def facebookService

  def index = {
    def player_list = Player.list()
    def players = []

    player_list.each {p -> players.push(playerService.createPlayerResponseObject(p))}

    render success(players)
  }

  def view = {
    def p = Player.findByFacebookId(params.id)

    if (!p) {
      render error("Player not found with the Facebook ID: " + params.id)
      return
    } else {
      render success(playerService.createPlayerResponseObject(p))
      return

    }

  }

  def create = {

    try {
      def p = playerService.getOrCreatePlayer(params.token)
      render success(playerService.createPlayerResponseObject(p))
    } catch (Error e) {
      log.error(e)
      render error(e.message)
    }

  }


}
