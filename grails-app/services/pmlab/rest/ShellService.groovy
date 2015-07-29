package pmlab.rest

import grails.transaction.Transactional

@Transactional
class ShellService {

    def verify() {

        def process = "pwd".execute()
        [workingFolder:process.text.trim()]
    }

    def discover(xes) {

        def dir = File.createTempDir().absolutePath
        String input = "$dir/input.xes"
        String output = "$dir/bpmn.xml"

        File xesFile = new File(input)
        xesFile << xes

        String cmd = "xes2bpmn.py $input $output"
        println "Executing > $cmd"
        def process = cmd.execute()

        def result = [:]
        File bpmnFile = new File(output)
        try {
            result = [bpmn: bpmnFile.text]
        } catch (e) {
            result = [error: "bmpn file not generated correctly"]
        }

        result
    }
}
