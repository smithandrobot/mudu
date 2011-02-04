import mudu.Achievement
import mudu.Interaction
import mudu.Player

class BootStrap {


  def init = { servletContext ->

    def testPlayer = Player.findByFacebookId("00000") ?: new Player(facebookId: "00000", facebookToken: "---___").save()
    def testPlayer2 = Player.findByFacebookId("500041832") ?: new Player(facebookId: "500041832", facebookToken: "---___---").save()
    def initialAchievement = Achievement.findByName("Invite") ?: new Achievement(name: "Invite", score: 10, description: "Invited a friend").save(failOnError: true)
    def interactionInvite = Interaction.findByName("Invite") ?: new Interaction(name: "Invite", description: "Invited a friend(s)").save(failOnError: true)
    def interactionBuck = Interaction.findByName("Buck Wild") ?: new Interaction(name: "Buck Wild", description: "Mudded with Buck Wild").save(failOnError: true)
    def interactionDrWalnuts = Interaction.findByName("Dr Walnuts") ?: new Interaction(name: "Dr Walnuts", description: "Mudded with Dr Walnuts").save(failOnError: true)
    def interactionSasquatch = Interaction.findByName("Sasplotch") ?: new Interaction(name: "Sasplotch", description: "Mudded with Sasplotch").save(failOnError: true)
    def interactionDirtyHarry = Interaction.findByName("Dirty Harry") ?: new Interaction(name: "Dirty Harry", description: "Mudded with Dirty Harry").save(failOnError: true)
    def interactionMuddedAFriend = Interaction.findByName("Mudded a Friend") ?: new Interaction(name: "Mudded a Friend", description: "Mudded a Friend.").save(failOnError: true)
    def interactionSharedAMudding = Interaction.findByName("Shared Mud") ?: new Interaction(name: "Shared Mud", description: "Shared a mudded photo.").save(failOnError: true)


  }
  def destroy = {
  }
}
