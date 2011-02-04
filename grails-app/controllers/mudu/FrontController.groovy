package mudu

import grails.plugin.springcache.annotations.Cacheable


class FrontController {

  def statsService

  @Cacheable(cache = "topTenCache")
  def index = {
     [topTen:statsService.topTen()]
  }
}
