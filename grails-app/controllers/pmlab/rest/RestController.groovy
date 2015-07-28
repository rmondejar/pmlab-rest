package pmlab.rest

import grails.converters.JSON

class RestController {

    def shellService

    def verify() {

        def result = shellService.verify()

        render (result as JSON)
    }

    def discover() {

        String filename = "${params.name}.xes"
        String xesXML = new String(params.xes?.bytes)

        def result = shellService.discover(filename, xesXML)

        render (result as JSON)
    }
}
