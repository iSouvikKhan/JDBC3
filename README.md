# JDBC3 - Date Handling with JDBC and MySQL

A console-based Java practice project that shows how to store and read date values in MySQL using JDBC. It focuses on converting user-entered date strings in different formats into `java.util.Date` and `java.sql.Date`, inserting them with a `PreparedStatement`, and formatting dates read back from the database.

## Features

A menu-driven console app (`Date.Driver`) offers three operations on a `datedata` table:

1. **Read all rows** - runs a `SELECT` with a plain `Statement` and prints every record, with dates formatted as `dd-MM-yyyy`.
2. **Read one row by name** - prints all rows, then asks for a name and looks it up with a `PreparedStatement`. It prints the raw `java.sql.Date` values and then the formatted row, or a "record not available" message.
3. **Insert a row** - asks for name, address, gender and three dates, each in a different input format, then inserts the row with a `PreparedStatement` and prints the table again.

After each operation, enter `7` to continue or `9` to exit. Update and delete options are present in the source but commented out.

### Date conversions demonstrated

| Field | Input format | Conversion used |
|-------|--------------|-----------------|
| DOB   | `dd-mm-yyyy` | `SimpleDateFormat("dd-MM-yyyy").parse(...)` -> `java.util.Date` -> `new java.sql.Date(getTime())` |
| DOJ   | `mm-dd-yyyy` | `SimpleDateFormat("MM-dd-yyyy").parse(...)` -> `java.util.Date` -> `new java.sql.Date(getTime())` |
| DOM   | `yyyy-mm-dd` | `java.sql.Date.valueOf(...)` directly |

The insert step prints the string, `java.util.Date` and `java.sql.Date` forms of each value so the differences are visible.

## Tech Stack

- Java (the Eclipse project targets JavaSE-18)
- JDBC (`java.sql`)
- MySQL with MySQL Connector/J (referenced in Eclipse as a user library named `MySQLJAR`)

## Project Structure

```
JDBC3/
├── src/
│   ├── Date/
│   │   ├── Driver.java                  # Entry point: console menu
│   │   ├── Insert.java                  # Reads input, converts dates, inserts a row
│   │   ├── ReadAll_using_Statement.java # Lists all rows using Statement
│   │   └── ReadOne.java                 # Finds a row by name using PreparedStatement
│   └── JDBC_Util_Package/
│       └── JDBCUtil.java                # Opens and closes JDBC resources
├── .classpath / .project / .settings/   # Eclipse project files
└── .gitignore
```

## Prerequisites

- JDK 18 or later
- A running MySQL server
- MySQL Connector/J JAR

## Setup

1. **Database.** `JDBCUtil` connects to `jdbc:mysql://localhost:3306/javaconnectiondb`. Create that database and a `datedata` table with the columns the code uses: `id`, `name`, `address`, `gender`, `dob`, `doj`, `dom`. The insert does not supply `id`, so it should be auto-generated. For example:

   ```sql
   CREATE DATABASE javaconnectiondb;
   USE javaconnectiondb;

   CREATE TABLE datedata (
       id      INT AUTO_INCREMENT PRIMARY KEY,
       name    VARCHAR(50),
       address VARCHAR(100),
       gender  VARCHAR(10),
       dob     DATE,
       doj     DATE,
       dom     DATE
   );
   ```

2. **Credentials.** The username and password are hard-coded in `src/JDBC_Util_Package/JDBCUtil.java`. Change them to match your MySQL account before running.

## How to Run

### Eclipse

1. Import the folder as an existing Eclipse project.
2. Create (or rename) a user library called `MySQLJAR` that contains the Connector/J JAR, or add the JAR to the build path directly.
3. Run `Date.Driver` as a Java application.

### Command line

Replace `mysql-connector-j.jar` with the path to your Connector/J JAR.

Windows:

```bat
javac -d bin src\JDBC_Util_Package\JDBCUtil.java src\Date\*.java
java -cp "bin;mysql-connector-j.jar" Date.Driver
```

Linux/macOS:

```bash
javac -d bin src/JDBC_Util_Package/JDBCUtil.java src/Date/*.java
java -cp "bin:mysql-connector-j.jar" Date.Driver
```

## Notes

- The name lookup reads a single word, so names that contain spaces will not be matched.
- Read operations format all dates as `dd-MM-yyyy`, regardless of the format used on input.
