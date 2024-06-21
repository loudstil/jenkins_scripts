// get all plugins
Jenkins.instance.pluginManager.plugins.collect{
  plugin -> 
    return "${plugin.getDisplayName()} (${plugin.getShortName()}): ${plugin.getVersion()}" 
}
.sort()
.each { println(it) }


// get all plugins with depedenecies
Jenkins.instance.pluginManager.plugins.findAll { p -> p.hasDependencies() }.collect{
  plugin -> 
  return "${plugin.getDisplayName()} (${plugin.getShortName()}): ${plugin.getVersion()} \nDependencies:\n${plugin.getDependencies().collect { d -> d.toString()}.join('\n')}\n\n " 
}
.sort()
.each { println(it) }

// get all disabled plugins
Jenkins.instance.pluginManager.plugins.findAll{
  plugin -> !plugin.isActive()
}
.sort()
.each { println(it) }

// uninstall plugins
["plugin list"].each { plg -> 
	def plugin = Jenkins.instance.pluginManager.plugins.find { p ->  p.getDisplayName() == plg }
  if(plugin) {
    println("Uninstalling ${plg}")
  	plugin.doDoUninstall()
  }
}

Jenkins.instance.pluginManager.plugins
	.findAll { p -> p.getDisplayName().contains("Blue Ocean") }
	.each { plg -> 
  if(plg) {
    println("Uninstalling ${plg}")
  	plg.doDoUninstall()
  }
}


// approve all pending scripts
import org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval

def scriptApproval = ScriptApproval.get()
def hashesToApprove = scriptApproval.getPendingScripts().collect { sa -> sa.getHash() }

hashesToApprove.each { scriptApproval.approveScript(it)  }

// get all environment vars


def entry = new EnvironmentVariablesNodeProperty.Entry(env_key, env_value)
def evnp = new EnvironmentVariablesNodeProperty(entry)
node.nodeProperties.add(evnp)
/*

*/
