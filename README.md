# jteuton

[![Maven Central](http://img.shields.io/maven-central/v/io.github.teuton-software/jteuton)](https://search.maven.org/artifact/io.github.teuton-software/jteuton)
[![GPL-3.0](https://img.shields.io/badge/license-GPL--3.0-%250778B9.svg)](https://www.gnu.org/licenses/gpl-3.0.html)

All-in-one Java wrapper for [teuton](https://github.com/teuton-software/teuton) Ruby gem (using JRuby).

## Using jteuton library with Maven

```xml
<dependency>
    <groupId>io.github.teuton-software</groupId>
    <artifactId>jteuton</artifactId>
    <version>{jteuton.version}</version>
</dependency>
```

## Using jteuton library with Gradle

```groovy
implementation 'io.github.teuton-software:jteuton:{jteuton.version}'
```

## How to compile and install jteuton to your local Maven repo

Clone the repo and change to its directory:

```bash
git clone https://github.com/teuton-software/jteuton.git
cd jteuton
```

Download and install Ruby gems (`teuton` and `teuton-get`) into `src/main/resources`:

```bash
mvn exec:exec@install-rubygems
```

Build, package and install the JAR library into your local Maven repo:

```bash
mvn install
```

## Upgrade Ruby gems

`jteuton` depends on some Ruby gems: `teuton` and `teuton-get`.

### Install Ruby gems

```bash
mvn exec:exec@install-rubygems
```

### Uninstall Ruby gems

```bash
mvn clean:clean@uninstall-rubygems
```

> Also deletes `target`directory.

## Usage examples:

### teuton

Import `Teuton` class:

```java
import io.github.teuton.Teuton;
```

#### Get version

Code:

```java
System.out.println(Teuton.execute("version"));
```

or

```java
System.out.println(Teuton.version());
```

Output:

```
X.Y.Z
```

#### Get help

Code:

```java
System.out.println(Teuton.execute("help"));
```

Output:

```
Commands:
  teuton [run] [OPTIONS] DIRECTORY  # Run challenge from directory
  teuton check [OPTIONS] DIRECTORY  # Test or check challenge contents
  teuton help [COMMAND]             # Describe available commands or one spec...
  teuton new DIRECTORY              # Create skeleton for a new project
  teuton readme DIRECTORY           # Create README.md file from challenge co...
  teuton version                    # Show the program version
```

### teuton-get

Import `TeutonGet` class:

```java
import io.github.teuton.TeutonGet;
```

#### Get version

Code:

```java
System.out.println(TeutonGet.execute("version"));
```

or

```java
System.out.println(TeutonGet.version());
```

Output:

```
X.Y.Z
```