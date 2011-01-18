package mudu



class PlayerTests extends GroovyTestCase {

  Player p1
  Player p2

  protected void setUp() {
    p1 = new Player(facebookId: "562363085", name: "No name", facebookToken: "444r4r4rr4r4").save()
    p2 = new Player(facebookId: "500042807", name: "No name", facebookToken: "444r4r4rr4ffr4").save()
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testFriendship() {


    assertNotNull p1
    assertNotNull p2

    p1.addToFriends(p2)
    p1.save()

    assertEquals 1, p1.friends.size()

  }
}
