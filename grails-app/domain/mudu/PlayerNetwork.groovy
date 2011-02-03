package mudu

class PlayerNetwork {

  static constraints = {
  }

  static belongsTo = [player: Player, network: Network]

  public String toString() {
    return network.name
  }
}
