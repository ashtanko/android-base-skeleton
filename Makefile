.PHONY: check run
check:
		./gradlew spotlessApply spotlessCheck spotlessKotlin detekt --profile --daemon

run:
		./gradlew build

.DEFAULT_GOAL := check
