package mudu

class InteractionService {

  static transactional = true

  def createInteractionEvent(Integer eventId, String playerFacebookId, String value) {

    def player = Player.findByFacebookId(playerFacebookId)
    if (player == null) {
      throw new Error("Player with facebook Id $playerFacebookId does not exist")
    }

    def interaction = Interaction.get(eventId)
    if (interaction == null) {
      throw new Error("Interaction with event Id $eventId does not exist")
    }

    return new InteractionEvent(player:player, interaction:interaction, value:value).save()

  }

  def listAllInteractions() {

    def all = Interaction.list()

    //sanitize
    def interactions = []

    all.each { i ->

      interactions.push([
              id: i.id,
              name: i.name,
              description: i.description,
              score:i.score,
              timesTriggered: InteractionEvent.countByInteraction(i)
      ])

    }

    return interactions
  }
}
