package mudu.api
import grails.converters.JSON

class AchievementRestControllerTests extends GroovyTestCase {
  def achievementService
  protected void setUp() {
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


}
