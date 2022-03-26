# Lab 2 Mocking dependencies (for unit testing)

## Useful commands 
```
mvn compile
mvn test
mvn package
mvn [integration-test] ?? mvn install integration-test
mvn install
$ mvn install -DskipTests=true
```

# 2.1
n percebi mt bem o verify

# 2.2

b) 
Na programaçao é normal que algumas classes tenham dependencias, e se nestes casos usarmos apenas o JUnit para testes, os testes vao depender tambem de outros métodos. Para que os testes sejam independentes temos que usar a framework Mock, onde existe um SuT, e um mock.

A classe AddressResolver tem um método findAddressForLocation() que chama um método de outra classe doHttpGet(), portanto a classe AddressResolver tem dependencias, logo esta classe é o SuT.   
Perante a informação dita anteriormente, ainda podemos dizer que a interface ISimpleHttpClient é aquela onde vamos fazer o mock, pois é esta que contem o método que é chamado na classe AddressResolver, fornecendo-lhe informacoes necessarias para a implementacao do método findAddressForLocation(). 
