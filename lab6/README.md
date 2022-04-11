# Lab 6 Static Code analysis (with SonarQube)

## Exercise 6.2

Prepare a local instance of SonarQube (using the Docker image):
```
$ docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
```

Once your instance is up and running, Log in to http://localhost:9000 using System Administrator credentials:
* login: admin
* password: admin

Update password: 12345

Project key, display name: lab6_2
Name Token: token1
Gnerate:
    token1: 819438c51094e6232c871340f06e66c273b94515

```
 mvn clean verify sonar:sonar   -Dsonar.projectKey=lab6_2   -Dsonar.host.url=http://localhost:9000   -Dsonar.login=819438c51094e6232c871340f06e66c273b94515
 ```

### Alinea f

O meu projeto passou the defined quality gate.

### Alinea g

| Issue                 | Problem description   | How to solve |
| --------------------- |            ---------- | -----        |
| ![](./lab6_2/image1.png)     | FIXME tags are commonly used to mark places where a bug is suspected, but which the developer wants to dealwith later (MITRE, CWE-546 - Suspicious Comment). Sometimes the developer will not have the time or will simply forget to get back to that tag. This rule is meant to track those tags and to ensure that they do not go unnoticed.                     |  Retirar o comentario, visto que o projeto já está feito, e nao temos que fazer aquilo que o FIXME indica.            |
| ![](./lab6_2/image2.png)     | The imports part of a file should be handled by the Integrated Development Environment (IDE), not manually by the developer. Unused and useless imports should not occur if that is the case. Leaving them in reduces the code’s readability, since their presence can be confusing. (Code smell, minor)                       |  Retirar o import pois estamos a fazer este import desnecessariamente, visto que as classes do mesmo package estao implicitamente imported.               |
| ![](./lab6_2/image3.png)    | JUnit5 is more tolerant regarding the visibilities of Test classes than JUnit4, which required everything to be public. In this context, JUnit5 test classes can have any visibility but private, however, it is recommended to use the default package visibility, which improves readability of code. (Bug handled by the rule S5810 .)                       | Alterar a visibilidade de public para private, tanto na classe como na funcao testGetTotalValue().              |