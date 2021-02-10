INSERT INTO flipnet.app_config(
config_type, value, inst_id, ability_code)
VALUES ('communication', '{"httpMethod":"POST","url":"https://nextar.flip.id/disburse","username":"HyzioY7LP6ZoO7nTYKbG8O4ISkyWnX1JvAEVAhtWKZumooCzqp41"}', 'BIGFLIP', 'WITHDRAW');

INSERT INTO flipnet.app_config(
config_type, value, inst_id, ability_code)
VALUES ('communication', '{"httpMethod":"GET","url":"https://nextar.flip.id/disburse/","username":"HyzioY7LP6ZoO7nTYKbG8O4ISkyWnX1JvAEVAhtWKZumooCzqp41"}', 'BIGFLIP', 'WITHDRAWQUERY');

INSERT INTO flipnet.app_config(
config_type, value, inst_id, ability_code)
VALUES ('router', '{"availableChannel":["BIGFLIP","ALTO"],"routeRulePriority":["CHANNEL_STATUS","SERVICE_TIME"],"channelAttributeDef":{"BIGFLIP":{"CHANNEL_STATUS":"Y","SERVICE_TIME":"04:00-22:00"},"ALTO":{"CHANNEL_STATUS":"Y","SERVICE_TIME":"01:00-02:00"}}}', 'DEFAULT', 'WITHDRAW');