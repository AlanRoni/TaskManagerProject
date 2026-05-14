# Upgrade Plan: taskmanager (20260512071324)

- **Generated**: 2026-05-12 07:13:24
- **HEAD Branch**: trial
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 17.0.16: C:\Users\alanr\.jdk\jdk-17.0.16\bin (current project JDK, used by step 2)
- JDK 25.0.1: C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot\bin (target runtime, used by steps 1, 3, 4)

**Build Tools**
- Maven 3.9.12: C:\Program Files\Apache\Maven\apache-maven-3.9.12\bin
- Maven 4.0+: **<TO_BE_INSTALLED>** (required by step 1 for Java 25 compatibility)

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260512071324
- Run tests before and after the upgrade: true

## Upgrade Goals

- Upgrade Java runtime target to the latest LTS version: Java 25

## Technology Stack

| Technology/Dependency         | Current         | Min Compatible | Why Incompatible / Notes                                        |
| ---------------------------- | --------------- | -------------- | --------------------------------------------------------------- |
| Java                         | 17              | 25             | User requested latest LTS                                        |
| Maven                        | 3.9.12          | 4.0.0          | Maven 3.9.x is not formally compatible with Java 25 builds       |
| maven-compiler-plugin        | 3.11.0          | 3.11.0 / 3.12+| 3.11.0 is recommended for Java 21+; may require validation for 25 |
| maven-surefire-plugin        | 3.1.2           | 3.1.2          | current version should support current test execution           |
| Hibernate ORM                | 7.3.3.Final     | 7.3.x          | Current version is compatible with Jakarta persistence and modern JDKs |
| Lombok                      | 1.18.46         | 1.18.46        | current version is fine                                          |
| MySQL Connector/J            | 9.7.0           | 9.x            | current version supports newer JDKs                              |
| JUnit 4                     | 4.13.2          | 4.13.2         | no upgrade required for Java 25 compile/test                    |

## Derived Upgrades

- Java 25 requires a compatible Maven build tool; Maven 4.0+ is needed because the current Maven 3.9.12 installation is not formally compatible with Java 25 per the upgrade compatibility reference.
- The current `maven-compiler-plugin` 3.11.0 is already at a version recommended for Java 21+; the upgrade plan will validate it on Java 25 and upgrade only if needed.
- No Jakarta namespace migration is required because the code already uses `jakarta.persistence` imports.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Java 25 requires a build tool that supports the latest JDK, and the workspace currently has Maven 3.9.12 only.
  - **Changes to Make**:
    - Install Maven 4.0+ and configure the execution environment to use it for upgrade verification.
    - Confirm JDK 25 is available and reachable at the selected path.
  - **Verification**: `mvn -version` using Maven 4.0+ and JDK 25, expected success.

- Step 2: Setup Baseline
  - **Rationale**: Verify the current `maven.compiler.release=17` baseline compiles and all tests pass before changing runtime target.
  - **Changes to Make**:
    - Use JDK 17.0.16 to execute the current Maven build.
    - Record baseline compilation and test behavior.
  - **Verification**: `mvn clean compile test-compile -q && mvn clean test -q` using JDK 17, expected success.

- Step 3: Update Java target to 25
  - **Rationale**: Apply the requested runtime upgrade and validate the project compiles with Java 25.
  - **Changes to Make**:
    - Update `maven.compiler.release` from `17` to `25` in `pom.xml`.
    - Validate `maven-compiler-plugin` compatibility and upgrade it if the Java 25 compile fails.
  - **Verification**: `mvn clean test-compile -q` using JDK 25 and Maven 4.0+, expected successful compilation of main and test code.

- Step 4: Final Validation
  - **Rationale**: Ensure the upgrade is complete by running the full test suite and fixing any compatibility issues found under Java 25.
  - **Changes to Make**:
    - Execute full Maven test lifecycle on Java 25.
    - Fix any failing tests or Java 25-specific runtime compatibility issues.
  - **Verification**: `mvn clean test -q` using JDK 25 and Maven 4.0+, expected all tests passing.

## Key Challenges

- **Build tool compatibility with Java 25**
  - **Challenge**: Maven 3.9.12 is not formally compatible with Java 25.
  - **Strategy**: Install Maven 4.0+ and use it as the execution build tool for the upgrade.

- **Compiler plugin compatibility**
  - **Challenge**: The current `maven-compiler-plugin` version 3.11.0 is recommended for Java 21+, but Java 25 may require a newer patch version.
  - **Strategy**: validate with JDK 25 and upgrade the plugin only if compile issues arise.

- **Jakarta Persistence and runtime compatibility**
  - **Challenge**: The project uses `jakarta.persistence` annotations while depending on Hibernate; ensure no hidden JDK 25 incompatibilities in existing ORM configuration.
  - **Strategy**: confirm compile and test under Java 25, with special attention to Hibernate classpath and JDBC compatibility.

- **Source-level JDK compatibility scan**
  - **Finding**: No `sun.*`, `jdk.internal.*`, reflection setAccessible patterns, or removed JDK module usages were detected in source files.
  - **Strategy**: rely on compile/test verification rather than source-level rewrites.
