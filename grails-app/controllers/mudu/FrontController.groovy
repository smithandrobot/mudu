package mudu

import grails.plugin.springcache.annotations.Cacheable

class FrontController {

  def statsService

  @Cacheable("topTenCache")
  def index = {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -7);
    [topTen: statsService.topTen(),
            weeklyTopTen: statsService.topTen(cal.getTime()),
            weapons:statsService.weaponStats()]
  }
}
