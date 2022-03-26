# Lab 2 Mocking dependencies (for unit testing)

## Useful commands 
```
mvn compile
mvn test
mvn package
mvn integration-test
mvn install integration-test
mvn install
$ mvn install -DskipTests=true
```

# Some notes from exercise 2.2

b) 
Na programaçao é normal que algumas classes tenham dependencias, e se nestes casos usarmos apenas o JUnit para testes, os testes vao depender tambem de outros métodos. Para que os testes sejam independentes temos que usar a framework Mock, onde existe um SuT, e um mock.

A classe AddressResolver tem um método findAddressForLocation() que chama um método de outra classe doHttpGet(), portanto a classe AddressResolver tem dependencias, logo esta classe é o SuT.   
Perante a informação dita anteriormente, ainda podemos dizer que a interface ISimpleHttpClient é aquela onde vamos fazer o mock, pois é esta que contem o método que é chamado na classe AddressResolver, fornecendo-lhe informacoes necessarias para a implementacao do método findAddressForLocation(). 

# Some notes from exercise 2.3

A classe AddressResolverIT é uma classe de teste de integração. Ao contrário dos Unit test onde métodos são testados individualmente, nos testes de integração os módulos são combinados e testados em grupo.  
Quando corri o comando **mvn test** o tempo total gasto foi apenas 1.935s, e aqui correu os testes da classe AddressResolverTest, que contem 2 testes.
Já quando corri o comando **mvn install failsafe:integration-test** o tempo total gasto foi de 3.349s, observei que aqui correu os testes da classe AddressResolverTest (2 testes), ainda instalou coisas necessarias para os testes de integração e correu tambem os testes da classe AddressResolverIT (2 testes)