def call(String... args) {
	bat "gradlew.bat ${args.join(' ')} -s"
}