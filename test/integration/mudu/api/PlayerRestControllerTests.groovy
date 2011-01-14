package mudu.api

import grails.converters.JSON
import mudu.Player

class PlayerRestControllerTests extends GroovyTestCase {

  def playerService
  def prc = new PlayerRestController()

  protected void setUp() {
    prc.playerService = playerService
    prc.params.token = "-----_____"
    prc.params.id = "12345"
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testMissingId() {
    prc.params.id = null
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    assertEquals resp.stat, "fail"
  }

  void testMissingToken() {
    prc.params.token = null
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    assertEquals resp.stat, "fail"
  }

  void testBadDate() {
    prc.params.birthdate = "fdq23d3/r12/dq"
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    assertEquals resp.stat, "ok"
    assertEquals null, Player.findByFacebookId(prc.params.id).birthdate
  }

  void testGoodDate() {
    prc.params.birthdate = "07/28/1976"
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    assertEquals resp.stat, "ok"
    assertEquals new Date(76, 6, 28), Player.findByFacebookId(prc.params.id).birthdate
  }
}
