import mudu.auth.SecRole
import mudu.auth.SecUser
import mudu.auth.SecUserSecRole

class BootStrap {

  def springSecurityService
  def init = { servletContext ->
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
