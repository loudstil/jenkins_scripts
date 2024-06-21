def today = new Date()
def jobs = Jenkins.instance.getAllItems(Job.class)
	.findAll{j -> !j.isBuilding() && 
      			   j.getLastBuild() != null && 
      			   j.getLastBuild().result == Result.FAILURE &&
    			   today.minus(j.getLastBuild().time) <= 1}
	.collect{j -> j.getLastBuild().api.getDisplayName()}
