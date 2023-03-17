Teste Admissional Prático da Apisul
Esse é o projeto desenvolvido para o teste admissional prático da Apisul. O objetivo é implementar um sistema que extrai informações de uso dos elevadores em um prédio.

Funcionamento do Sistema
O sistema foi implementado na classe ElevadorServiceImpl, que implementa a interface IElevadorService. Essa classe recebe como parâmetro de construção um arquivo JSON contendo as informações de uso dos elevadores pelos usuários. A partir dessas informações, o sistema é capaz de extrair as seguintes informações:

Andar menos utilizado pelos usuários.
Elevador mais frequentado e o período em que se encontra maior fluxo.
Elevador menos frequentado e o período em que se encontra menor fluxo.
Período de maior utilização do conjunto de elevadores.
Percentual de uso de cada elevador em relação ao total de serviços prestados.
Essas informações são obtidas através dos seguintes métodos:

andarMenosUtilizado(): Retorna o andar menos utilizado pelos usuários.
elevadorMaisFrequentado(): Retorna o elevador mais frequentado e o período em que se encontra maior fluxo.
elevadorMenosFrequentado(): Retorna o elevador menos frequentado e o período em que se encontra menor fluxo.
periodoMaiorFluxoConjunto(): Retorna o período de maior utilização do conjunto de elevadores.
percentualDeUsoElevadores(): Retorna o percentual de uso de cada elevador em relação ao total de serviços prestados.
Execução do Sistema
Para executar o sistema, basta criar um objeto ElevadorServiceImpl, passando como parâmetro o caminho para o arquivo JSON contendo as informações de uso dos elevadores. Em seguida, basta chamar os métodos para obter as informações desejadas.

O arquivo input.json contém as informações utilizadas para testar o sistema. Para testar o sistema com outras informações, basta criar um arquivo JSON com o mesmo formato e passar o caminho desse arquivo como parâmetro para o construtor de ElevadorServiceImpl.

Tecnologias Utilizadas
O sistema foi implementado em Java 8, utilizando as bibliotecas Gson e JUnit. Para compilar e executar o sistema, é necessário ter o JDK 8 e o Maven instalados.

Autor
Desenvolvido por Leandro Miranda.