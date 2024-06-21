// remove all build in queue

Jenkins.instance.queue.getBuildableItems().each {
	it.doCancelQueue()
}
