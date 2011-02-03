package mudu

class FrontController {

  def statsService

  def index = {
     [topTen:statsService.topTen()]
  }
}
