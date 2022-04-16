# Lab 4 Acceptance testing with web automation (Selenium)

## Exercise 4.3

A extensão Selenium-Júpiter se calhar nao está a ser bem utilizada. Os ficheiros testes deviam conter as seguintes linhas se calhar:
```
String driverPath = "/home/eva/Downloads/geckodriver-v0.30.0-linux64/geckodriver";
System.setProperty("webdriver.gecko.driver", driverPath);
```