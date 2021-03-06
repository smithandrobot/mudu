package mudu

import grails.converters.JSON
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.util.URIUtil

class FacebookService {

  static transactional = true

  def graphURL = "https://graph.facebook.com/me"
  def apiURL = "https://api.facebook.com/method/"

  public fetchFacebookData(token) {

    if (token == null) {
      throw new Error("Token not provided.")
    }

    def method = new GetMethod(graphURL)

    method.setRequestHeader("Accept", "text/json")
    method.setQueryString(
            URIUtil.encodeQuery("fields=id,name,birthday,gender,location,email&access_token=$token"))
    return connect(method)
  }

  public fetchFriendsUsingApp(token){
    def method = new GetMethod(apiURL + "friends.getAppUsers")
    method.setRequestHeader("Accept", "text/json")

    method.setQueryString(
            URIUtil.encodeQuery("access_token=$token&format=json"))

    return this.connect(method)
  }

  public fetchNetworks(Player player) {

    def method = new GetMethod(apiURL + "users.getInfo")
    method.setRequestHeader("Accept", "text/json")
    method.setQueryString(
            URIUtil.encodeQuery("fields=affiliations&access_token=$player.facebookToken&uids=$player.facebookId&format=json"))
    return this.connect(method)
  }

  private connect(method) {
    def client = new HttpClient()
    client.executeMethod(method)
    def raw = method.getResponseBodyAsString().toString()

    if (raw.contains('error')) {
      throw new Error("Facebook error: " + raw)
    }

    def resp = JSON.parse(raw)

    return resp
  }

}
