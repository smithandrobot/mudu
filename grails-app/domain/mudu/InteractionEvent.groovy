package mudu

class InteractionEvent {

    Date dateEarned = new Date()
    String value

    static constraints = {
    }

    static belongsTo = [player: Player, interaction: Interaction]

}
