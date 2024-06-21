import groovy.xml.XmlUtil
import com.cloudbees.hudson.plugins.folder.Folder
import org.jenkinsci.plugins.workflow.job.WorkflowJob

def jobs = Jenkins.instance.getAllItems(WorkflowJob.class).findAll { w -> w.getFullName().startsWith("unified") }
def unifiedJobs= [:]


jobs.each { j -> 
  def xml = new XmlSlurper(false, false).parse(j.getConfigFile().getFile())
  unifiedJobs[j.getFullName()] = [
  	scriptPath : xml.definition.scriptPath,
  	sctiptUrl: xml.definition.scm.userRemoteConfigs['hudson.plugins.git.UserRemoteConfig'].url
  ]
}

println(unifiedJobs)

unifiedJobs.each { u -> 
  println("name: ${u.key}")
  println("\t* Script Path: ${u.value.scriptPath}")
  println("\t* Sctipt Url  :${u.value.sctiptUrl}")
}

unifiedJobs.each { u -> 
  println("${u.key};${u.value.scriptPath};${u.value.sctiptUrl}")
}
