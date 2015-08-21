package pmlab.rest

import grails.transaction.Transactional

import org.activiti.bpmn.BpmnAutoLayout
import org.activiti.bpmn.converter.BpmnXMLConverter

import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamReader

@Transactional
class AutoLayoutService {

    def complete(xml) {

        def xmlComplete

        try {

            Reader reader = new StringReader(xml)
            XMLInputFactory factory = XMLInputFactory.newInstance() // Or newFactory()
            XMLStreamReader xtr = factory.createXMLStreamReader(reader)

            def bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr)
            def layout = new BpmnAutoLayout(bpmnModel)
            layout.execute()
            xmlComplete = new String(new BpmnXMLConverter().convertToXML(bpmnModel))

        } catch(e) {
            println "ERROR parsing XML : $e"
            log.info "ERROR parsing XML : $e"
        }

        xmlComplete

    }
}
