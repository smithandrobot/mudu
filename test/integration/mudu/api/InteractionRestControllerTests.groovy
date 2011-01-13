package mudu.api

import grails.converters.JSON
import mudu.InteractionEvent
import mudu.Player

class InteractionRestControllerTests extends GroovyTestCase {

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

  void testSingleValue() {
    irc.params.interactionValue = "012"
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "ok"
    assertEquals InteractionEvent.countByPlayer(Player.findByFacebookId(fid)), 1
  }

  void testSplitValue() {
    irc.params.interactionValue = "123,456,789"
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "ok"
    assertEquals InteractionEvent.countByPlayer(Player.findByFacebookId(fid)), 3
  }

  void testEmptyValue() {
    irc.params.interactionValue = null
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "ok"
    assertEquals InteractionEvent.countByPlayer(Player.findByFacebookId(fid)), 1
  }

  void testEmptyFacebookId() {
    irc.params.facebookId = null
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "fail"
  }

  void testEmptyInteractionId() {
    irc.params.interactionId = null
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "fail"
  }

  void testNonExistentFacebookId() {
    irc.params.facebookId = "14425234234"
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "fail"
  }

  void testInvalidInteractionIdInteger() {
    irc.params.interactionId = "14425234234"
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "fail"
  }

  void testNonExistentInteractionId() {
    irc.params.interactionId = "1442"
    irc.create()
    def resp = JSON.parse(irc.response.contentAsString)
    assertEquals resp.stat, "fail"
  }
}
