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


  }

  void testCreateWithBadToken() {
    prc.params.token = "____"
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    println resp
    assertEquals "fail", resp.stat
  }


  void testDuplicateCreation() {
    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    assertEquals "ok", resp.stat
    prc.create()
    assertEquals 1, Player.countByFacebookToken(prc.params.token)
  }

  void testAllDataSaved() {

    prc.create()
    def resp = JSON.parse(prc.response.contentAsString)
    def createdPlayer = Player.findByFacebookToken(prc.params.token)
    assertEquals resp.stat, "ok"
    assertEquals "male", createdPlayer.gender
    assertEquals "Austin, Texas", createdPlayer.location
    assertEquals "Mud Uni", createdPlayer.name
    assertEquals prc.params.token, createdPlayer.facebookToken
    assertEquals new Date(77, 7, 03), createdPlayer.birthdate
  }
}
