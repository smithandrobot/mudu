import mudu.auth.SecRole
import mudu.auth.SecUser
import mudu.auth.SecUserSecRole
import mudu.Achievement
import mudu.Interaction
import mudu.Player

class BootStrap {

  def springSecurityService
  def init = { servletContext ->

    def testPlayer = Player.findByFacebookId("00000") ?: new Player(facebookId: "00000").save(failOnError:true)
    def initialAchievement = Achievement.findByName("Invite") ?: new Achievement(name: "Invite", score: 10, description: "Invited a friend").save(failOnError: true)
    def interactionInvite = Interaction.findByName("Invite") ?: new Interaction(name: "Invite", description: "Invited a friend(s)").save(failOnError: true)
    def interactionBuck = Interaction.findByName("Buck Wild") ?: new Interaction(name: "Buck Wild", description: "Mudded with Buck Wild").save(failOnError: true)
    def interactionDrWalnuts = Interaction.findByName("Dr Walnuts") ?: new Interaction(name: "Dr Walnuts", description: "Mudded with Dr Walnuts").save(failOnError: true)
    def interactionSasquatch = Interaction.findByName("Sasquatch") ?: new Interaction(name: "Sasquatch", description: "Mudded with Sasquatch").save(failOnError: true)
    def interactionDirtyHarry = Interaction.findByName("Dirty Harry") ?: new Interaction(name: "Dirty Harry", description: "Mudded with Dirty Harry").save(failOnError: true)
    def interactionMuddedAFriend = Interaction.findByName("Mudded a Friend") ?: new Interaction(name: "Mudded a Friend", description: "Mudded a Friend.").save(failOnError: true)
    def interactionSharedAMudding = Interaction.findByName("Shared Mud") ?: new Interaction(name: "Shared Mud", description: "Shared a mudded photo.").save(failOnError: true)
    def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)
    def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)
    def adminUser = SecUser.findByUsername('admin') ?: new SecUser(username: 'admin',
            password: springSecurityService.encodePassword('R3t4rd3d'),
            enabled: true).save(failOnError: true)
    if (!adminUser.authorities.contains(adminRole)) {
      SecUserSecRole.create adminUser, adminRole
    }

  }
  def destroy = {
  }
}
