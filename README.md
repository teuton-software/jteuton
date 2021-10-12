# jteuton

All-in-one Java wrapper for [teuton](https://github.com/teuton-software/teuton) Ruby gem (using JRuby).

## How to compile and install jteuton to your local Maven repo

```bash
git clone https://github.com/teuton-software/jteuton.git
cd jteuton
mvn install
```

## Adding jteuton library to POM

```xml
<dependency>
	<groupId>io.github.teuton-software</groupId>
	<artifactId>jteuton</artifactId>
	<version>2.1.11</version>
</dependency>
```

## Usage examples

Impot `Teuton` class:

```java
import io.github.teuton.Teuton;
```

### Get teuton version

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
teuton (version X.Y.Z)
```

### Get teuton help


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