package pmlab.rest

import grails.transaction.Transactional

@Transactional
class ShellService {

    def grailsApplication

    def verify() {

        String dir = grailsApplication.config.pmlab.folder
        File workingFolder = new File(dir)

        [workingFolder:workingFolder.absolutePath]
    }

    def discover(filename, xes) {

        def map = [:]

        def dir = grailsApplication.config.pmlab.folder
        filename = dir+"/"+filename

        File xesFile = new File(filename)
        xesFile << xes

        def process = "cat $filename".execute()
        println "${process.text}"

        map
    }
}
