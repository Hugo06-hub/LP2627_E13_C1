# LP2627_E13_C1

Trabalho de LP grupo 13:

Como está organizado

O Main é só o menu, a parte que fala com quem usa o programa. A lógica toda está no Gerir: valida o que o utilizador escreve, grava na base de dados e faz as consultas. As classes da pasta model correspondem às tabelas: Cliente, Funcionario, Espaco, Objeto, HistoricoEstado, Levantamento, Mensagem e Registo.

As relações que mais pesam são: um cliente tem muitas passagens de cartão, um objeto tem muitos estados no histórico, e clientes e objetos ligam-se entre si através das reclamações.

Onde trocar as credenciais

Abre o ficheiro hibernate.cfg.xml, que está na pasta src/main/resources, e procura as duas linhas com connection.username e connection.password. No username, troca root pelo teu utilizador do MySQL. Na password, troca as quatro estrelas pela tua password do MySQL. 


As user stories:

User story 1, registar o cliente: fica guardado o nome, o email e o telefone, e o programa não deixa registar dois clientes com o mesmo email.

User story 2, contactar os perdidos e achados: o cliente reporta o que perdeu e escreve ao fórum, por telefone ou por email.

User story 3, alterar o estado de um objeto: o funcionário muda entre perdido, achado e entregue, e o histórico guarda cada mudança com a data.

User story 4, responder no fórum: o funcionário responde às mensagens dos clientes, por exemplo quando alguém diz que o objeto é seu.

User story 5, passar o cartão: a leitura do cartão à entrada e à saída regista o dia e a hora, e é isso que permite reclamar um objeto.
