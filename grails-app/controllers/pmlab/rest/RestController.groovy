package pmlab.rest

import grails.plugins.rest.client.RestBuilder
import grails.converters.JSON
import groovy.util.slurpersupport.GPathResult

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

        render result
    }

    def fetch() {

        println params
        def result = [:]
        if (params.url && params.usr && params.pwd) {

            def rest = new RestBuilder()

            println "fetching : ${params.url} ${params.usr} ${params.pwd}"
            def resp = rest.get(params.url) {
                auth params.usr, params.pwd
            }

            println "response xml : "+(resp?.xml instanceof GPathResult)
            if (!resp.responseEntity.body) result = [error:"error retrieving xes from $params.url"]
            else result = shellService.discover(resp.responseEntity.body)
        }
        else result = [error:"some param not found : url, usr or pwd"]

        render result
    }
}
