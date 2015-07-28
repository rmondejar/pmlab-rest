class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/api/verify"(controller: "rest", action: "verify", method: "GET")
        "/api/discover"(controller: "rest", action: "discover", method: "POST")

	}
}
