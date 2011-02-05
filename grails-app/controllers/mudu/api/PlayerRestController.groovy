package mudu.api

import mudu.Player
import grails.plugin.springcache.annotations.Cacheable
import grails.plugin.springcache.annotations.CacheFlush

class PlayerRestController extends RestController {

  def playerService
  def facebookService
  def statsService

  @Cacheable("playerCache")
  def index = {
    def player_list = Player.list()
    def players = []

    player_list.each {p -> players.push(playerService.createPlayerResponseObject(p))}
    render success(players)
  }

  @Cacheable("playerStats")
  def view = {
    def p = Player.findByFacebookId(params.id)

    p.stats = statsService.playerStats(p)

    if (!p) {
      render error("Player not found with the Facebook ID: " + params.id)
      return
    } else {
      render success(playerService.createPlayerResponseObject(p))
      return

    }

  }

  @CacheFlush(["playerCache"])
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
