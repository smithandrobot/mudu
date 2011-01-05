class UrlMappings {

  static mappings = {


    "/api/players/$action?/$id?" {
      controller = "playerRest"
    }

    "/api/achievements/$action?/$id?" {
      controller = "achievementRest"
    }

    "/api/" {
      controller = "rest"
    }

    "/login/$action?/$id?" {
      controller = "login"
    }

    "/logout/$action?/$id?" {
      controller = "logout"
    }

    "/admin/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
