# xy-inc
Processo seletivo ZUP

IDE de desenvolvimento Spring Tools Suite 3.7.3.RELEASE.

Configurado H2-Database  - Como banco para testes em desenvolvimento.

Aplicado a solução utilizando serviço RESTFul - Seguindo constraints definidas por Roy Fielding na especificação.

*Interface Uniforme
*Stateless
*Retorno padronizado utilizando HTTP Status Code, dentre outras constraints.

Utilizado Spring Framework buscando produtividade e facilidade de integrações com outras frameworks.

Informações para execução do projeto.

Para simplicar testes do WebService, importar o project no Spring Tools Suite.

Clique em File -> Import -> Existing Mavem Project -> e selecione pasta clonado através do GIT.

Abra o arquivo ZupApplication, clique na opção RUN no menu superior, RUN AS -> SpringBootAPP.

http://localhost:8080/products - Lista todos os produtos       -GET

http://localhost:8080/products/{id} - Busca um produto por id  -GET

http://localhost:8080/products - Cria um novo produto          -POST

http://localhost:8080/products/{id} - Edita um produto         -PUT

http://localhost:8080/products/{id} - Deleta um produto        -DELETE


Utilizado POSTMAN para simulação de requisição do lado cliente.

Tempo gasto para desenvolvimento = 30 minutos.

Prazo para entrega = 15/08/2016

Caso seja necessário, utilizarei o tempo restante para acrescentar mais funcionalidades e frontend como PLUS ao projeto.