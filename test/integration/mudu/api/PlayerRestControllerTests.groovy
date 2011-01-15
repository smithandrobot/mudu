package mudu.api

import grails.converters.JSON
import mudu.Player

class PlayerRestControllerTests extends GroovyTestCase {

  def playerService
  def prc = new PlayerRestController()

  protected void setUp() {
    prc.playerService = playerService
    prc.params.token = "131296520253246|ae1d4f5df91267a5b300f34b-100000241798640|rr1QmKnUcRZ4RnDMBzVI0xnRfbk"
    //prc.params.id = "12345"
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testCreateWithToken() {
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    assertEquals "ok", resp.stat
    assertEquals "100000241798640", resp.data.facebookId
    assertEquals "Mud Uni", resp.data.name

    def player = Player.findByFacebookId(resp.data.facebookId)
    assertEquals prc.params.token, player.facebookToken
    assertEquals new Date(77, 7, 03), player.birthdate
  }



  void voidtestDuplicateCreation() {
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    assertEquals resp.stat, "ok"
    prc.create()
    assertEquals 1, Player.countByFacebookId(prc.params.id)
  }

  void voidtestGenderAndLocation() {
    prc.params.location = "Austin, TX"
    prc.params.gender = "male"
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    assertEquals resp.stat, "ok"
    assertEquals "male", Player.findByFacebookId(prc.params.id).gender
    assertEquals "Austin, TX", Player.findByFacebookId(prc.params.id).location
  }
}
