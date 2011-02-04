package mudu

import java.text.DateFormat
import java.text.SimpleDateFormat

class PlayerService {

  static transactional = true
  def facebookService

  def createPlayerResponseObject(Player p) {
    def achievements = []
    p.achievements.each {a ->
      def achievement = [
              dateEarned: a.dateEarned,
              id: a.achievement.id
      ]
      achievements.push(achievement)
    }
    def player = [
            facebookId: p.facebookId,
            name: p.name,
            achievements: achievements
    ]

    return player
  }

  public Player getOrCreatePlayer(String facebookToken) {

    if (facebookToken == null) {
      throw new Error("Missing Parameter facebookToken")
    }

    def player = Player.findByFacebookToken(facebookToken)

    if (player != null) {

      // persist any updated data
      refreshFacebookFriends(player)
      storeFacebookNetworks(player)

      return player
    }

    def params = facebookService.fetchFacebookData(facebookToken)
    params.token = facebookToken

    params = validateParams(params)

    def existingPlayer = Player.findByFacebookId(params.id)

    if(existingPlayer != null){
      existingPlayer.facebookToken = facebookToken
      existingPlayer.location = params.location
      existingPlayer.email = params.email
      existingPlayer.save()
      refreshFacebookFriends(existingPlayer)
      storeFacebookNetworks(existingPlayer)
      return existingPlayer
    }

    def newPlayer =  new Player(facebookId: params.id,
            name: params.name,
            email: params.email,
            gender: params.gender,
            location: params.location,
            facebookToken: params.token,
            birthdate: params.birthdate).save(failOnError: true)

    refreshFacebookFriends(newPlayer)
    storeFacebookNetworks(newPlayer)

    return newPlayer
  }

  public storeFacebookNetworks(Player player) {

    def networkData = facebookService.fetchNetworks(player)

    networkData.affiliations[0].each { a ->
      def network = Network.findByName(a.name) ?:
        new Network(name: a.name).save()

      def pn = PlayerNetwork.findByPlayerAndNetwork(player, network) ?:
        new PlayerNetwork(player: player, network:network).save()

    }

  }

  public refreshFacebookFriends(Player player) {

    def friendData = facebookService.fetchFriendsUsingApp(player.facebookToken)


    friendData.each { friendId ->

      def friend = Player.findByFacebookId(friendId)

      if (friend != null) {
        player.addToFriends(friend)
      }

    }

    player.save()

  }


  public Date parseDate(String dateString) {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    return df.parse(dateString);
  }

  public Object validateParams(Object params) {

    if (params.id == null) {
      throw new Error("Missing parameter: facebookId")
    } else if (params.token == null) {
      throw new Error("Missing parameter: token")
    }

    try {
      params.birthdate = parseDate(params.birthday)
    } catch (java.text.ParseException e) {
      log.error("Unable to parse birthdate for $params.id: $e.message")
      params.birthdate = null
    } catch (java.lang.NullPointerException e) {
      log.error("Unable to parse birthdate for $params.id: $e.message")
      params.birthdate = null
    }

    try {
      params.location = params.location.name
    } catch (e) {
      log.error("Unable to store location for $params.id: $e.message")
      params.location = null
    }

    // handle JSONObjects weird Null constant
    if (params.location.toString() == 'null') {
      params.location = null
    }


    return params
  }

}
