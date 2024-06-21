import groovy.xml.XmlUtil
import com.cloudbees.hudson.plugins.folder.Folder
import org.jenkinsci.plugins.workflow.job.WorkflowJob

def jobs = Jenkins.instance.getAllItems(WorkflowJob.class)
def jobsNames = []
def scriptToLookfor = "Accessibility-Build.groovy"

jobs.each { j -> 
  def xml = new XmlSlurper(false, false).parse(j.getConfigFile().getFile())
  if (xml.definition.scriptPath.toString() == scriptToLookfor ) {
  	jobsNames.add(j.getFullName())
  }
}

println(jobsNames)
