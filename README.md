# xy-inc
Processo seletivo ZUP

IDE de desenvolvimento Spring Tools Suite 3.9.3.RELEASE.

Configurado H2-Database  - Como banco para testes em desenvolvimento.

Aplicado a solução utilizando serviço RESTFul - Seguindo constraints definidas por Roy Fielding na especificação.

*Interface Uniforme
*Stateless
*Retorno padronizado utilizando HTTP Status Code, dentre outras constraints.

Utilizado Spring Framework buscando produtividade e facilidade de integrações com outras frameworks.

-------------------------------------------------------------------------------------------------------------

Informações para execução do projeto.

Importar o project no Spring Tools Suite ou IDE de sua preferência.

Clique em File -> Import -> Existing Mavem Project -> e selecione pasta clonado através do GIT.

Abra o arquivo ZupApplication, clique na opção RUN no menu superior, RUN AS -> SpringBootAPP.

------------------------------------------------------------------------------------------------------------

Ou se preferir, abra o terminal, clone o projeto:

https://github.com/kenhiti/xy-inc.git  

Entre no diretório /xy-inc , e execute:

mvn clean install compile package

Execute o JAR gerado na pasta target:

java -jar Senior-1.0.0-SNAPSHOT.jar 

------------------------------------------------------------------------------------------------------------

Para facilitar os testes, foi implementado no projeto a dependência do SWAGGER, uma interface amigável para acesso aos ENDPOINTS do projeto. 

Abra o browser de sua preferência, e entre na seguinte URL:

http://localhost:8080/swagger-ui.html

