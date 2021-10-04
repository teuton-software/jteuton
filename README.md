# jteuton

All-in-one Java wrapper for [teuton](https://github.com/teuton-software/teuton)  (including JRuby).

## How to compile and install jteuton library

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
	<version>2.1.7</version>
</dependency>
```

## Usage examples

### Get teuton version

Code:

```java
import io.github.teuton.Teuton;

public class Sample {
	public static void main(String[] args) {
		System.out.println(Teuton.execute("version"));
	}
}
```

Output:

```
teuton (version 2.1.7)
```

### Get teuton help


Code:

```java
import io.github.teuton.Teuton;

public class Sample {
	public static void main(String[] args) {
		String output = Teuton.execute("help");
		System.out.println(output);
	}
}
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