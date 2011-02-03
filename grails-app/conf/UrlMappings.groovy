class UrlMappings {

  static mappings = {


    "/api/players/$action?/$id?" {
      controller = "playerRest"
    }

    "/api/achievements/$action?/$id?" {
      controller = "achievementRest"
    }

    "/api/interactions/$action?/$id?" {
      controller = "interactionRest"
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

    "/admin/" {
      controller = "admin"
      constraints {
        // apply constraints here
      }
    }

    "/" {
      controller = "front"
    }

    "500"(view: '/error')
  }
}
