package mudu

class Network {

  static hasMany = PlayerNetwork
  String name

  static constraints = {
  }

  public String toString() {
    return name
  }
}
