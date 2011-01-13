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
            facebookId    : p.facebookId,
            name          : p.name,
            achievements  : achievements
    ]

    return player
  }

  public Player getOrCreatePlayer(params) {
    def player = Player.findByFacebookId(params.id) ?:
      new Player(facebookId: params.id,
              name: params.name,
              gender: params.gender,
              location: params.location,
              facebookToken: params.token,
              birthdate: this.parseDate(params.birthdate)).save(failOnError: true)
    return player
  }

  public Date parseDate(String dateString) {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    return df.parse(dateString);
  }

}
