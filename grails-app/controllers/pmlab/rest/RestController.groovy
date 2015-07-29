package pmlab.rest

import grails.converters.JSON

class RestController {

    def shellService

    def verify() {

        def result = shellService.verify()

        render (result as JSON)
    }

    def discover() {

        def result = [:]
        if (params.xes) {
            String xesXML = new String(params.xes?.bytes)
            result = shellService.discover(xesXML)
        }
        else result = [error:"xes param not found"]

        render (result as JSON)
    }
}
