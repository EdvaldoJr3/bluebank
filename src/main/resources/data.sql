

select 1;

/*
INSERT INTO `role` VALUES (1,'ADMIN');

--TIPO OPERACAO

INSERT INTO `bluebank`.`tipo_operacao` (`id`, `data_alteracao`, `descricao`) VALUES (1, now(), 'Crédito');
INSERT INTO `bluebank`.`tipo_operacao` (`id`, `data_alteracao`, `descricao`) VALUES (2, now(), 'Débito');


--AGENCIA

INSERT INTO `bluebank`.`agencia` (`id`, `data_alteracao`, `digito`, `numero`, `status`)
VALUES (1, now(), '1', '101112', 1);

INSERT INTO `bluebank`.`agencia` (`id`, `data_alteracao`, `digito`, `numero`, `status`)
VALUES (2, now(), 2, '202122', 1);

INSERT INTO `bluebank`.`agencia` (`id`, `data_alteracao`, `digito`, `numero`, `status`)
VALUES (3, now(), 3, '303132', 1);

INSERT INTO `bluebank`.`agencia` (`id`, `data_alteracao`, `digito`, `numero`, `status`)
VALUES (4, now(), 4, '404142', 1);


--CLIENTES

--  45657006542
--	94704634477
--	22987703015
--	31825535850 

INSERT INTO `bluebank`.`cliente` (`id`, `cpf`, `data_alteracao`, `email`, `endereco`, `nome`, `status`)
VALUES ('1', '45657006542', now(), 'joao@seuemail.com', 'Rua de João ', 'João Alberto', '1');

INSERT INTO `bluebank`.`cliente` (`id`, `cpf`, `data_alteracao`, `email`, `endereco`, `nome`, `status`)
VALUES ('2', '94704634477', now(), 'maria@seuemail.com', 'Rua de Maria', 'Maria Clara', '1');

INSERT INTO `bluebank`.`cliente` (`id`, `cpf`, `data_alteracao`, `email`, `endereco`, `nome`, `status`)
VALUES ('3', '22987703015', now(), 'claudio@seuemail.com', 'Rua de Cláudio', 'Claudio Luiz', '1');

INSERT INTO `bluebank`.`cliente` (`id`, `cpf`, `data_alteracao`, `email`, `endereco`, `nome`, `status`)
VALUES ('4', '31825535850', now(), 'roberta@seuemail.com', 'Rua de Roberta', 'Roberta Miranda', '1');


--CONTA CORRENTE

INSERT INTO `bluebank`.`conta_corrente` (`id`, `agencia_id`, `cliente_id`, `data_alteracao`, `digito`, `numero`, `status`)
VALUES ('1', '1', '1', now(), '1', '505152', '1');

INSERT INTO `bluebank`.`conta_corrente` (`id`, `agencia_id`, `cliente_id`, `data_alteracao`, `digito`, `numero`, `status`)
VALUES ('2', '2', '2', now(), '2', '606162', '1');

INSERT INTO `bluebank`.`conta_corrente` (`id`, `agencia_id`, `cliente_id`, `data_alteracao`, `digito`, `numero`, `status`)
VALUES ('3', '3', '3', now(), '3', '707172', '1');

INSERT INTO `bluebank`.`conta_corrente` (`id`, `agencia_id`, `cliente_id`, `data_alteracao`, `digito`, `numero`, `status`)
VALUES ('4', '4', '4', now(), '4', '808182', '1');


--USUARIO

INSERT INTO `bluebank`.`usuario` (`id`, `cpf`, `data_alteracao`, `senha`, `status`, `cliente_id`)
VALUES ('1', '45657006542', now(), '$2a$10$lxcIEKek.K0o.PNqFaG/xOPNR97/leepscjFQmwMsomS/wbDsXh.S', '1', '1');

INSERT INTO `bluebank`.`usuario` (`id`, `cpf`, `data_alteracao`, `senha`, `status`, `cliente_id`)
VALUES ('2', '94704634477', now(), '$2a$10$lxcIEKek.K0o.PNqFaG/xOPNR97/leepscjFQmwMsomS/wbDsXh.S', '1', '2');

INSERT INTO `bluebank`.`usuario` (`id`, `cpf`, `data_alteracao`, `senha`, `status`, `cliente_id`)
VALUES ('3', '22987703015', now(), '$2a$10$lxcIEKek.K0o.PNqFaG/xOPNR97/leepscjFQmwMsomS/wbDsXh.S', '1', '3');

INSERT INTO `bluebank`.`usuario` (`id`, `cpf`, `data_alteracao`, `senha`, `status`, `cliente_id`)
VALUES ('4', '31825535850', now(), '$2a$10$lxcIEKek.K0o.PNqFaG/xOPNR97/leepscjFQmwMsomS/wbDsXh.S', '1', '4');


--USUARIO_ROLE

INSERT INTO `bluebank`.`usuario_role` (`id`, `role_id`) VALUES ('1', '1');
INSERT INTO `bluebank`.`usuario_role` (`id`, `role_id`) VALUES ('2', '1');
INSERT INTO `bluebank`.`usuario_role` (`id`, `role_id`) VALUES ('3', '1');
INSERT INTO `bluebank`.`usuario_role` (`id`, `role_id`) VALUES ('4', '1');

--CONTA_CORRENTE_SALDO
INSERT INTO `bluebank`.`conta_corrente_saldo` (`id`, `conta_corrente_id`, `data_alteracao`, `saldo`, `versao`) 
VALUES ('1', '1', now(), '10000', '1');

INSERT INTO `bluebank`.`conta_corrente_saldo` (`id`, `conta_corrente_id`, `data_alteracao`, `saldo`, `versao`) 
VALUES ('2', '2', now(), '20000', '1');

INSERT INTO `bluebank`.`conta_corrente_saldo` (`id`, `conta_corrente_id`, `data_alteracao`, `saldo`, `versao`) 
VALUES ('3', '3', now(), '30000', '1');

INSERT INTO `bluebank`.`conta_corrente_saldo` (`id`, `conta_corrente_id`, `data_alteracao`, `saldo`, `versao`) 
VALUES ('4', '4', now(), '40000', '1');
*/
