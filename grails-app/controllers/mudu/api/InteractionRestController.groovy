package mudu.api

import grails.converters.JSON
import grails.plugin.springcache.annotations.CacheFlush

class InteractionRestController extends RestController {

  def interactionService

  def index = {

    render interactionService.listAllInteractions() as JSON
  }

  @CacheFlush(["topTenCache"])
  def create = {
    try{
      interactionService.createInteractionEvent(params.interactionId, params.facebookId, params.interactionValue)
      render success("Event recorded")
    } catch(Error e) {
      render error(e.message)
    }

  }
}
