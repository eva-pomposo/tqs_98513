API que escolhi:
    https://rapidapi.com/api-sports/api/covid-193

/countries:
->pesquisar os paises todos;
->pesquisar os paises por nome;

/statistics:
-> ver as estatisticas de hoje de cada pais
-> ver as estatisticas de hoje para um determinado pais;

/history:
-> ver as estatisticas de toda a historia de um pais;
-> ver todas as estatisticas de um determinado dia de um pais;


https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_filter_table

https://adminlte.io/themes/v3/iframe.html#


Tirar o string e colocar date e time.
-> Verficar se corrigi o Log "Delete all countries" estar sempre aparecer
->Quando procuro a history do pais x retorna uma lista vazia, devia ser assim ou returnar uma execao de tipo erro??E dps guardo esta lista vazia na cache, devia ser assim?
->No file Covid19ControllerTest o teste: testGetHistoryByCountryAndByDay_withParametersInvalid  , esta completo?
-> verificar se no file Covid19ServiceTest as funcoes verify estao bem , tive preguica
se tiver tempo fazer teste para o model
teste de integracao "Covid19RestControllerIT.java" acho que esta mal feito pq tenho duas funcao no service publicas que chamam o objeto da cache  


quando pesquiso por USA no otherdays encrava por ser mts dados
quando pesquiso por dia, o dia desaparece do input :(
Compor os model, tirar as anotacoes, tirar os repository
No file Covid19ServiceTest falta testes quando a API n esta a funcionar
ResolverTest verificar se aquilo que achava esta certo, e falta testes de quando converto algo invalido 
se usar o docker mudar a porta que esta no teste cucumber no file feature
