def call(String goToolName = 'go-1.12') {
	pipeline {
		agent {
			label 'docker-cloud-go'
		}
		tools {
			go "$goToolName"
		}
		environment {
			GO111MODULE = 'on'
			GITHUB_TOKEN = credentials('DOCKER_HUB_CREDENTIALS')
		}

		parameters {
			string(name: 'PROFILE', defaultValue: 'dev', description: 'project profile')
		}

		triggers {
			pollSCM('H */4 * * *')
		}


		stages {
			stage('Build') {
				steps {
					sh 'go build'
				}
			}
			stage('Test') {
				steps {
					sh 'go test ./... -coverprofile=coverage.txt'
					sh "curl -s https://codecov.io/bash | bash -s -"
				}
			}

			stage('Code Analysis') {
				steps {
					sh 'curl -sfL https://install.goreleaser.com/github.com/golangci/golangci-lint.sh | bash -s -- -b $GOPATH/bin v1.18.0'
					sh 'golangci-lint run'
				}
			}

			stage('Release') {
				when {
					buildingTag()
				}
				environment {
					GITHUB_TOKEN = credentials('GITHUB_TOKEN')
				}
				steps {
					sh 'curl -sL https://git.io/goreleaser | bash'
				}
			}

		}
		post {
			failure {
				mail to: 'cse63152@gmail.com',
				subject: 'Test Declarative Jenkins',
				body: 'This is a test'
			}
			success {
				mail to: 'cse63152@gmail.com',
				subject: 'Test is done!',
				body: 'This is a test of a shared library'
			}

		}

	}

}
