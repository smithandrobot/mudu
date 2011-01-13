package mudu.api

import grails.converters.JSON

class InteractionRestController extends RestController {

  def interactionService

  def index = {

    render interactionService.listAllInteractions() as JSON
  }

  def create = {
    try{
      interactionService.createInteractionEvent(Integer.parseInt(params.eventId), params.facebookId, params.eventValue)
      render success("Event recorded")
    } catch(Error e) {
      render error(e.message)
    }

  }
}
