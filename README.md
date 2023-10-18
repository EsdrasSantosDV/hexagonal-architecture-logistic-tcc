
🏛️ Arquitetura de Software: Uma Exploração Profunda da Arquitetura Hexagonal
Definindo a Essência da Arquitetura de Software

A arquitetura de software é profundamente explorada como um fundamento crucial para raciocinar sobre os sistemas em desenvolvimento, envolvendo elementos do software, relações entre eles e propriedades de ambos, como proposto por Bass, Kazman e Clements (2012). A arquitetura não somente estabelece a estrutura, mas também transcende para estilos, estética, funcionalidade e interações, assim como acontece na arquitetura física de edificações.

Uma Metáfora Construtiva: Edifícios e Softwares

A consideração de Bonocore (2022) acerca da espinha dorsal da solução tecnológica oferece uma comparação proveitosa entre a arquitetura de edifícios e software. Ambas vão além da mera estrutura, enfatizando estilos, funcionalidade e a integração holística dos espaços/elementos com seus ocupantes/utilizadores. Onde arquitetos de edifícios consideram estilos, diretrizes e ambientes, arquitetos de software planejam antecipadamente, considerando diretrizes que orientarão o desenvolvimento de sistemas.

Organização e Estrutura

A sofisticação da arquitetura de software está na habilidade de harmonizar as funções distribuídas e facilitar a comunicação entre os componentes do sistema, como explorado por Shaw e Garlan (1996). Adicionalmente, como Fairbanks (2010) ilustra, a arquitetura de um software é, em muitos aspectos, semelhante à estrutura geopolítica de um país, onde a organização e decisões tomadas reverberam muito além do código-fonte, refletindo em suas funcionalidades, interações e operações.

🔍 Arquitetura Hexagonal: Explorando a Estrutura e Aplicações no Desenvolvimento de Sistemas



Estruturação e Principais Elementos

Vamos mergulhar profundamente na estrutura e nos principais elementos da arquitetura hexagonal, explorando como ela estabelece uma base robusta para o desenvolvimento de sistemas, com um olhar aguçado para as relações e propriedades em diferentes domínios.

Implicações no Desenvolvimento de Sistemas

Através de uma análise meticulosa, exploraremos como a arquitetura hexagonal pode ser aplicada abrangentemente no universo do desenvolvimento de sistemas, fornecendo uma fundação estável e flexível para a criação de soluções de software eficientes.

Arquitetura Hexagonal e Logística

Com um foco especial nos sistemas de gestão logística, exploraremos como a arquitetura hexagonal pode ser instrumental para atender aos objetivos de negócios e desafios particulares desta esfera, especialmente no que diz respeito à gestão de estoque logístico.

Entrelaçando Negócios e Tecnologia

O esforço é para destacar a complexidade das operações logísticas, sublinhando a importância vital da arquitetura de software hexagonal no panorama contemporâneo de negócios e tecnologia, criando uma sinergia que aprimora tanto o entendimento quanto a implementação prática nos sistemas de desenvolvimento.
A Crítica Infiltração da Lógica de Negócios e a Salvaguarda pela Arquitetura Hexagonal
A coesão intrincada entre a lógica de negócios e a interface do usuário tem sido um ponto crítico de fragilidade na arquitetura de software, como pontuado por Cockburn (2006). Esta amalgamação, não apenas atua como um impeditivo para a modificabilidade eficaz dos aplicativos, mas também semeia as sementes para sistemas que são inerentemente inflexíveis e resistentes à adaptação e modificação.

Dilema da Rigidez e Obsolescência Tecnológica
Alterações tanto na lógica de negócios quanto na tecnologia da interface imergem como desafios formidáveis, exigindo reestruturações significativas do código existente e eventualmente levando os sistemas a um estado de obsolescência tecnológica. Adicionalmente, a fusão da lógica de negócios ao código da interface notoriamente incrementa custos e complicações ao longo do ciclo de vida do desenvolvimento de software. Tal cenário manifesta-se em atrasos de entrega, elevação dos custos de manutenção, e, finalmente, diminui a eficácia dos esforços no desenvolvimento de software.


Hexagonal como a Chave para a Desacoplação
Arquitetura Hexagonal, explorada por Vieira (2021), emerge como uma resposta estratégica a essa complexidade enraizada. Ela orquestra uma estrutura que promove a separação e isolamento do código de negócios do código de tecnologia. Este isolamento, mais do que um tampão, atua como uma estratégia prudencial, garantindo que o código de negócios possa ser desenvolvido, testado e evoluído independentemente, se liberando das cadeias de preocupações vinculadas à tecnologia de interface ou à infraestrutura subjacente.

Hexágonos: Domínio e Aplicação
O Hexágono do Domínio é visualizado como um santuário, onde elementos vitais para solucionar problemas de negócios são consolidados e preservados de influências e perturbações externas. Paralelamente, o Hexágono da Aplicação interpreta um papel crítico como mediador, utilizando portas e casos de uso para processar e orquestrar as regras de negócios do Hexágono do Domínio e também para permitir a comunicação fluida com a tecnologia subjacente.



![Hexagonal Architecture Modules](doc/hexagonal-architecture-modules.png)
Portas: Mecanismos de Comunicação Unificada
Martinez (2021) nos ilumina sobre o conceito de "portas", vistas como mecanismos que habilitam aplicações a revelar suas funcionalidades de maneira agnóstica à tecnologia. Estas portas, ao proporcionarem uma interface de comunicação padronizada, facilitam uma comunicação unificada com o ambiente externo, que é orquestrada dentro do Hexágono do Framework.

Adaptadores: O Elo de Ligação
Os adaptadores, como definidos em detalhe por Martinez (2021), iniciam a interação com a aplicação através de uma porta, utilizando uma tecnologia específica, e são incorporados na arquitetura hexagonal, concretizando decisões tecnológicas e orchestrando a transmissão de pedidos e respostas entre os hexágonos e o ambiente externo.

Sintetizando com Woltmann (2023)
Em um trecho elucidativo de Woltmann (2023), a lógica de negócios, situada no coração da arquitetura, define interfaces - as portas - para comunicar-se com o mundo externo. Este arranjo assegura que as modulações tecnológicas ou na interface do usuário sejam gerenciadas de forma isolada, sem prejudicar adversamente a lógica de negócios do software, endossando uma postura ágil e adaptativa ante as mutações constantes no cenário tecnológico e operacional.

Reflexão Final
Neste contexto, a arquitetura hexagonal surge como um paradigma que promove a robustez e flexibilidade, mitigando os desafios clássicos relacionados à coesão indesejada da lógica de negócios e código de interface. Dessa forma, ela não apenas atua como uma estratégia de design de software, mas também como uma filosofia de desenvolvimento que acentua a independência, evolução e adaptabilidade do código de negócios em meio às ondas de mudanças tecnológicas e exigências operacionais.





## Como Executar
Para rodar a aplicação, certifique-se de que você tenha o Maven e o Docker instalados em sua máquina. Siga os passos abaixo para subir a aplicação:

Pré-requisitos
Maven
Docker
Docker Compose

### Banco de Dados
1. Navegue até `infra/database`.
2. Execute `docker-compose up -d`.

### Aplicação
- **Padrão (In-memory)**: `mvn quarkus:dev`
- **MySQL**: `mvn quarkus:dev -Dpersistence=mysql`



