package mudu

class InteractionEvent {

  Date dateEarned = new Date()
  String value

  static constraints = {
  }

  static mapping = {
    interaction cache:true
    player cache:true
  }

  static belongsTo = [player: Player, interaction: Interaction]

  public String toString() {
    return interaction.name
  }

}
