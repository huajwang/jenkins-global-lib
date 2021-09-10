package com.longmaple.jenkins

import org.apache.commons.lang3.SystemUtils

@Grab('org.apache.commons:commons-lang3:3.8.1')
class CommonUtils implements Serializable {

	def wrapper(String... args) {
		if (!SystemUtils.IS_OS_WINDOWS) {
			steps.sh "./gradlew ${args.join(' ')} -s"
		} else {
			steps.bat "gradlew.bat ${args.join(' ')} -s"
		}
	}
}