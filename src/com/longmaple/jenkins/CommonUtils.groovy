package com.longmaple.jenkins


class CommonUtils implements Serializable {

	def steps

	CommonUtils(steps) {
		this.steps = steps
	}

	def runPy(String scriptPath, def args) {
		String script = libraryResource(scriptPath)
		String argsString = args.join(' ')
		steps.bat "python ${script} ${argsString}"
	}
}
