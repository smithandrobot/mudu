package mudu

class StatsService {

  static transactional = true

  def serviceMethod() {

  }

  def playerStats(Player player) {

  }

  def topTen(Date since = new Date(110, 1, 1)) {

    def interactions = InteractionEvent.withCriteria {
      and {
        between('dateEarned', since, new Date())
      }
    }

    return interactions
  }
}
