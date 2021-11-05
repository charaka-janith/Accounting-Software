# Accounting Software

This Standalone Software is for dealing with cash flow in your business. 🕊

## Installation

* Create user account in mySql or mariaDb client as the comment in
```bash
back_end -> config -> SqlConfig
```
* Paste the db text in resources to your mySql or mariaDb client
```bash
resources -> db.txt
```
* After loading maven changes, modify run configuration using exec:java in exec plugin with adding "compile" before to execution command
```bash
compile exec:java -f pom.xml
```

## Technologies

* IDE - Intellij Ultimate
* Database - MySql / MariaDb
```bash
org.openjfx - <version>17.0.0.1</version>
com.jfoenix - <version>9.0.10</version>
MySQL - <version>8.0.25</version>
<maven.compiler.source>17</maven.compiler.source>
<modelVersion>4.0.0</modelVersion>
```

## Contributing
#### Charaka Janith ♥️
#### Ayesh Gunathilaka ♥️
#### Dilini Fernando ♥️
#### Buddhika Mahesh ♥️
#### Shashika Vidushan ♥️

## License
[MIT](https://choosealicense.com/licenses/mit/)