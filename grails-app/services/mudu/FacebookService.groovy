package mudu

import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.util.URIUtil
import grails.converters.JSON

class FacebookService {

  static transactional = true

  def graphURL = "https://graph.facebook.com/me"
  def apiURL = "https://api.facebook.com/method/"

  public fetchFacebookData(token) {

    if (token == null){
      throw new Error("Token not provided.")
    }
    def client = new HttpClient()
    def method = new GetMethod(graphURL)

    method.setRequestHeader("Accept", "text/json")
    method.setQueryString(URIUtil.encodeQuery("fields=id,name,birthday,gender,location&access_token=$token"))
    client.executeMethod(method)

    return JSON.parse(method.getResponseBodyAsString().toString())
  }

}
