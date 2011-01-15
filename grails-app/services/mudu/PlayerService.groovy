package mudu


import java.text.DateFormat
import java.text.SimpleDateFormat


class PlayerService {

  static transactional = true

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

  public Player getOrCreatePlayer(params) {

    params = validateParams(params)

    def player = Player.findByFacebookId(params.id) ?:
      new Player(facebookId: params.id,
              name: params.name,
              gender: params.gender,
              location: params.location.name,
              facebookToken: params.token,
              birthdate: params.birthdate).save(failOnError: true)

    return player
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

    return params
  }

}
