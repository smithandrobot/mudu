package mudu

import grails.test.GrailsUnitTestCase

class PlayerServiceTests extends GrailsUnitTestCase {

  def params
  def playerService

  protected void setUp() {
    params = [
            id: "12345",
            token: "-----_____|||||",
            birthdate: "7/28/1976"
    ]

    playerService = new PlayerService()
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testMissingId() {
    params.id = null
    try {
      def cleaned = playerService.validateParams(params)
    } catch (Error e) {
      assert true
    }
  }

  void testMissingToken() {
    params.token = null
    try {
      def cleaned = playerService.validateParams(params)
    } catch (Error e) {
      assert true
    }
  }

  void testBadDate() {
    params.birthdate = "fdq23d3/r12/dq"
    try {
      def cleaned = playerService.validateParams(params)
    } catch (Error e) {
      assert true
    }
  }

  void testGoodData() {
    try {
      def cleaned = playerService.validateParams(params)
    } catch (Error e) {
      assert false
    }
  }


}
