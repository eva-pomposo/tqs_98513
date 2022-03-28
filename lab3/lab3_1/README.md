## Exercise 3.1

### a) Identify a couple of examples on the use of AssertJ expressive methods chaining.

* No teste A_EmployeeRepositoryTest:
    ```
    assertThat( found ).isEqualTo(alex)  
    assertThat(fromDb).isNull()
    ```

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

O teste B é um unit test, logo aí podemos concluir que os testes não envolvem a base de dados. Nesta classe usa-se o JUnit5 e o Mockito, logo faz-se os testes necessários para interface EmployeeServiceImpl sem se testar os métodos da interface EmployeeRepository chamados, fazendo assim mock de EmployeeRepository. Nesta interface de teste confia-se apenas no Junit + Mockito para controlar testes e para definir expectativas e verificações.

### c) What is the difference between standard @Mock and @MockBean?

Tanto a anotação @Mock como a anotação @MockBean servem para criar objetos simulados de uma classe ou interface. Mas enquanto que @Mock pode ser usado em qualquer estrutura de aplicação, o @MockBean é apenas utilizado na framework Spring.  

A anotação @Mock (usado só em classes teste) como dizemos em cima cria uma simulação, pois aqui a implementação da classe com esta anotação não interessa. A extensão Mockito JUnit Jupiter vai instanciar o mock e injetá-lo na respectiva classe teste. Normalmente em conjunto com esta anotação usa-se o @InjectMocks que vai procurar um construtor adequado para passar o mock, criando assim uma instância da classe com a anotação @Mock.

A simulação da anotação @MockBean vai substituir qualquer bean existente do mesmo tipo no contexto da aplicação. Caso nenhum bean do mesmo tipo seja definido, irá ser adicionado um novo. Quando usamos o @MockBean em um campo, o mock vai ser injetado no campo, além de ser registado no contexto da aplicação.
Esta anotação é útil em testes de integração onde um bean (ex: serviço externo) precisa ser simulado.

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

O ficheiro **application-integrationtest.properties** contém informações para configurar o armazenamento de persistência, não usando assim uma base de dados real na execução dos testes de integração.

Na linha de código:
    ```
    @TestPropertySource(locations = "application-integrationtest.properties")
    ```

O ficheiro indicado com @TestPropertySource substituirá o application.properties. Esta anotação ajuda assim a configurar os locais dos ficheiros de propriedades específicos para os nossos testes.

### e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

As estratégias usadas nos testes D e E aparentam alguma semelhança, tendo estes dois uma disparidade notável com o teste C.

Para começar o teste C não envolve a base de dados, mas o teste D e E envolvem.
O teste C usa o @WebMvcTest, que simula o comportamento de um servidor da aplicaçao num ambiente simples, e além dissoo usa o MockMvc que fornece uma API expressiva. Este teste ainda simula as dependencias relacionadas com o service implementation com a anotação @MockBean. Aqui o componente repository tambem não é envolvido.

Tanto o teste D como o E são testes de integração que envolvem vários componentes. Ambos envolvem as componentes: service implementation, o repository e a base de dados.
Os dois testes iniciam o contexto da Web completo através da anotação @SpringBootTest e a API é implementada no contexto SpringBoot.
A diferença entre estes dois testes é nesta API implementada, o teste D usa o Mockvc como entry point para suporte de teste Spring MVC do lado do servidor. Já o teste E usa o TestRestTemplate, um cliente REST para criar pedidos realistas, envolvendo também respostas.
