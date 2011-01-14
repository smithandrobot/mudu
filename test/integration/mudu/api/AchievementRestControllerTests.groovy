package mudu.api
import grails.converters.JSON
import mudu.Achievement
import mudu.EarnedAchievement
import mudu.Player

class AchievementRestControllerTests extends GroovyTestCase {

  def achievementService
  def interactionService
  def fid = "00000"
  def irc = new InteractionRestController()

  protected void setUp() {
    irc.interactionService = interactionService
    irc.params.facebookId = fid
    irc.params.interactionId = "1"
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testListAchievements() {
    def arc = new AchievementRestController()
    arc.achievementService = achievementService
    arc.index()
    def resp = JSON.parse(arc.response.contentAsString)
    assertEquals resp[0].name, "Invite"
  }

  void testInviteAchievementAwarded() {
    irc.params.interactionId = "1"
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "ok"
    def a = EarnedAchievement.findByPlayer(Player.findByFacebookId(fid))
    assertNotSame null, a
    assertEquals "Invite", a.achievement.name
  }


}
