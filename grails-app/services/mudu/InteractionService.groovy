package mudu

class InteractionService {

  static transactional = true

  def createInteractionEvent(String interactionId, String playerFacebookId, String value) {

    if (interactionId == null) {
      throw new Error("Params missing: interactionId")
    } else if(playerFacebookId == null){
      throw new Error("Params missing: facebookId")
    }

    def player = Player.findByFacebookId(playerFacebookId)

    if (player == null) {
      throw new Error("Player with facebook Id $playerFacebookId does not exist")
    }

    try {
      interactionId = Integer.parseInt(interactionId)
    } catch(NumberFormatException e){
      throw new Error("invalid interactionId: " + e.message)
    }

    def interaction = Interaction.get(interactionId)
    if (interaction == null) {
      throw new Error("Interaction with event Id $interactionId does not exist")
    }

    def interactions = []

    value = value ?: ""
    def values = value.split(",")
    values.each { v ->
      interactions.push(new InteractionEvent(player: player, interaction: interaction, value: v).save())
    }

    return interactions

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
              score: i.score,
              timesTriggered: InteractionEvent.countByInteraction(i)
      ])

    }

    return interactions
  }
}
