package com.longmaple.jenkins


class CommonUtils implements Serializable {

	def steps

	CommonUtils(steps) {
		this.steps = steps
	}

	def runPy(String... args) {
		String argsString = args.join(' ')
		steps.bat "python ../../scripts/jenkins.py ${argsString}"
	}
}
