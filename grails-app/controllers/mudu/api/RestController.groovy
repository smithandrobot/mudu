package mudu.api

import grails.converters.JSON

class RestController {

  def index = { render "Hi there!" }

  def error(String msg) {

    def response = [
            stat: "fail",
            message: msg

    ]

    return response as JSON

  }

  def success(data) {
    def response = [
            stat: "ok",
            data: data

    ]

    return response as JSON
  }
}
