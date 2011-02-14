package mudu

class StatsService {

  static transactional = true



  def topTen(Date since = new Date(110, 1, 1)) {

    def records = []
    def players = getRankings(since)
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
          eq("i.name", "Dirty Hairy")
        }


      }

      def groups = weapon.groupBy {it -> it.interaction.name}

      def counts = []
      groups.each { k, v ->
        counts.push([
                name: k,
                uses: v.size(),
                machine_name: k.replace(" ", "_").toLowerCase()
        ])
      }

      counts = counts.sort {a, b -> b['uses'] <=> a['uses']}
      p.favoriteWeapon = counts[0]


    }


    return players
  }


  def getRankings(Date since = new Date(110, 1, 1)) {
    def players = []
    def interactions = InteractionEvent.withCriteria {
      createAlias("interaction", "i")
      and {
        between('dateEarned', since, new Date())
      }
      or {
        eq("i.name", "Shared Mud")
        eq("i.name", "Invite")
        eq("i.name", "Mudded a Friend")
      }
    }.groupBy{it.player}

    interactions.each { p,i ->

      players.push([
         player:p,
         score:i.size(),
         favoriteWeapon: null
      ])

    }

    players = players.sort {a, b -> b['score'] <=> a['score']}

    return players

  }

  def playerRank(Player player, Date since = new Date(110, 1, 1)) {


    def rankings = getRankings(since)
    def counter = 0
    def rank = rankings.size()

    rankings.each { r ->
      counter += 1
      if (r.player.facebookId == player.facebookId) {
        rank = counter
        return
      }

    }

    //println "$player is ranked $rank"
    return rank
  }

  def playerStats(Player player, Date since = new Date(110, 1, 1)) {

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -7);

    def playerInteractions = InteractionEvent.findAllByPlayer(player).groupBy {it.interaction.name}
    def friendsRanks = []

    player.friends.each { f ->
      friendsRanks.push([
              id: f.facebookId,
              rank: playerRank(f)
      ])

    }

    friendsRanks = friendsRanks.sort {a, b -> b['rank'] <=> a['rank']}.reverse()

    def mudded = playerInteractions['Mudded a Friend'] ? playerInteractions['Mudded a Friend'].size() : 0
    def shared = playerInteractions['Shared Mud'] ? playerInteractions['Shared Mud'].size() : 0
    def vites = playerInteractions['Invite'] ? playerInteractions['Invite'].size() : 0
    def rank = [
            rank: playerRank(player),
            photosMudded: mudded,
            mudvites: vites,
            photosShared: shared,
            mudprops: mudded+shared+vites,
            friendsData: friendsRanks.size() > 2 ? friendsRanks[0..2] : friendsRanks,
            weekRank: playerRank(player, cal.getTime())
    ]

    return rank
  }

    def weaponStats(Date since = new Date(110, 1, 1)){
        def weapon = InteractionEvent.withCriteria {
        createAlias("interaction", "i")
        and {
          between('dateEarned', since, new Date())
        }
        or {
          eq("i.name", "Sasplotch")
          eq("i.name", "Buck Wild")
          eq("i.name", "Dr Walnuts")
          eq("i.name", "Dirty Hairy")
        }


      }

      def groups = weapon.groupBy {it -> it.interaction.name}

      def counts = []
      groups.each { k, v ->
        counts.push([
                name: k,
                uses: v.size(),
                machine_name: k.replace(" ", "_").toLowerCase()
        ])
      }

      counts = counts.sort {a, b -> b['uses'] <=> a['uses']}
      def weapons = [total:0]
        counts.each { c ->
            weapons[(c.machine_name)] = c.uses
            weapons.total += c.uses
        }

      return weapons
    }
}
