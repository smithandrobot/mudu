package mudu.api

import grails.converters.JSON
import mudu.Player

class PlayerRestControllerTests extends GroovyTestCase {

  def playerService
  def prc = new PlayerRestController()

  protected void setUp() {
    prc.playerService = playerService
    prc.params.token = "131296520253246|86b72838825d11a5b55a4278-500041832|jhsy_zm2Gggp2dbmGX_Hrv0aAE4"
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
    assertEquals "500041832", resp.data.facebookId
    assertEquals "Tommy Klumker", resp.data.name


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
    assertEquals "Tommy Klumker", createdPlayer.name
    assertEquals "tommy.klumker@gmail.com", createdPlayer.email
    assertEquals prc.params.token, createdPlayer.facebookToken
    assertEquals null, createdPlayer.birthdate
  }
}
