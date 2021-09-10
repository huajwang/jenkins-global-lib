package com.longmaple.jenkins

import org.apache.commons.lang3.SystemUtils


@Grab('org.apache.commons:commons-lang3:3.12.0')
class CommonUtils implements Serializable {

	def steps

	CommonUtils(steps) {
		this.steps = steps
	}

	def wrapper(String... args) {
		if (!SystemUtils.IS_OS_WINDOWS) {
			steps.sh "./gradlew ${args.join(' ')} -s"
		} else {
			steps.bat "gradlew.bat ${args.join(' ')} -s"
		}
	}
}
