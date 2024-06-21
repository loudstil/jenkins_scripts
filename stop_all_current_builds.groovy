// remove all current builds
import com.cloudbees.hudson.plugins.folder.Folder
import org.jenkinsci.plugins.workflow.job.WorkflowJob
 
def jobs = Jenkins.instance.getAllItems(WorkflowJob.class)

jobs[0].builds.each { b ->
  if(b.isBuilding()) {
    b.doStop();
    println(b.getDisplayName() + " successfully stopped!")
  }
}
