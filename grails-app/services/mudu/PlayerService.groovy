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

    if (facebookToken == null){
      throw new Error("Missing Parameter facebookToken")
    }

    def player = Player.findByFacebookToken(facebookToken)

    if (player != null){
      return player
    }

    def params = facebookService.fetchFacebookData(facebookToken)
    params.token = facebookToken

    params = validateParams(params)

    player = new Player(facebookId: params.id,
              name: params.name,
              email: params.email,
              gender: params.gender,
              location: params.location,
              facebookToken: params.token,
              birthdate: params.birthdate).save(failOnError: true)

    return player
  }

  public storeFacebookNetworks(Player player){

    def networkData = facebookService.fetchNetworks(player)

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
      params.birthdate = null
    } catch (java.lang.NullPointerException e) {
      params.birthdate = null
    }

    if(params.location != null){
      params.location = params.location.name
    }

    return params
  }

}
