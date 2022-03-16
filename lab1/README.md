# Lab 1: Unit testing (with JUnit 5)

## Useful commands 
```
mvn compile
mvn clean package
mvn test
```
## [ IMPORTANT ] NOTE FROM EXERCISE 1.1  
Este exercicio só dá para correr através do terminal com os comandos mencionados anteriormente, nao consegui correr pelo editor de texto (usei VScode)

## Some notes from exercise 1.2 
a) Alterei a funcao format() do ficheiro Dip.java, e também no teste testConstructorFromBadArrays no ficheiro DipTest.

b) e c) Na funcao generateRandomDip() do ficheiro Dip.java, alterei o valor máximo de estrelas para 12 (estava 10), e fiz o Refactor do código extraindo constantes.

d)
Desenvolvi a implementaçao da funçao intersects() no ficheiro SetOfNaturals.java.

No ficheiro SetOfNaturalsTest no teste testAddElement e testAddBadArray, adicionei testes de modo que verifique que nao seja possivel adicionar elementos duplicados, nem elementos que nao sejam inteiros. Além disso, os testes tambem verificam se os elementos foram adicionados, e se o tamanho aumentou de forma correta. 

No teste testFromArray do ficheiro SetOfNaturalsTest, verifiquei se o objeto de SetOfNatural returnado nao tem elementos duplicados, se só tem numeros inteiros, se contem todos os elementos, e se tem o tamanho correto.

Adicionei o teste testContains, para verficar se devolve o booleano certo caso o elemento esteja num set ou nao.

e)
In this project there are 645 instructions that refer to the byte code instructions.

In package euromillions the report shows 81% instructions coverage, 87% branches coverage, 10/42 for cyclomatic complexity.Still in this package, only the generateRandomDraw() method and getDrawResults() method of the EuromillionsDraw class, and the format() method of the CuponEuromillions class weren't tested.

In package sets the report shows 88% instructions coverage, 83% branches coverage, 4/19 for cyclomatic complexity.Still in this package, only the hashcode() method and equals() method of the SetOfNaturals class weren't tested.

In package ui the report shows 0% instructions coverage, 0% branches coverage, 3/3 for cyclomatic complexity. No code has been tested.

Classes/methods offer less coverage:
-> generateRandomDraw() method and getDrawResults() method of the EuromillionsDraw class
-> format() method of the CuponEuromillions class

All possible decision branches aren't being covered. But 100% code coverage doesn't necessarily reflect effective testing,and so I think branches that aren't covered, don't need to be covered. 