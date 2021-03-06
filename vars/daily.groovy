def call(String mavenToolName = 'apache-maven-3.8.2') {
	pipeline {

		agent {
			node {
				label 'emulator'
			}
		}

		tools {
			maven "$mavenToolName"	
		}
		
		stages {

			stage('Test') {
				steps {
					bat "dir"
				}
			}

			stage('Run python') {
				steps {
					bat "python ../../scripts/jenkins.py --find foo"
				}
			}

		}

	}

}
