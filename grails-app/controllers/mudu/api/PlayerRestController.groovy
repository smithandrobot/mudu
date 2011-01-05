package mudu.api

import grails.converters.JSON
import mudu.Player

class PlayerRestController extends RestController {

  def index = {
    def player_list = Player.list()
    def players = []

    player_list.each {p -> players.push(createPlayerResponseObject(p))}

    render success(players)
  }

  def view = {
    def p = Player.findByFacebookId(params.id)

    if (!p) {
      render error("Player not found with the Facebook ID: " + params.id)
      return
    } else {
      render success(createPlayerResponseObject(p))
      return

    }

  }

  def createPlayerResponseObject(Player p) {
    def achievements = []
    p.achievements.each {a ->
      def achievement = [
              dateEarned: a.dateEarned,
              id: a.achievement.id
      ]
      achievements.push(achievement)
    }
    def player = [
            facebookId: p.facebookId,
            achievements: achievements
    ]

    return player
  }
}
