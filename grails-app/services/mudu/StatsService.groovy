package mudu

class StatsService {

  static transactional = true

  def serviceMethod() {

  }

  def playerStats(Player player) {

  }

  def topTen(Date since = new Date(110, 1, 1)) {

    def records = []
    def players = []

    def playerList = Player.getAll()

    playerList.each { p ->

      def score = InteractionEvent.withCriteria {

        createAlias("interaction", "i")
        and {
          between('dateEarned', since, new Date())
          eq("player", p)
        }
        projections {
          sum "i.score", "score"
        }
      }

      players.push([
          player:p,
          score:score[0] ?: 0,
          favoriteWeapon:null
      ])

    }

    players = players.sort{a,b -> b['score'] <=> a['score']}
    players = players.size() > 14 ? players[0..14] : players

    players.each { p ->

      def weapon = InteractionEvent.withCriteria {
        createAlias("interaction", "i")
        and {
          between('dateEarned', since, new Date())
          eq("player", p.player)
        }
        or {
          eq("i.name", "Sasplotch")
          eq("i.name", "Buck Wild")
          eq("i.name", "Dr Walnuts")
          eq("i.name", "Dirty Harry")
        }


      }

      def groups = weapon.groupBy {it -> it.interaction.name}

      def counts = []
      groups.each { k, v ->
          counts.push([
              name:k,
              uses:v.size()
          ])
      }

      counts = counts.sort{a,b -> b['uses'] <=> a['uses']}
      p.favoriteWeapon = counts[0]


    }

    //println players
    return players
  }
}
