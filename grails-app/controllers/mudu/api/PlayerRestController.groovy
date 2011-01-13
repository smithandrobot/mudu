package mudu.api

import grails.converters.JSON
import mudu.Player

class PlayerRestController extends RestController {

  def playerService

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
    def p = playerService.getOrCreatePlayer(params)
    render success(playerService.createPlayerResponseObject(p))
  }


}
